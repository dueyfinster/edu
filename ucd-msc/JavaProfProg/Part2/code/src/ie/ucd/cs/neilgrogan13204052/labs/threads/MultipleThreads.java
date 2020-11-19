package ie.ucd.cs.neilgrogan13204052.labs.threads;

/**
 * Created by ngrogan on 06/04/2015.
 */
public class MultipleThreads {

    public static void main(String[] args){
        System.out.println("The main thread of execution has started!");

        RunCounter rct1 = new RunCounter("First Thread");
        RunCounter rct2 = new RunCounter("Second Thread");
        RunCounter rct3 = new RunCounter("Third Thread");
    }

    static class RunCounter implements Runnable{
        Thread myThread;

        RunCounter(String name){
            myThread = new Thread(this, name);
            myThread.start();
        }

        @Override
        public void run() {
            for(int i=1; i<=5; i++){
                System.out.println("Thread: " + myThread.getName() + " Count: " + i);
            }
        }
    }
}
