package Lab1;
import java.io.*;
import java.net.*;

public class SimpleServer {
  public final static int TESTPORT = 5000;
  public static void main(String args[]) {

    // declare a server socket and a client socket for the server
    // declare an input and an output stream
    ServerSocket checkServer = null;
    String line;
    BufferedReader is = null;
    DataOutputStream os = null;
    Socket clientSocket = null;
           
    // Try to open a server socket on port TESTPORT
    try {
       checkServer = new ServerSocket(TESTPORT);
       System.out.println("SimpleServer started.....");
    } catch (IOException e) {
       System.out.println(e);
    }   

    // Create a socket object from the ServerSocket to listen and accept connections.
    // Open input and output streams
    try {
       clientSocket = checkServer.accept();
       is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
       os = new DataOutputStream(clientSocket.getOutputStream());
    } catch(Exception ei) {
       ei.printStackTrace();
    }

    // receive client's input
    try {
      line=is.readLine();
      System.out.println("we received: "+line);
      if (line.compareTo("Greetings") == 0) {
	os.writeBytes("...and saluation...");
      } else {
	 os.writeBytes("sorry, you don't speak my protocol");
      }
    } catch (IOException e) {
       System.out.println(e);
    }

    // close the i/o streams and the connection
    try {
       os.close();
       is.close();
       clientSocket.close();
    } catch(IOException ic) {
       ic.printStackTrace();
    }
  }
}
