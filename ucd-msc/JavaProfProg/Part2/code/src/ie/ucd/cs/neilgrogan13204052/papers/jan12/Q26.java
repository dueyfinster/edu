package ie.ucd.cs.neilgrogan13204052.papers.jan12;

public class Q26 {

    public static void main(String[] args) {
        Integer x = 1;
        x++;
        Integer y = 2;
        if(x==y){
            System.out.println("Area: " + areaOfASquare(4.0d));
        }
    }

    public static Double areaOfASquare(Double side){
        return side*side;
    }

    /**
     * Consider the following code, What is the result when you attempt to compile this code?
     *
     * A. compiler error at line 4
     * B. compiler error at line 5
     * C. compiler error at line 7
     * D. compiler error at line 8
     * E. compiles fine (no errors)
     */

}
