/*************************************************************
* COMP40730 High Performance Computing 2014
* by Neil Grogan - Student No. 13204052
*
* Assignment 2 - Variant of Assignment: Q1a, Q2a, Q3a
*
* Assignment 2 - Question 1(a)
*
* NOTES: Seems
**************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include <cblas.h>
#include <string.h>
#include <pthread.h>
#define MAXTHRDS 124

typedef struct
{
    double *my_A; //my portion of A
    double *B; //all of B
    double *my_C; //my portion of C
    double **A_global;
    double **B_global;
    double **C_global;
    int n; //vector length
    pthread_mutex_t* mutex; // mutex
    int row_num; // row number
    int rows; // num of rows
} multip_ds; // Multiply Data Structure

//do matrix multiplication 
void *Multiply(void *arg)
{
    // to avoid filling C, replace result, so BETA = 0
    double ALPHA = 1.0, BETA = 0.0;
    multip_ds *my_data = (multip_ds*)arg;
    
    printf("\n Doing multiplication...\n ");

    if (my_data == NULL){ printf("Input is null: my_data\n"); exit(1);}
    else if(my_data->my_A == NULL){ printf("Input is null: my_data->my_A \n"); exit(1);}
    else if(my_data->B == NULL){ printf("Input is null: my_data->B\n"); exit(1);} 
    else if(my_data->my_C == NULL){ printf("Input is null: my_data->my_C\n"); exit(1);} 
    else if(my_data->rows<=0){ printf("Input is null: my_data->rows\n"); exit(1);}
    else if(my_data->n<=0){ printf("Input is null: my_data->n\n"); exit(1);}
    else{  printf("Input is okay! \n");}

    int n = my_data->n;
    pthread_mutex_lock(my_data->mutex);
    // SEGFAULT HAPPENS HERE - DON'T KNOW WHY
    cblas_dgemm( CblasRowMajor,  CblasTrans, CblasNoTrans, my_data->rows, n, n,
                 ALPHA, my_data->my_A, n, my_data->B, n, BETA, my_data->my_C, n );
    pthread_mutex_unlock(my_data->mutex);
    printf("\n Finished CBLAS Doing multiplication thread...\n ");
    //pthread_exit(0);
}

// COPIED FROM Khalid's WORKING EXAMPLE
void *matrix_multip(void *arg) {
   multip_ds *dot_data;
   int i, j, k;

   dot_data = arg;
   int rows;

   if((dot_data->row_num+dot_data->rows)>dot_data->n)
    rows = dot_data->n - dot_data->row_num;
   else
    rows = dot_data->rows;

   for(i=dot_data->row_num; i<(rows+dot_data->row_num); i++){
      for(j=0; j<dot_data->n; j++){
    for(k=0; k<dot_data->n; k++){
       dot_data->C_global[i][j] += dot_data->A_global[i][k]*dot_data->B_global[k][j];
    }
      }
   }
   pthread_exit(NULL);
}

typedef struct
{
    double *my_C; //my portion of C
    int n; //vector length
    int row; // num of row
    double *global_col_norm; //global dot product
    pthread_mutex_t* mutex; // mutex
    int my_chunk_len;
} norm_ds; // Data structure for normalisation

//computation of norm
void *compute_col_norm(void *arg)
{
    norm_ds* my_data = (norm_ds*)arg;

    int n = my_data->n;
    int row = my_data->row;
    int my_chunk_len =  my_data->my_chunk_len;

    int i = 0;
    int j = 0;

    printf("my_chunk_len=%d\n",my_chunk_len);

    // cycle through chunks
    for (j = 0; j < my_chunk_len; j++)
    {
    double my_sum = 0;
        //inner loop
        for (i = 0; i < n; i++)
        {
            //printf("I'm at: %d,%d",i,j);
            my_sum+=my_data->my_C[i*n+j];
            //printf("value of: %d,%d is %f\n",i,j, my_data->my_C[i*n+j] );
            printf("%f\t",my_data->my_C[i*n+j] );
            pthread_mutex_lock(my_data->mutex);
            // Update global column max norm only if bigger then current
            if(my_sum>*(my_data->global_col_norm)){
                *(my_data->global_col_norm) = my_sum;
            }
            pthread_mutex_unlock(my_data->mutex);
        }
        printf("\n");
    }

    pthread_exit(NULL);
}

FILE *createFile(char filename[])
{
    FILE *f = fopen(filename, "w");
    if (f == NULL)
    {
        printf("Error opening file!\n");
        exit(1);
    }

    // printi header values to the file
    fprintf(f, "#no_of_threads N TimeTaken\n");

    return f;
}


void help()
{
    printf("\n Arguments required: \n");
    printf("1. Number of Threads (num_of_thrds) \n");
    printf("2. Size of Matrix (n) \n");
    printf("3. Blocksize (bs) \n");
    printf("4. Repetitions (reps) \n");
    printf("5. Filename (fname) \n");
}

void args_check(int args)
{
    printf("Number of arguments is not correct, should be %d args specified.\n", args);
    help();
    exit(-1);
}

void calc_offsets(int n, double **vA, double *bA, double **vB, double *bB, double **vC, double *bC){
    int i=0;
    for (i = 0; i < n; i++)
    {
        vA[i] = bA + i * n;
        vB[i] = bB + i * n;
        vC[i] = bC + i * n;
    }
}

void fill_matrix(int n, double **vA, double **vB, double **vC){
    int i=0; int j=0;
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < n; j++)
        {
            vA[i][j] = 1.;
            vB[i][j] = 2.;
            vC[i][j] = 0.;
        }
    }
}

void create_multip_threads(int num_of_thrds, int n, double *bA, double *bB, double *bC, double **vA, double **vB, double **vC){
    void *status;
    multip_ds *thrd_multip_data; //create pointer for struct array
    pthread_t *working_thread = malloc(num_of_thrds * sizeof(pthread_t));

    //block all
    pthread_mutex_t mutex_norm;
    pthread_mutex_init(&mutex_norm, NULL);

    thrd_multip_data = malloc(num_of_thrds * sizeof(multip_ds)); // create array to store struct for each thread

    if(num_of_thrds<=0){ printf("Error num_of_thrds  null! \n");exit(1); }  
    else if(bA == NULL){ printf("Error bA null! \n");exit(1); }  
    else if(bB == NULL){ printf("Error bB null! \n");exit(1); }  
    else if(bC == NULL){ printf("Error bC null! \n");exit(1); }  
    else if(thrd_multip_data == NULL){ printf("Error thrd_multip_data null! \n");exit(1); }  
    else if(working_thread == NULL){ printf("Error working_thread null! \n");exit(1); }  
    else{
        printf("Create Multip thread data is not null");
    }

    int i=0;
    int row_num = 0;
    int row_chunk_num = n / num_of_thrds;
    for (i = 0; i < num_of_thrds; i++)
    {
        thrd_multip_data[i].my_A = (bA + i * n * (row_chunk_num)); //pass part of A
        thrd_multip_data[i].B = bB; // pass whole of B for calc
        thrd_multip_data[i].my_C = (bC + i * n * (row_chunk_num)); // pass part of C
        thrd_multip_data[i].A_global = vA;
        thrd_multip_data[i].B_global = vB;
        thrd_multip_data[i].C_global = vC;
        thrd_multip_data[i].n = n; //leading size of matrix
        thrd_multip_data[i].mutex = &mutex_norm;
        thrd_multip_data[i].row_num = row_num;
        thrd_multip_data[i].rows = row_chunk_num; // row number

        row_num = row_num+row_chunk_num;
        // create a thread to run function: Multiply with data: thrd_multip_data
        if(&thrd_multip_data[i] == NULL | &working_thread[i] == NULL){
            printf("Error thread data 1 structures null! \n");
            exit(1);
        }
        int thread_id = pthread_create(&working_thread[i], NULL, matrix_multip, (void *)&thrd_multip_data[i]);
        printf("\nCreated multip thread: %d\n", thread_id);
    }

    // for all the threads
    for (i = 0; i < num_of_thrds; i++)
    {
        //join them
        
        int thread_join_status = pthread_join(working_thread[i], &status);
        printf("\nJoining multip thread: %d\n", thread_join_status);
    }

    free(working_thread);
    free(thrd_multip_data);
}

double create_norm_thrds(int num_of_thrds, int n, double *bC){
    pthread_t *working_thread;
    norm_ds *thrd_norm_data;

    working_thread = malloc(num_of_thrds * sizeof(pthread_t));
    double glo_col_norm;
    void *status;

    //block all
    pthread_mutex_t mutex_norm;
    pthread_mutex_init(&mutex_norm, NULL);

    thrd_norm_data = malloc(num_of_thrds * sizeof(norm_ds));

    int column_chunk_num = n / num_of_thrds;
    int j=0; int i=0;

    printf("Starting norm calculation\n");

    // calculate norm for each row
    for (j = 0; j < num_of_thrds; j++)
    {
        thrd_norm_data[i].my_C = (bC + j * (column_chunk_num)); // pass part of C to each thread
        thrd_norm_data[i].n = n; //leading size of matrix
        thrd_norm_data[i].global_col_norm = &glo_col_norm;
        thrd_norm_data[i].mutex = &mutex_norm;
        thrd_norm_data[i].my_chunk_len = (j == num_of_thrds - 1) ? n - (num_of_thrds - 1) * column_chunk_num : column_chunk_num;
        // create a thread to run function: Multiply with data: thrd_multip_data
        printf("\nCreated norm thread: %d\n", i);
        pthread_create(&working_thread[j], NULL, compute_col_norm, (void *)&thrd_norm_data[i]);

    }


    // for all the threads
    for (i = 0; i < num_of_thrds; i++)
    {
        //join them
        pthread_join(working_thread[i], &status);
    }

    return glo_col_norm;
}

void repeat_matrix_multip_and_time(int numreps, int n, int num_of_thrds,double *glo_col_norm, double *bA, double *bB, double *bC,
double **vA, double **vB, double **vC, FILE *f)
{

    //set time structs
    struct timeval tv1, tv2;
    struct timezone tz;

    printf("Multiply matrices %d times...\n", numreps);
    int i=0;
    for (i = 0; i < numreps; i++ )
    {
        //get starting time
        gettimeofday(&tv1, &tz);
        // do work
        create_multip_threads(num_of_thrds, n, bA, bB, bC, vA, vB, vC );
        printf("Finished multip threads");
        *glo_col_norm = create_norm_thrds(num_of_thrds, n, bC );
        //complete time measurement
        gettimeofday(&tv2, &tz);
        double elapsed = (double) (tv2.tv_sec - tv1.tv_sec) + (double) (tv2.tv_usec - tv1.tv_usec) * 1.e-6;
        fprintf(f, "%d %d %f\n", num_of_thrds, n, elapsed);
    }

    // close the file
    fclose(f);

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

int main(int args, char **argv)
{
    int num_args_req = 6;

    if (args != num_args_req)
    {
        args_check(num_args_req);
    }

    int num_of_thrds = atoi(argv[1]); //number of threads
    int n = atoi(argv[2]); // leading size of matrix
    int bs = atoi(argv[3]); //blocksize
    int numreps = (int)(argv[4][0] - '0'); //number of reptitions
    char filename[30]; // name of the file
    strcpy(filename, &argv[5][0]);
    printf("no of threads is: %d n is: %d, bs is: %d, reps is: %d and filename is: %s.\n", num_of_thrds, n, bs, numreps, filename);



    int subvec_len;

    double *bA = malloc(sizeof(double) * n * n);
    double **vA = malloc(sizeof(double *)*n);
    double *bB = malloc(sizeof(double) * n * n);
    double **vB = malloc(sizeof(double *)*n);
    double *bC = malloc(sizeof(double) * n * n);
    double **vC = malloc(sizeof(double *)*n);
    double glo_col_norm = 0.0;
    // open a file to write to
    FILE *f = createFile(filename);

    int i = 0;
    int j = 0;

    calc_offsets(n, vA, bA, vB, bB, vC, bC);
    fill_matrix(n, vA, vB, vC);

    repeat_matrix_multip_and_time(numreps, n, num_of_thrds, &glo_col_norm, bA, bB, bC, vA, vB, vC, f);

    printMatrix(vC,n);

    printf("\nCol Max norm is: %f\n", glo_col_norm);

    //free memory
    free(bA);
    free(vA);
    free(bB);
    free(vB);
    free(bC);
    free(vC);
    //pthread_mutex_destroy(mutex_norm);

    return 0;
}
