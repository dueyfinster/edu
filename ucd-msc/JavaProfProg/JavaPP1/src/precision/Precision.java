package precision;

/**
 * Created by ngrogan on 18/06/2014.
 */
public class Precision {

    public static void main(String[] args){
        double f = 4.35;
        int n =  (int) (100 * f); // n will be 434 - why?
        System.out.println("n (before) is now: " + n);
        n = (int) Math.round(100*f); // n will be 435
        System.out.println("n (after) is now: " + n);
    }
}
