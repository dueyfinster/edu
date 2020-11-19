package ie.ucd.cs.neilgrogan13204052.papers.sept10;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q3 {

    public static void main(String[] args) {
        int i=1, j=1;
        try {
            i++;
            j--;
            if(i == j)
                i++;
        }
        catch(ArithmeticException e) {
            System.out.println(0);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(1);
        }
        catch(Exception e) {
            System.out.println(2);
        }
        finally {
            System.out.println(3);
        }
        System.out.println(4);
    }

    /**
     * Which of the following is part of the output? (Choose all that apply)
     * A. 0
     * B. 1
     * C. 2
     * D. 4
     * E. none of the above
     */
}
