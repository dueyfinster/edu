package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

import java.io.IOException;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class Declare {
    public static void main(String[] args) {
        Declare d = new Declare();
        try{
            d.callingMethod();
        }catch(Exception e){
            {
                System.out.println(e);
            }
        }
    }

    private void callingMethod() throws IOException {
        calledMethod();
    }

    private void calledMethod() throws IOException {
        throw new IOException();
    }
}
