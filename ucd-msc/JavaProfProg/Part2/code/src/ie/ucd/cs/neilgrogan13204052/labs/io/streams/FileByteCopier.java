package ie.ucd.cs.neilgrogan13204052.labs.io.streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class FileByteCopier {

    public static void main(String[] args) throws IOException{
        File inputFile = new File("scjp.txt");
        System.out.println(inputFile.getAbsolutePath());
        File outputFile = new File("scjpCopy.txt");
        FileInputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        int c;
        while((c= in.read())!= -1) {
            out.write(c);
        }
        in.close();
        out.close();
    }
}
