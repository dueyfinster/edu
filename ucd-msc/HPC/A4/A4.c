#include <stdio.h>
#include <stdlib.h>
#include <float.h>
#include <unistd.h>
#include <string.h>
#include <gsl/gsl_cblas.h>
#include <mpi.h>

int n, p;


FILE *createFile(char filename[])
{
    FILE *f;
    int result;
    result = access (filename, F_OK); // F_OK tests existence also (R_OK,W_OK,X_OK).
                                      //            for readable, writeable, executable
    if ( result == 0 )
    {
       f = fopen(filename, "a+");
    }
    else
    {
        f = fopen(filename, "w");
       // printi header values to the file
        fprintf(f, "#N TimeTaken\n");
    }

    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }  

    return f;
}

int main(int argc, char **argv) {

	int myn, myrank;
	double *a, *b, *c, *myA, *myC, start, max, infNorm, sum, *allC, sumdiag;
	int i, j, k;
	infNorm =0.;
	n = atoi(argv[1]);
	char filename[30]; // name of the file
    strcpy(filename, &argv[2][0]);
    // open a file to write to
    FILE *f = createFile(filename);

	MPI_Init(&argc, &argv);
	MPI_Datatype tmp_type, col_type;
	MPI_Comm_size(MPI_COMM_WORLD,&p);
	MPI_Comm_rank(MPI_COMM_WORLD,&myrank);
	myn = n/p;

	if(myrank==0){
		a = malloc(n*n*sizeof(double));
		b = malloc(n*n*sizeof(double));
		c = malloc(n*n*sizeof(double));
	}
	myA = malloc(myn*n*sizeof(double));
	myC = malloc(myn*n*sizeof(double));

	for(i=0; i<n*n; i++) {
		a[i] = 1.;
		b[i] = 2.;
		c[i] = 0.;
	}

	MPI_Barrier(MPI_COMM_WORLD);

	MPI_Type_vector(myn, n, 1, MPI_DOUBLE, &tmp_type);
	MPI_Type_create_resized(tmp_type, 0, n * sizeof(double), &col_type);
	MPI_Type_commit(&col_type);
	MPI_Type_free(&tmp_type);
	// Scatter A and C
	MPI_Scatter(a, 1, col_type, myA, myn*n, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	MPI_Scatter(c, 1, col_type, myC, myn*n, MPI_DOUBLE, 0, MPI_COMM_WORLD);
	// Broadcast all of B
	MPI_Bcast(b, n*n, MPI_DOUBLE,0,MPI_COMM_WORLD);

	if(myrank==0)
		start = MPI_Wtime();

	// to avoid filling C, replace result, so BETA = 0
    double ALPHA = 1.0, BETA = 0.0;

	cblas_dgemm( CblasRowMajor,  CblasTrans, CblasNoTrans, myn, n, n,
	ALPHA, myA, n, b, n, BETA, myC, n );

	// cycle through chunks
	max = 0.;
    for (j = 0; j < myn*n; j++)
    {
    double my_sum = 0.;
        //inner loop
        for (i = 0; i < n; i++)
        {
            my_sum+=myC[i*n+j];
        }
        max = (max<my_sum)?my_sum:max;
    }

	MPI_Barrier(MPI_COMM_WORLD);

    MPI_Reduce(&max, &infNorm, 1, MPI_DOUBLE, MPI_MAX, 0, MPI_COMM_WORLD);
 
	if(myrank==0){
		fprintf(f, "%d %f\n", n, MPI_Wtime()-start);
		printf("It took %f seconds to multiply 2 %dx%d matrices.\n",MPI_Wtime()-start, n, n);
	}

	if(myrank==0)
		allC = malloc(n*n*sizeof(double));

	MPI_Gather(myC, myn*n, MPI_DOUBLE, allC, myn*n, MPI_DOUBLE,0, MPI_COMM_WORLD);

	if(myrank==0) {
		for(i=0, sumdiag=0.; i<n; i++)
			sumdiag += allC[i*n+i];
		printf("The trace of the resulting matrix is %f\n", sumdiag);
		printf("The max col norm of the resulting matrix is %f\n", infNorm);
	}

	if(myrank==0)
	free(allC);
	
	MPI_Type_free(&col_type);
	MPI_Finalize();
	free(a);
	free(b);
	free(c);
}