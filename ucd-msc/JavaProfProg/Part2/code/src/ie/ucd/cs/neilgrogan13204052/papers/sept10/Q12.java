package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q12 {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = new String ("abc");
        if(s1 == s2)
            System.out.println(1);
        else
            System.out.println(2);
        if(s1.equals(s2))
            System.out.println(3);
        else
            System.out.println(4);
    }

    /**
     * What is output when this code is compiled & run? Select the two correct answers.
     *
     * A. 1
     * B. 2
     * C. 3
     * D. 4
     */
}
