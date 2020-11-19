package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q2 {

    public static void main(String[] args) {
        try{
            System.out.println("In Try!");
            // no Exception
            // no System.exit()
        }finally{
            System.out.println("In Finally!");
        }
    }

    /**
     * True or False: a finally block will be executed if there is no exception thrown in the
     * corresponding try (assume no System.exit() statement is met).
     * A. True
     * B. False
     *
     */

}
