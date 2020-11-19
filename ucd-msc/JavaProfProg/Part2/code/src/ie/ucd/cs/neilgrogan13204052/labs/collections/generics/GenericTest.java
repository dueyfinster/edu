package ie.ucd.cs.neilgrogan13204052.labs.collections.generics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class GenericTest {

    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<String>();
        String st1 = "ready";
        String st2 = "set";
        String st3 = "go";
        myList.add(st1);
        myList.add(st2);
        myList.add(st3);
        String st;
        Iterator<String> itr = myList.iterator();
        while(itr.hasNext()){
            st = itr.next();
            System.out.println(st);
        }
    }
}
