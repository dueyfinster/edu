package answers;

public class C6 {

	
	public static void main(String[] args) {
		Instrument i = new Instrument(10);
		i.start();
		
		int y = 1000000;
		for(int x=0; x<5; x++){
			int A[] = i.createArray(y, 1, y);
			minValueIndex(A, A.length);
			y = y + y;
		}
		
		y = 5000000;
		for(int x=0; x<5; x++){
			int A[] = i.createArray(y, 1, y);
			minValueIndex(A, A.length);
			y = y + 1000000;
		}
		
		
		i.stop();
		
		i.calculateRunningTime();
	}
	
	public static int minValueIndex(int[] A, int n){
		int minValueIndex=0;
		
		System.out.println("array=" + A[0] + "," + A[1] + "," 
		+ A[2]  + "," + A[3] + "," + A[4]);
		for(int i=0; i<n; i++){
			
			
			
			if(A[i]<A[minValueIndex]){
				minValueIndex = i;
			}
		}
		
		System.out.println("minValueIndex=" + minValueIndex );
		
		return minValueIndex;
		
	}

}
