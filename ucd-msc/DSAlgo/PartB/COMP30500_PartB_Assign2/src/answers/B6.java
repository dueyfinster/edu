package answers;

public class B6 {

	
	public static void main(String[] args) {
		int A[] = {5,4,3,2,1};
		int n = A.length;
		System.out.println(minValueIndex(A, n));
	}
	
	public static int minValueIndex(int[] A, int n){
		int minValueIndex=0;
		for(int i=0; i<n; i++){
			
			if(A[i]<A[minValueIndex]){
				minValueIndex = i;
			}
			
			System.out.println("i=" + i );
			System.out.println("n=" + n );
			System.out.println("minValueIndex=" + minValueIndex );
		}
		
		return minValueIndex;
		
	}
}
