package ie.ucd.cs.neilgrogan13204052.papers.sept10;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Neil Grogan on 26/02/15
 */
public class Q18 {

    public static void main(String[] args) {
        List<Box> boxes = Arrays.asList(new Box(10), new Box(-1), new Box(5),
            new Box(2));
        Collections.sort(boxes, new BoxComparator());
        for (Box box : boxes) {
            System.out.print((box).getSize() + " ");
        }
    }
}

    class Box {
        public int size;

        public Box(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    class BoxComparator implements Comparator<Box> {
        public int compare(Box one, Box two) {
            if (one.getSize() == two.getSize()) {
                return 0;
            } else if (one.getSize() < two.getSize()) {
                return -1;
            }
            return 1;
        }
    }

    /**
     * Given the following code, what is the expected result?
     *
     * A. The program will throw a ClassCastException at run-time.
     * B. The program will compile and produce the output '10 -1 5 2 '.
     * C. The program will compile and produce the output '-1 2 5 10 '.
     * D. The program will compile and produce the output '10 5 2 -1 '.
     */

