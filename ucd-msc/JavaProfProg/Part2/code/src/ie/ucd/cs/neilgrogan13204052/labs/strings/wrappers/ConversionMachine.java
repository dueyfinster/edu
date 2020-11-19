package ie.ucd.cs.neilgrogan13204052.labs.strings.wrappers;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ConversionMachine {
    public static void main(String[] args) {
        int b =4;
        Integer wbyte = new Integer(b);
        double d = 354.56d;
        Double wdouble = new Double(d);
        System.out.println("wrapped inside Byte: " + b);
        System.out.println("double value extracted from Byte: " + wbyte.doubleValue());
        System.out.println("wrapped inside Double: " + d);
        System.out.println("byte value extracted from Double: " + wdouble.byteValue());

    }
}
