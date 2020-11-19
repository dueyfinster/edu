package Huffman;

import java.io.*;

/**
 * @author ngrogan
 * Class to process a text file. Reads in each line one at time and builds our coding system for transfer into a Tree structure
 */
public class HuffmanFileReader {
	//Take the file as an argument to process
	String HuffmanFileReader(File LetterCount) {
		//Start a buffer
		StringBuffer contents = new StringBuffer();

		try { // To catch stack problems
			//Use the buffered reader to read in lines one at a time, so we can build our alphabet of codes
			BufferedReader input =  new BufferedReader(new FileReader(LetterCount));
			try { // to catch problems with lines
				String line = null;
				//while no strings are empty, keep reading from our textfile
				while (( line = input.readLine()) != null){
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			}finally{
				// Close the buffered reader when done
				input.close();
			}
		}
		catch (IOException ex){
			//catch problems filling our stack
			ex.printStackTrace();
		}
		//return our contents to string
		return contents.toString();
	}
}


