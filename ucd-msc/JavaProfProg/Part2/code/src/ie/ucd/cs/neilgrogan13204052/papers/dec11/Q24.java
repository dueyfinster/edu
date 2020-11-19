package ie.ucd.cs.neilgrogan13204052.papers.dec11;

public class Q24 {

    //synchronized int i;
    synchronized void go() {
        Q24 s = new Q24();
        synchronized(this) { }
        synchronized(s) { }
    }

    public static void main(String[] args) {
        // code here
    }

    /**
     * Given the code: Which line will cause a compiler error? (Choose one)
     * A. line 2
     * B. line 3
     * C. line 5
     * D. line 6
     */

}
