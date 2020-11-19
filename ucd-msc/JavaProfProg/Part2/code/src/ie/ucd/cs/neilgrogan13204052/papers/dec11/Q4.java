package ie.ucd.cs.neilgrogan13204052.papers.dec11;

public class Q4 {

    public static void main(String[] args) {
        try{
            System.out.println ("I was in try");
        }
        finally {
            System.out.println("I was in finally");
        }
    }

    /**
     * What is the result of executing this code? (Select the correct answer)
     *
     * A. I was in try
     * B. I was in finally
     * C. I was in try
     *    I was in finally
     * D. A compiler error occurs at line 6.
     * E. The program compiles, but throws an exception during execution.
     */

}
