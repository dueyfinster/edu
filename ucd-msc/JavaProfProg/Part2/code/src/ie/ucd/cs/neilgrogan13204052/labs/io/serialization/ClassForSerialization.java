package ie.ucd.cs.neilgrogan13204052.labs.io.serialization;

import java.io.*;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ClassForSerialization implements Serializable {
    int x=9;
    transient int y = 10;

    public static void main(String[] args) {
        try{
            FileOutputStream out = new FileOutputStream("objectStore.ser");
            ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(new ClassForSerialization());
            os.flush();

            FileInputStream in = new FileInputStream("objectStore.ser");
            ObjectInputStream is = new ObjectInputStream(in);

            ClassForSerialization cfs = (ClassForSerialization) is.readObject();
            System.out.println(cfs.x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
