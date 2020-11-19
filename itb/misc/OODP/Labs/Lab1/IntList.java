package Lab1;

import java.util.StringTokenizer;

public class IntList extends NumberList  {
	
	int size;
	
	IntList(String list)  {
		
		size = 0;
		StringTokenizer token = new StringTokenizer(list);
		size = token.countTokens();
	
		
		intList = new int[size];
		
		
		for(int i = 0; i < size; i++)  {
			intList[i] = Integer.parseInt(token.nextToken());
		}
	}
	
	public Number sum()
	{
		int n = 0;

		for(int i = 0; i < size; i++)
		{
		   n = n + intList[i];
		}
		return new Integer(n);
	}

	public void display()
	{
		System.out.print("Integer List");
		for(int i = 0; i < size; i++)
			System.out.println("[" + i + "] = " + intList[i]);
	}
}


