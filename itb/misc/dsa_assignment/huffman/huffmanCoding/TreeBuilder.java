package huffmanCoding;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import dataStructures.BinaryTree;
import dataStructures.ListIndexOutOfBoundsException;
import dataStructures.ListReferenceBased;
import dataStructures.TreeIterator;

public class TreeBuilder 
{
	/***
	 * This method reads the frequency and symbols from a textfile huffman.txt, the values are saved
	 * in a list
	 * @param f list object passed by reference to be filled with contents from the file.
	 */
	public static void reader(ListReferenceBased f)
	{
		try 
		{
			/***
			 * Create filereader object
			 */
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/huffman.txt");
			/***
			 * Buffer the input stream
			 */
			BufferedInputStream bis = new BufferedInputStream(fis);
			/***
			 * Pass the buffered input stream to a datastream object
			 */
			DataInputStream dis = new DataInputStream(bis);
			
			/***
			 * dis.available returns null when there are no more lines
			 */
		      try 
		      {
		    	int c=1;
				while (dis.available()!=0)
				  
				{
					//System.out.println("Starting string tokenizer");
					  StringTokenizer st = new StringTokenizer(dis.readLine());
					  /***
					   * Read the first symbol in the text file save in a temp variable called symbol,
					   * read the second variable parse it as an integer and save in a temp variable called
					   * freq
					   */
					  

					  String symbol = st.nextToken();
					  //System.out.println("Symbol = "+symbol);
					  int freq = Integer.parseInt(st.nextToken());
					  //System.out.println("Freq = "+freq);
					  f.add(c, new HuffmanSymbol(freq, symbol));
					  /***
					   * Apply these two values to the list.
					   */
					  
					  c++;
					  //System.out.println(c);
					  
				}
				/***
				 * Now I should have a referenced based list containing the values from the textfile
				 */
				
			} 
		      catch (ListIndexOutOfBoundsException e) {
				System.out.print("There has been a problem with the list index...");
				e.printStackTrace();
			} 
		      catch (IOException e) {
		    	  System.out.print("There has been a problem with the file...");
				e.printStackTrace();
			}
		} 
		
		catch (FileNotFoundException e) 
		{
			/***
			 * If the file is not found or there is an error reading the file.
			 */
			System.out.println("File Error");
			e.printStackTrace();
		}
	}
	/***
	 * Builds the full tree from a list of binarytree roots.
	 * @param b takes the list of roots as an argument
	 */
	public static void builder(ListReferenceBased b)
	{
		
		/***
		 * Remove the first item off the list
		 */
		BinaryTree binarytree1 = (BinaryTree) b.get(1);
		b.remove(1);
		/***
		 * Remove the next item off the list
		 */
		BinaryTree binarytree2 = (BinaryTree) b.get(1);
		b.remove(1);
		
		/***
		 * Use temporary variables to get the frequency values
		 */
		HuffmanSymbol huffman1 = (HuffmanSymbol) binarytree1.getRootItem();
		HuffmanSymbol huffman2 = (HuffmanSymbol) binarytree2.getRootItem();
		
		/***
		 * New Root item, this root contains the frequency values of smallest two items on the list.
		 */
		BinaryTree binarytree = new BinaryTree(new HuffmanSymbol(huffman1.getFreq()+huffman2.getFreq(), "*" ));
		/***
		 * Figure out wether to place the subtree left or right depending on values (Largest number
		 * goes on the right).
		 */
		if(huffman1.getFreq()>huffman2.getFreq())
		{
			binarytree.attachRightSubtree(binarytree1);
			binarytree.attachLeftSubtree(binarytree2);
		}
		
		else if(huffman1.getFreq()<huffman2.getFreq())
		{
			binarytree.attachLeftSubtree(binarytree1);
			binarytree.attachRightSubtree(binarytree2);
		}
		
		/***
		 * Add the Binarytree back to the list
		 */
		b.add(1, binarytree);
		/***
		 * Add the sort method here!!!
		 */
		sort(b);
		
	}
	public static void sort(ListReferenceBased b)
	{
		/***
		 * Integral part to this whole thing, need to bubble sort this list by frequency each time
		 * builder is called.
		 */
		for(int i=1;i<b.size();i++)
		{
			BinaryTree binarytree1 = (BinaryTree) b.get(i);
			HuffmanSymbol huffman1 = (HuffmanSymbol) binarytree1.getRootItem();
			int freq1 = huffman1.getFreq();
			
			for(int j=i;i<b.size();i++)
			{
				BinaryTree binarytree2 = (BinaryTree) b.get(j);
				HuffmanSymbol huffman2 = (HuffmanSymbol) binarytree2.getRootItem();
				int freq2 = huffman2.getFreq();
				if(freq1>freq2)
				{
					b.remove(i);
					b.add(i, binarytree1);
				}
			}
		}
	}

}
