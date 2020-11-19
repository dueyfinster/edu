package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

import java.io.IOException;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class CheckedExceptions {

    public static void main(String[] args) {
        try {
            new CheckedExceptions().calledMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void callingMethod(){
        try {
            calledMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calledMethod() throws IOException {
        throw new IOException("Bad things happened!");
    }
}
