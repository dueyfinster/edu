package answers;

public class B4 {

	public static void main(String[] args) {
		int a=5,b=6;
		System.out.println(difference(a,b));
	}
	
	public static int difference(int a, int b){
		if(a > b){
        	return a-b;
		}else{
			return b-a;
		}
	}
}
