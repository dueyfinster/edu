package matrices;

import java.util.Scanner;

public class ProductOfMatrices {


	
	private static Matrix multiplyMatrices(Matrix m, Matrix n) {
		if ( m.getNoOfRows() != n.getNoOfColumns() ){
	         System.out.println("Can't multiply these matrices with each other. Must have equal # of points for successful multiplication.");
	         return null;
		}
		int sum =0;
		Matrix result = new Matrix(m.getNoOfRows(), n.getNoOfColumns());
		
		for (int c = 0 ; c < m.getNoOfRows() ; c++ ){
           for (int d = 0 ; d < n.getNoOfColumns() ; d++ ){   
              for (int k = 0 ; k < n.getNoOfRows() ; k++ ){
            	  
            	  sum = sum + m.getPoint(c, k)*m.getPoint(k, d);
              }
              result.setPoint(c, d, sum);
              sum = 0;
           }
        }
		
		return result;
	}
	
	public static void main(String[] args) {
		Matrix m = MatrixUtils.createMatrix();
		MatrixUtils.setMatrixPoints(m);
		
		Matrix n = MatrixUtils.createMatrix();
		MatrixUtils.setMatrixPoints(n);
		
		Matrix r = multiplyMatrices(m,n);
		
		System.out.println("Result is: ");
		r.toString();
		
	}


}
