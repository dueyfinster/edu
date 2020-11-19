package matrices;

import java.util.Scanner;

public class MatrixUtils {
	public static Matrix createMatrix(){
		 Scanner in = new Scanner(System.in);
		 System.out.println("Enter rows for matrix: ");
	     int m = in.nextInt();
	     System.out.println("Enter columns for matrix: ");
	     int n = in.nextInt();
	      
	     return new Matrix(m,n);
	}
	
	public static void setMatrixPoints(Matrix m){
		Scanner in = new Scanner(System.in);
		for(int i=0; i<m.getNoOfRows(); i++){
			for(int j=0; j<m.getNoOfColumns(); j++){
				 System.out.println("Enter value for ["+i+"]["+j+"] in the matrix: ");
				 m.setPoint(i, j, in.nextInt());
			}
		}
	}
}
