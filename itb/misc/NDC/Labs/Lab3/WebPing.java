package Lab3;

import java.net.*;
import java.io.*;

public class WebPing 
{
	public static void main(String[] args) 
	{
		try 
		{
		  InetAddress addr;
		  Socket sock=new Socket(args[0],80);
		  addr=sock.getInetAddress();
		  System.out.println("Connected to " + addr);
		  sock.close();
		}
		catch (java.io.IOException e) 
		{
		  System.out.println("Can't connect to " + args[0]);
		  System.out.println(e);
		}
    }
}
