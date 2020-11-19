package ie.ucd.cs.neilgrogan13204052.papers.dec11;

public class Q5 {

    public static void main(String[] args) {
        final int x  =0;
        if ( x > 10) {
            throw new IndexOutOfBoundsException("The value of index x=" + x + " is out of bound!" );
        }
    }

    /**
     * Assume that the variable x is already properly declared, has some value, and is in scope. Which
     * one of the following code fragments is the most appropriate way of throwing an exception?
     *
     * A.
         if ( x > 10) {
         throws new IndexOutOfBoundsException("Index is out of bound!");
         }
     * B.
         if ( x > 10) {
         throw new IndexOutOfBoundsException("The value of index x=" + x + " is out of bound!" );
         }
     * C.
         IndexOutOfBoundsException iob = new IndexOutOfBoundsException("Index out of bound!");
         if ( x > 10) {
         throw iob;
         }
     * D.
         if ( x > 10) {
         throw "Index is out of bound!";
         }
     */

}
