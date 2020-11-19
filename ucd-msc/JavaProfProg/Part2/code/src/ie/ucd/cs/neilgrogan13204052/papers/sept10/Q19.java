package ie.ucd.cs.neilgrogan13204052.papers.sept10;

public class Q19 extends Thread {

    public static void main(String[] args) {
        Q19 t = new Q19();
        t.run();
        t.start();
    }

    public void run() {
        System.out.print("go ");
    }

    /**
     * Given that t is a reference to a valid Thread object, with valid run() method for t:
     * What is the result?
     *
     * A. Prints 'go '
     * B. Prints 'go go '
     * C. Compilation fails.
     * D. An exception is thrown at runtime.
     */

}
