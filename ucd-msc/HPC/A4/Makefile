PROGRAM_NAME=A4
DATA_FILE1=open_mpi_data
CC=mpicc 
Q4_CFLAGS=-I/home/cs/bobr/include -L/home/cs/bobr/lib -lgslcblas


all: $(PROGRAM_NAME)

$(PROGRAM_NAME): $(PROGRAM_NAME).c 
	$(CC) -o  $(PROGRAM_NAME) $(PROGRAM_NAME).c $(Q4_CFLAGS) 

clean:
	rm -f $(PROGRAM_NAME)
	rm -f $(DATA_FILE1)

