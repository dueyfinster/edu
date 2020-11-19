package ie.ucd.cs.neilgrogan13204052.papers.nov12;

public class Q16 {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("0123456789");
        if (s.length() == 10)
            s.insert(10, "abcdef");
        s.delete(3,8);
        System.out.println(s.indexOf("b"));
    }

    /**
     * Consider the following code, What is the output?
     *
     * A. 7
     * B. 6
     * C. 5
     * D. -1
     */
}
