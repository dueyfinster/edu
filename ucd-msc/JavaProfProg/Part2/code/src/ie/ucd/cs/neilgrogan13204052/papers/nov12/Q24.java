package ie.ucd.cs.neilgrogan13204052.papers.nov12;

public class Q24 {
    int i;
    synchronized void go() { //3
        Q24 s = new Q24();
        synchronized(this) { } //5
        synchronized(s) { } //6
    }

    public static void main(String[] args) {
        // code here
    }

    /**
     * Given the code, Which line will cause a compilation error? (Choose one)
     *
     * A. line 3
     * B. line 5
     * C. line 6
     * D. None of them – compilation succeeds.
     */
}
