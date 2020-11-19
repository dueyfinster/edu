package ie.ucd.cs.neilgrogan13204052.papers.dec11;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Q10 {

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("tester");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(-3);
            dos.writeDouble(1.0001);
            dos.close();
            fos.close();
        }
        catch (IOException e) { }
    }

    /**
     * How many 8-bit bytes does the following Java code successfully write to file tester?
     *
     * A. 4
     * B. 8
     * C. 12
     * D. The number of bytes written depends on the underlying system.
     * E. Compiler error, so no bytes written to the file.
     */
}
