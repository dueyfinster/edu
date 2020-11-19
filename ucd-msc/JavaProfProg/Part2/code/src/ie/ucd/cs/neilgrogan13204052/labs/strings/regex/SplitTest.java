package ie.ucd.cs.neilgrogan13204052.labs.strings.regex;

import java.util.regex.Pattern;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class SplitTest {

    public static void main(String[] args) {
        String input = "www.cs.cornell.edu";
        //Pattern p = Pattern.compile("\\.");
        Pattern p = Pattern.compile("c");
        String[] pieces = p.split(input);
        for (String piece : pieces){
            System.out.println(piece);
        }
    }
}
