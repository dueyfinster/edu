package Lab5;

/* GetName.java  */

import java.net.*;

public class GetName {
  public static void main(String argv[]) throws Exception {
    
	InetAddress host = null;
    
	host = InetAddress.getLocalHost();
    
	System.out.println(host.getHostName()); //name of our local machine
	
  }
}
