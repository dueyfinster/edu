package Lab1;
import java.io.*;
public class FileToConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String currentDir = System.getProperty("user.dir");
		//Get the current working directory
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(currentDir+"\\Labs\\Lab1\\text.txt"));
			String str;
			while((str=in.readLine())!=null)
		{
			process(str);
		}
			in.close();
		}
		catch (IOException e)
		{
		e.printStackTrace();
		}
	}

	private static void process(String str) 
	{
		System.out.println(str);
	}

}
