package Lab1;

import java.util.StringTokenizer;

public class HexList extends NumberList  {
	
	int size;
	
	HexList(String list)  {
		
		size = 0;
		StringTokenizer token = new StringTokenizer(list);
		size = token.countTokens();
	
		
		hexList = new int[size];

		
		for(int i = 0; i < size; i++)  {
			hexList[i] =  Integer.parseInt(token.nextToken(), 16);
		}
	}
	
	public void display()
	{
		System.out.print("Integer List");
		for(int i = 0; i < size; i++)
			System.out.println("[" + i + "] = " + "0x" + intList[i]);
	}
}


