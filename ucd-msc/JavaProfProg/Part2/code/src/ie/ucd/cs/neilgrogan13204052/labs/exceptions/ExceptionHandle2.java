package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ExceptionHandle2 {
    public static void main(String[] args) {
        int x = 15;
        int y = 1;

        try{
            System.out.println("x/y: " + x/y);
            System.out.println("x+y: " + (x+y));
        }catch(ArrayIndexOutOfBoundsException outOfBoundsException){
            System.out.println("An exception occurred: " + outOfBoundsException);
        }finally {
            System.out.println("Finally Block must be executed");
        }
        System.out.println("x-y:" + (x-y));
    }
}
