package answers;

public class B10 {

	public static void main(String[] args) {
		int A[] = {5,4,3,2,1};
		int n = A.length;
		int q = 4;
		System.out.println(LinearSearch(A, n, q));
	}
	
	public static int LinearSearch(int[] A, int n, int q){
		
		int index=0;
		
		while(index<n && (A[index]!=q)){
			index = index+1;
		}
		
		if(index==n){
			return -1;
		}else{
			return index;
		}		
	
	}
}
