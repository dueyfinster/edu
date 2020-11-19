package ie.ucd.cs.neilgrogan13204052.labs.strings;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class StringAndBuffer {
    public static void main(String[] args) {
        String s1 = "String Literal!";
        String sn = new String("String new");
        StringBuffer sb = new StringBuffer("String Buffer");
        s1.concat(" Ya!");
        sn.concat(" Ya!");
        sb.append(" Ya!");
        System.out.println("s1 after concat(): " + s1);
        System.out.println("sn after concat(): " + sn);
        System.out.println("sb after append(): " + sb);
    }
}
