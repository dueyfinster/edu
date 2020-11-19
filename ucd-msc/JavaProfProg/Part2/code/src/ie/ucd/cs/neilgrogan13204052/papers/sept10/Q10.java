package ie.ucd.cs.neilgrogan13204052.papers.sept10;

import java.io.File;

/**
* Created by Neil Grogan on 26/02/15
*/
public class Q10 {

    public static void main(String[] args) {
        File file = new File("Cafe4Java.txt");
        // INSERT CODE HERE
        // INSERT CODE HERE
        /*w.write ("Cafe4Java", 0, 9);
        w.flush();
        w.close();
        System.out.println(new BufferedReader(new FileReader(file)).readLine());*/
    }

    /**
     * Examine the following code and select the correct option(s), which if
     * independently inserted at line 5 will compile/execute successfully and read/write
     * 'Cafe4Java' to file Cafe4Java.txt
     *
     * A. BufferedWriter w = new BufferedWriter (new FileWriter (new PrintWriter (file)));
     * B. BufferedWriter w = new BufferedWriter (new PrintWriter (new FileWriter (file)));
     * C. PrintWriter w = new PrintWriter (new FileWriter (new BufferedWriter (file)));
     * D. FileWriter w = new FileWriter (new BufferedWriter (new PrintWriter (file)));
     * E. PrintWriter w = new PrintWriter (new BufferedWriter (new FileWriter (file)));
     * F. FileWriter w = new FileWriter (new PrintWriter (new BufferedWriter (file)));
     */
}
