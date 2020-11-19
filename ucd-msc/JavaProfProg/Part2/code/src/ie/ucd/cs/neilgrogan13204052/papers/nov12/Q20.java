package ie.ucd.cs.neilgrogan13204052.papers.nov12;

public class Q20 extends Thread {

    public void run() {
        System.out.print("go ");
    }

    public static void main(String[] args) {
        Q20 t = new Q20();
        t.run();
        t.run();
        t.start();
    }

    /**
     * Given that t is a reference to a valid Thread object, with a valid run() method for t
     * What is the result?
     *
     * A. Output is go
     * B. Output is go go
     * C. Output is go go go
     * D. Compilation fails.
     * E. An exception is thrown at runtime.
     */

}
