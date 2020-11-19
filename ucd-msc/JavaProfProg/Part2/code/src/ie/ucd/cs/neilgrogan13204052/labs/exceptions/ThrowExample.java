package ie.ucd.cs.neilgrogan13204052.labs.exceptions;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ThrowExample {

    public static void main(String[] args) {
        double x = 15.0;
        double y = 0.0;
        try{
            ThrowExample te = new ThrowExample();
            double z = te.doubleDivide(x,y);
            System.out.println("x/y: " + x/y);
        }catch(ArithmeticException ae){
            System.out.println("An Exception occurred " + ae);
        }
    }

    double doubleDivide(double x, double y){
        if(y==0.0){
            //throw new ArithmeticException("Integer or not, please do not divide by zero!");
            throw new RuntimeException("This is a more general exception");
        }else{
            return x/y;
        }
    }
}
