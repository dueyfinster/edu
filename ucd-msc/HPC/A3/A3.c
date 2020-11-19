#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include <cblas.h>
#include <string.h>
#include <omp.h>
#define MAXTHRDS 124

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
        f = fopen(filename, "a+");
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

void fillArray(double **arr, int n, double value)
{
    int i = 0;
    int j = 0;
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < n; j++)
        {
            arr[i][j] = value;
        }
    }
}

double **createArray(int m, int n)
{
    double *values = calloc(m * n, sizeof(double));
    double **rows = malloc(n * sizeof(double *));
    int i = 0;
    for (i = 0; i < n; ++i)
    {
        rows[i] = values + i * m;
    }
    return rows;
}


void Multiply(int n, double** a, double** b, double** c){
    // Alpha 1 and Beta 1 for chunks of matrix, for single run - Alpha=1 Beta=0
    double ALPHA=1.0, BETA=0.0;

    cblas_dgemm( CblasRowMajor,  CblasTrans, CblasNoTrans, n, n, n,
                       ALPHA, a[0], n, b[0], n, BETA, c[0], n );    
}


void printMatrix(double **vA, int n)
{
    int numberOfLines = n;
    int numberColumns = n;

    printf("\n\t");
    int columns = 0;

    for (columns = 0; columns < numberColumns; columns++)
    {
        printf("| Col %d\t", columns);
    }
    printf("| \n");
    printf("\t+");
    char c = '-';

    int z = 0;
    for (z = 0; z < n * 8 - 1; z++)
    {
        printf("%c", c);
    }

    printf("+\n");

    int row = 0;

    for (row = 0; row < numberOfLines; row++)
    {
        printf("Row %d\t", row);
        int columns = 0;
        for (columns = 0; columns < numberColumns; columns++)
        {
            printf("| %.2lf\t", vA[row][columns]);
        }
        printf("| \n");

    }
}

void help()
{
    printf("\n Arguments required: \n");
    printf("1. Size of Matrix (n) \n");
    printf("2. Filename (fname) \n");
}

void args_check(int args)
{
    printf("Number of arguments is not correct, should be %d args specified.\n", args);
    help();
    exit(-1);
}

int main(int args, char **argv)
{
    int num_args_req = 3;

    if (args != num_args_req)
    {
        args_check(num_args_req);
    }


    int n = atoi(argv[1]); // leading size of matrix
    char filename[30]; // name of the file
    strcpy(filename, &argv[2][0]);
    
    // Create Arrays to hold matrices
    double **A = createArray(n, n);
    double **B = createArray(n, n);
    double **C = createArray(n, n);

    int i = 0;
    int j = 0;
    int k = 0;

    //set time structs
    struct timeval tv1, tv2;
    struct timezone tz;
    // open a file to write to
    FILE *f = createFile(filename);

    // Fill matrices A and B
    fillArray(A, n, 1.);
    fillArray(B, n, 1.);
    fillArray(C, n, 0.);


    printMatrix(A,n);

    printMatrix(B,n);

    printMatrix(C,n);

    //get starting time
    gettimeofday(&tv1, &tz);

    #pragma omp parallel 
    {

        Multiply(n, A, B, C);  
    }

    printMatrix(C,n);

    double max_sum = 0.0;

    double *c = C[0];

    double col_sum = 0.0;

    #pragma omp parallel for shared(max_sum,n,i,j,c)
    for (j = 0; j < n; j++) {
        col_sum = 0.0;
        #pragma omp parallel for private(col_sum)
        for (i = 0; i < n; i++) {
                printf("Adding %f to sum of col %d\n\n", c[i*n+j],j);
                col_sum += c[i*n+j];
                #pragma omp critical
                if(col_sum>max_sum){
                    max_sum=col_sum;
                }
        }
        //printf("col_sum for: c[%d] is: %f \n",j,col_sum);
    }

    //complete time measurement
    gettimeofday(&tv2, &tz);
    double elapsed = (double) (tv2.tv_sec - tv1.tv_sec) + (double) (tv2.tv_usec - tv1.tv_usec) * 1.e-6;
    fprintf(f, "%d %f\n", n, elapsed);

    printf("Max col norm is: %f\n\n", max_sum);

    return 0;
}
