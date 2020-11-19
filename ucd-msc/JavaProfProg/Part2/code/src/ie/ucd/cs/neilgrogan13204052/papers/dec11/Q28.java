package ie.ucd.cs.neilgrogan13204052.papers.dec11;

import java.util.ArrayList;
import java.util.Iterator;

public class Q28 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        Iterator<Integer> itr = list.iterator();
        for(Integer j:list){
            System.out.println("number: " + j);
        }
    }

    /**
     * What is the output from this code fragment?
     *
     * A. Compiler error at line 4.
     * B. Compiler error at line 5.
     * C. number: 1
     *    number: 2
     * D. Runtime error.
     */

}
