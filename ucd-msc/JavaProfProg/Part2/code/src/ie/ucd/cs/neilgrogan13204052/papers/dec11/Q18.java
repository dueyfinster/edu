package ie.ucd.cs.neilgrogan13204052.papers.dec11;

import java.util.Set;
import java.util.TreeSet;

public class Q18 {

    public static void main(String[] args) {
        Set<Q18> set = new TreeSet<Q18>(); // line 5
        set.add(new Q18()); // line 6
        set.add(new Q18()); //line 7
    }

    /**
     * What happens when you try to compile and run the following application?
     *
     * A. Compiler error.
     * B. An exception is thrown at line 5.
     * C. An exception is thrown at line 6.
     * D. An exception is thrown at line 7.
     * E. No exception is thrown.
     */
}
