package ie.ucd.cs.neilgrogan13204052.papers.sept10;

public class Q24 {
    int i;

    synchronized void go() {
        Q24 s = new Q24();
        synchronized(this) { }
        synchronized(s) { }
    }

    public static void main(String[] args) {
        // code here
    }

    /**
     * Which line will cause a compilation error? (Choose one)
     * A. line 3
     * B. line 4
     * C. line 6
     * D. none of them – compilation succeeds
     */
}
