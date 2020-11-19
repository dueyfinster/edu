package huffmanCoding;

import dataStructures.BinaryTree;
import dataStructures.ListReferenceBased;
import dataStructures.TreeIterator;

public class TreeBuilderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String symbol = "a";
		ListReferenceBased huffList = new ListReferenceBased();
		ListReferenceBased binList = new ListReferenceBased();
		/***
		 * Read file a generate list of huffmanSymbols
		 */
		TreeBuilder.reader(huffList);
		
		while(!huffList.isEmpty())
		{
			HuffmanSymbol tempHuff = (HuffmanSymbol) huffList.get(1);
			huffList.remove(1);
			binList.add(1, new BinaryTree(tempHuff));
		}
		
		while(binList.size()>1)
		TreeBuilder.builder(binList);
		
		//System.out.println(binList.size());
		
		BinaryTree finalTree = (BinaryTree) binList.get(1);
		
		TreeIterator treeIt = new TreeIterator(finalTree);
		
		treeIt.setPostorder();
		
		int count = 0;
		
		while(treeIt.hasNext())
		{
			HuffmanSymbol tempHuff = (HuffmanSymbol) treeIt.next();
			
			//System.out.println(tempHuff.getSymbol());
			
			if(symbol.equalsIgnoreCase(tempHuff.getSymbol()))
			{
				System.out.println("Found Symbol in "+count+" steps!");
			}
			count++;
			System.out.println(count);
		}
		
	}
	

}
