package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class MultipleExceptions {

    public static void main(String[] args) {
        int[] x = {0, 1, 2, 3, 4};
        try{
            System.out.println("x[6]: " + x[4]); // From 6 to 4
            System.out.println("[3] " + x[3]);
        }catch(ArithmeticException ae){
            System.out.println("An arithmetic exception occurred: " + ae);
        }catch(ArrayIndexOutOfBoundsException oe){
            System.out.println("Array index out of bound!");
        }catch(IndexOutOfBoundsException ie){
            System.out.println("Some kind of index out of bound");
        }finally{
            System.out.println("Finally must be executed!");
        }

        System.out.println("x[0]: " + x[0]);
    }
}
