package ie.ucd.cs.neilgrogan13204052.labs.collections;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class SearchCollectionTest {
    public static void main(String[] args) {
        String[] str = {"Mark","Ready","Set","Go"};
        System.out.println("Unsorted: ");
        for(String s: str) System.out.println(s + " ");
        Arrays.sort(str);
        System.out.println("Sorted in natural order: ");
        for(String s: str) System.out.println(s + " ");
        System.out.println("\nGo = " + Arrays.binarySearch(str, "Go"));
        System.out.println("Sorted in reverse order using a Comparator: ");
        MyReverseSorter ms = new MyReverseSorter();
        Arrays.sort(str, ms);
        for(String s: str) System.out.println(s + " ");
        System.out.println("\nGo = " + Arrays.binarySearch(str, "Go"));
        System.out.println("Go = " + Arrays.binarySearch(str, "Go", ms));

    }

    static class MyReverseSorter implements Comparator<String>{
        public int compare(String s1, String s2){
            return s2.compareTo(s1);
        }
    }
}
