package ie.ucd.cs.neilgrogan13204052.labs.threads;

/**
 * Created by ngrogan on 06/04/2015.
 */
public class RunnableTest {

    public static void main(String[] args){
        RunCounter rct = new RunCounter();
        Thread th = new Thread(rct);
        th.start();
        System.out.println("Thread has been started!");
        RunCounter2 rct2 = new RunCounter2();
        Thread th2 = new Thread(rct2);
        th2.start();
        System.out.println("Thread 2 has been started!");
    }

    static class RunCounter extends Nothing implements Runnable{

        @Override
        public void run() {
            for(int i=1; i<=5; i++){
                System.out.println("Count " + i);
            }
        }
    }

    static class RunCounter2 extends Nothing implements Runnable{

        @Override
        public void run() {
            for(int i=5; i<=10; i++){
                System.out.println("Count " + i);
            }
        }
    }

    static class Nothing{

    }
}
