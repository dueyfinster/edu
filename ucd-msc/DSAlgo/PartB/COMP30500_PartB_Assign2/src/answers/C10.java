package answers;

public class C10 {

	public static void main(String[] args) {
		Instrument i = new Instrument(10);
		i.start();
		int y = 1000000;
		int q = i.randInt(1, y);
		System.out.println("Looking for:" + q);
		for(int x=0; x<5; x++){
			int A[] = i.createArray(y, 1, y);
			System.out.println("Result: " + LinearSearch(A, A.length, q) + " in Looking for:" + q);
			y = y + y;
		}
		
		y = 5000000;
		for(int x=0; x<5; x++){
			int A[] = i.createArray(y, 1, y);
			System.out.println("Result: " + LinearSearch(A, A.length, q) + " in Looking for:" + q);
			y = y + 1000000;
		}
		
		i.stop();
		
		i.calculateRunningTime();
	}
	
	public static int LinearSearch(int[] A, int n, int q){
		
		int index=0;
		
		while(index<n && ((A[index]>q) | (A[index]<q))){
			index = index+1;
		}
		
		if(index==n){
			return -1;
		}else{
			return index;
		}		
	
	}
}
