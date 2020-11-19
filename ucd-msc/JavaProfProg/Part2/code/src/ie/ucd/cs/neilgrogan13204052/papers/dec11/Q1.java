package ie.ucd.cs.neilgrogan13204052.papers.dec11;

public class Q1 {

    public static void main(String[] args) {
        int[] x = {0, 1, 2, 3, 4};
        try{
            System.out.println("x[6]: " + x[6]);
            System.out.println("x[3]: " + x[3]);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Some kind of index out of bound!");
        }
        /*catch (ArrayIndexOutOfBoundsException oe) {
            System.out.println("Array index out of bound!" );
        }*/
        finally {
            System.out.println("finally block must be executed!");
        }
    }
    /**
     * Which one of the following is the output of this code?
     * A. Array index out of bound!
     *    finally block must be executed!
     * B. Some kind of index out of bound!
     *    finally block must be executed!
     * C. Some kind of index out of bound!
     *    Array index out of bound!
     *    finally block must be executed!
     * D. No output - a compiler error occurs
     */
}
