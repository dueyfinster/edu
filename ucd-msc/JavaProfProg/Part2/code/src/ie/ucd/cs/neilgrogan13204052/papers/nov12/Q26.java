package ie.ucd.cs.neilgrogan13204052.papers.nov12;

public class Q26 {

    public static void main(String[] args) {
        Integer x = 1; //4
        x++; //5
        Integer y = 2;
        if(x==y){ //7
            System.out.println("Area: " + areaOfASquare(4.0d)); //8
        }
    }

    public static Double areaOfASquare(Double side){
        return side*side;
    }

    /**
     * What is the result when you attempt to compile this code?
     *
     * A. compiler error at line 4
     * B. compiler error at line 5
     * C. compiler error at line 7
     * D. compiler error at line 8
     * E. compiles fine (no errors)
     */

}
