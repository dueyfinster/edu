package ie.ucd.cs.neilgrogan13204052.labs.threads;

/**
 * Created by ngrogan on 06/04/2015.
 */
public class ThreadTest {

    public static void main(String[] args){
        Counter ct = new Counter();
        ct.start();
        System.out.println("The thread has been started");
        Counter2 ct2 = new Counter2();
        ct2.start();
        System.out.println("The thread has been started");
    }

    static class Counter extends Thread{
        public void run(){
            for(int i=1; i<=5; i++){
                System.out.println("Count " + i);
            }
        }
    }

    static class Counter2 extends Thread{
        public void run(){
            for(int i=5; i<=10; i++){
                System.out.println("Count " + i);
            }
        }
    }
}
