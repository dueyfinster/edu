package ie.ucd.cs.neilgrogan13204052.labs.collections.autoboxing;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class AutoBoxingTest {
    public static void main(String[] args) {
        Integer wi1 = 1;
        wi1++;
        Integer wi2 = 2;
        if(wi1 == wi2){
            System.out.println("Area: " + areaOfASquare(4.0d));
        }
    }

    private static Double areaOfASquare(Double side) {
        return side*side;
    }
}
