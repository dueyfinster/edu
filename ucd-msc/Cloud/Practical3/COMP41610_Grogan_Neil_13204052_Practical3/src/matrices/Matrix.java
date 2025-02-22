package matrices;

import java.util.Arrays;

public class Matrix {
	private int rows;
	private int columns;
	private Integer[][] matrix;
	
	public Matrix(int a, int b){
		matrix = new Integer[a][b];
		rows = a;
		columns = b;
	}

	public void setPoint(int a,int b, int c){
		matrix[a][b] = c;
	}
	
	public int getPoint(int a, int b){
		return matrix[a][b];
	}
	
	public int getNoOfRows(){
		return rows;
	}
	
	public int getNoOfColumns(){
		return columns;
	}
	
	public int getTotalNumberOfPoints(){
		return rows * columns;
	}
	
	public String toString(){
		for (Integer[] current : matrix) {
            System.out.println(Arrays.toString(current));
        }
		return null;
	}
}
