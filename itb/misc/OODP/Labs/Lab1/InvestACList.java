package Lab1;

import java.util.StringTokenizer;

public class InvestACList extends NumberList  {
	
	int size;
	String list1[];
	
	InvestACList(String list)  {
		
		size = 0;
		StringTokenizer token = new StringTokenizer(list);
		size = token.countTokens();
	
		String list1[] = new String[size];

		
		for(int i = 0; i < size; i++)  {
			list1[i] =  token.nextToken();
		}
	}
	
	public void display()
	{
		System.out.print("Invest List");
		for(int i = 0; i < size; i++)
			System.out.println("[" + i + "] =" + list1[i]);
	}
}


