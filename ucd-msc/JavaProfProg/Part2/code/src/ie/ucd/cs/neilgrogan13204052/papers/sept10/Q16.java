package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q16 {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("0123456789");
        if (s.length() == 10)
            s.insert(10, "abcdef");
        s.delete(3,9);
        System.out.println(s.indexOf("c"));
    }

    /**
     * Consider the following code, What is the output?
     *
     * A. 7
     * B. 6
     * C. 5
     * D. -1
     */

}
