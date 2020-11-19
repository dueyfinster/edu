package ie.ucd.cs.neilgrogan13204052.papers.sept10;

public class Q20 extends Thread implements Runnable {

    public void run(){ System.out.print("hi "); }

    public static void main(String[] args) {
        Thread t1 = new Q20();
        Thread t2 = new Thread(t1);
        t1.run();
        t2.run();
        t1.run();
    }

    /**
     * Given the following code: What is the result?
     *
     * A. Prints 'hi '
     * B. Prints 'hi hi '
     * C. Prints 'hi hi hi '
     * D. Compilation fails.
     * E. An exception is thrown at runtime.
     */

}
