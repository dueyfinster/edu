package answers;

import java.util.Random;

public class Instrument {

	private long start;
	private long end;
	private int numberOfRuns;
	
	Instrument(int numberOfRuns){
		this.numberOfRuns = numberOfRuns;
	}
	
	protected void start(){
		start = System.currentTimeMillis();
	}
	
	protected void stop(){
		end = System.currentTimeMillis();
	}
	
	protected void calculateRunningTime(){
		long duration = end-start;
		duration = duration / numberOfRuns;
		
		System.out.print("Running Time=" + duration);
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	public int[] createArray(int sizeOfArray, int min, int max){
		int A[] = new int[sizeOfArray];
		
		for(int y=0; y<sizeOfArray; y++){
			A[y] = Instrument.randInt(min,max);
		}
		
		return A;
		
	}
}
