package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q6 {

    public static void main(String[] args) {
        String s1 = null;
        String s2 = "TrUe";
        String s3 = "yes";
        String s4 = "no"; // not printed
        Boolean b1 = new Boolean("tRuE");
        boolean b2 = false;
        System.out.printf("%b %b %b %b %b", s1, s2, s3, b1, b2, s4);
    }

    /**
     * Given the following code, what is the expected result?
     *
     * A. false true false true false
     * B. false true true true false
     * C. false true true true false false
     * D. An exception is thrown at runtime.
     */

}
