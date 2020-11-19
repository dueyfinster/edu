package ie.ucd.cs.neilgrogan13204052.papers.jan12;

public class Q1 {

    public static void main(String[] args) {
        int x = 15;
        int y = 1;
        int []z = new int [5];
        z[1]=0;
        try{
            System.out.println ("x/y: " + x/y);
            System.out.println(z[1]);
            System.out.println(z[10]);
            System.out.println("x*y: " + x*y);
        } catch (ArithmeticException ae) {
            System.out.println("An exception occurred: " + ae);
        }
        catch (ArrayIndexOutOfBoundsException oe) {
            System.out.println("An exception occurred: " + oe);
        }
        finally {
            System.out.println("finally block must be executed!");
        }
        System.out.println("x-y: " + (x-y));
    }

    /**
     * Which of the following is part of the output? (Choose all that apply)
     *
     * A. x/y: 15
     * B. x*y: 15
     * C. finally block must be executed!
     * D. x-y: 14
     * E: An exception occurred: java.lang.ArithmeticException: / by zero
     */
}
