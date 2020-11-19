package ie.ucd.cs.neilgrogan13204052.papers.dec11;

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
     * A. Prints 'hi hi '
     * B. Prints 'hi hi hi '
     * C. Compiler error.
     *D. Compiles ok, but an exception is thrown at runtime.
     */
}
