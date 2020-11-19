package Lab3;

// NsLookup.java 

import java.net.*;  

public class NsLookup 
{
	public static void main(String argv[]) 
	{
		if (argv.length == 0) 
		{
		  System.out.println("Usage: java NsLookup <hostname>");
		  System.exit(0);
		}
		
		String host = argv[0]; 
		InetAddress address = null; 
		
		try 
		{
		  address = InetAddress.getByName(host); 
		} 
		catch(UnknownHostException e) 
		{
		   System.out.println("Unknown host"); 
		   System.exit(0); 
		}

		System.out.println(address.getHostAddress());		
  } 
}

