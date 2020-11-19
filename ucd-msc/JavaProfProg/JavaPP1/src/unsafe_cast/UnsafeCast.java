package unsafe_cast;

/**
 * Created by ngrogan on 18/06/2014.
 */
public class UnsafeCast {
    public static void main(String[] args){
        Base b = new Base();
        Sub s = (Sub) b;
    }
}
