package ie.ucd.cs.neilgrogan13204052.labs.exceptions.assertions;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class AssertionExample {

    public static void main(String[] args) {
        int x = 15;
        DataAccess da = new DataAccess();
        assert da.dataIsAccessible(): "Data is not acceptable";
        System.out.println("Value of x: " + x);

    }

    static class DataAccess{

        public boolean dataIsAccessible() {
            return false;
        }
    }
}
