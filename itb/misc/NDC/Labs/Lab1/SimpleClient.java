package Lab1;
import java.io.*;
import java.net.*;

public class SimpleClient {    
  public final static int REMOTE_PORT = 5000;
  public static void main(String argv[]) throws Exception {

    Socket cl = null, cl2=null;
    BufferedReader is = null;
    DataOutputStream os = null;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    String userInput = null;
    String output = null;

    // Open connection to the server on port REMOTE_PORT	
    try {
      cl = new Socket("192.168.1.1",REMOTE_PORT);
      is = new BufferedReader(new InputStreamReader(cl.getInputStream()));
      os = new DataOutputStream(cl.getOutputStream());
    } catch(UnknownHostException e1) {
       System.out.println("Unknown Host: "+e1);
    } catch (IOException e2) {
       System.out.println("Erorr io: "+e2);
    }

    // write the url to the compute engine
    try {	    
      System.out.print("Please input a keyword: ");
      userInput = stdin.readLine();
      os.writeBytes(userInput+"\n");
    } catch (IOException ex) {
       System.out.println("error writing to server..."+ex);
    }
	
    // receive results from the compute engine
    try {
      output = is.readLine();
      System.out.println("Got from server: "+output);
    } catch(IOException e) {
       e.printStackTrace();
    }

    // close input stream, output stream and connection
    try {
      is.close();
      os.close();
      cl.close();
    } catch (IOException x) {
       System.out.println("Error writing...."+x);
    }
  }
}
