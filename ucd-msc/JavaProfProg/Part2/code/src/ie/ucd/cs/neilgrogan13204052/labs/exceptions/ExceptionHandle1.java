package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ExceptionHandle1 {

    public static void main(String[] args) {
        int x = 15;
        int y = 0;
        try {
            System.out.println("x/y: " + x / y);
        }catch(ArithmeticException e){
            System.out.println("Sorry cannot " +  e.getMessage());
        }
        System.out.println("x*y: " + x*y);
        System.out.println("x-y: " + (x-y));
    }
}
