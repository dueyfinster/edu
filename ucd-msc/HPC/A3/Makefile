PROGRAM_NAME=A3
DATA_FILE1=open_mp_data
CC=gcc
CFLAGS=-pthread
Q3_CFLAGS=-Ilibs/CBLAS/src libs/cblas_LINUX.a libs/libblas.a -lgfortran
# YEATS DID NOT WORK FOR ME!!
Q3_CFLAGS_YEATS=-I/opt/atlas-3.8.3/interfaces/blas/C/src/ /opt/atlas-3.8.3/lib/libcblas.a /usr/lib64/libblas.a -lgfortran


all: $(PROGRAM_NAME)

yeats: $(PROGRAM_NAME).c 
	$(CC) -fopenmp -o  $(PROGRAM_NAME) $(PROGRAM_NAME).c $(Q3_CFLAGS_YEATS) 

$(PROGRAM_NAME): $(PROGRAM_NAME).c 
	$(CC) -fopenmp -o  $(PROGRAM_NAME) $(PROGRAM_NAME).c $(Q3_CFLAGS) 



clean:
	rm -f $(PROGRAM_NAME)
	rm -f $(DATA_FILE1)

