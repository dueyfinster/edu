package ie.ucd.cs.neilgrogan13204052.labs.io.readerswriters;

import java.io.*;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class FileBufferCopier {
    public static void main(String[] args) throws IOException {

        File inputFile = new File("scjp.txt");
        File outputFile = new File("scjpCopy.txt");

        // Buffered is more efficient and easier to work with
        // then plain FileReader/FileWriter

        BufferedReader in = new BufferedReader(new FileReader(inputFile));
        BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

        String line;

        while((line = in.readLine())!=null){
            out.write(line);
            out.newLine();
        }

        in.close();
        out.close();
    }
}
