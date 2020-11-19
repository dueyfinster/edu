package ie.ucd.cs.neilgrogan13204052.papers.sept10;

public class Q23 {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        int i = o.hashCode();
        o.notify();
        o.wait();
        o.notifyAll();
    }

    /**
     * Which of the following are methods of the Object class? (Choose all that apply)
     *
     * A. wakeup()
     * B. sleep()
     * C. run()
     * D. wait()
     * E. notify()
     */
}
