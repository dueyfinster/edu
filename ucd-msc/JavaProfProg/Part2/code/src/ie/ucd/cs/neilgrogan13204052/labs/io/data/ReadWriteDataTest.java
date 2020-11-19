package ie.ucd.cs.neilgrogan13204052.labs.io.data;

import java.io.*;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class ReadWriteDataTest {

    public static void main(String[] args) throws IOException {
        //writeFile();
        readFile();
    }

    public static void writeFile() throws IOException{
        String dataFile = "orders.txt";
        DataOutputStream out = new DataOutputStream(new FileOutputStream(dataFile));
        double[] priceList = {19.99, 29.99, 39.99};
        int[] copies = {100000, 5000, 70000};
        String[] titleList = {"SCJP Study Guide",
                "SCBCD Study Guide",
                "SCSA Study Guide"};
        // Write out to a file
        for(int i =0; i<priceList.length; i++)
        {
            out.writeDouble(priceList[i]);
            out.writeChar('\t');
            out.writeInt(copies[i]);
            out.writeChar('\t');
            out.writeChars(titleList[i]);
            out.writeChar('\n');

        }
        out.close();
    }

    public static void readFile() throws IOException{
        String dataFile = "orders.txt";
        DataInputStream out = new DataInputStream(new FileInputStream(dataFile));
        for(int i=0; i<3; i++){
            double d = out.readDouble();
            out.skipBytes(2);
            int x = out.readInt();
            out.skipBytes(2);
            String s = out.readLine();
            System.out.println(d + " " + x + " "+ s);
        }
    }
}

