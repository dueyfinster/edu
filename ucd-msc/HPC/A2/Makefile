PROGRAM_NAME=A2
DATA_FILE1=single_threaded
DATA_FILE2=multi_threaded
CC=gcc
CFLAGS=-pthread
Q3_CFLAGS=-I/home/cs/khasanov/libs/CBLAS/src libs/cblas_LINUX.a libs/libblas.a -lgfortran -pthread

all: $(PROGRAM_NAME)

$(PROGRAM_NAME): $(PROGRAM_NAME).c 
	$(CC) -g -o $(PROGRAM_NAME) $(PROGRAM_NAME).c $(Q3_CFLAGS) 

clean:
	rm -f $(PROGRAM_NAME)
	rm -f $(DATA_FILE1)
	rm -f $(DATA_FILE2)

