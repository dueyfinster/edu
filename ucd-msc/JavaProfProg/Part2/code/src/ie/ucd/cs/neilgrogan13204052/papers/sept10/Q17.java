package ie.ucd.cs.neilgrogan13204052.papers.sept10;

import java.util.Scanner;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner("hello 1 2.00 false");
        scanner.useDelimiter(" ");
        String str = scanner.next();
        int anInt = scanner.nextInt();
        float aFloat = scanner.nextFloat();
        boolean booleanValue = scanner.nextBoolean();
        System.out.println(str + ":" + anInt + ":" + aFloat + ":" +
            booleanValue);
    }

    /**
     * Given the following code, what is the expected result?
     *
     * A. The program will throw an InputMismatch exception at run-time
     * B. The program will produce no output
     * C. The program will output 'hello:1:2.0:false'
     * D. The program will output ':1:2.0:false'
     */

}
