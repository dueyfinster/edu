package ie.ucd.cs.neilgrogan13204052.papers.sept10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Q29 implements Serializable {
    int x=9;
    transient int y=10;
    public static void main (String[] args){
        try{
            FileOutputStream out = new FileOutputStream("objectStore.ser");
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(new Q29());
            os.flush();
            FileInputStream in = new FileInputStream("objectStore.ser");
            ObjectInputStream is = new ObjectInputStream(in);
            Q29 cfs = (Q29)is.readObject();
            System.out.println(cfs.x);
        }catch(Exception e){ System.out.println(e); }
    }

    /**
     * Given the following code: What is the output when this code is compiled and run?
     *
     * A. 9
     * B. 10
     * C. No output – compiler error
     * D. No output – runtime exception
     */
    }
