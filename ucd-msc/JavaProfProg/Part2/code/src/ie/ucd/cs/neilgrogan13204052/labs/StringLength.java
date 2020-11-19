package ie.ucd.cs.neilgrogan13204052.labs;

/**
 * Created by ngrogan on 20/04/2015.
 */
public class StringLength {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("0123456789");
        if(s.length() ==10)
            s.insert(10, "abcdef");
        s.delete(3,8);
        System.out.println(s.indexOf("b"));
    }
}
