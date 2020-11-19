package Lab6;

import java.rmi.*;
import java.net.*;

/**
 * @(#) ArithApp.java
 * @author Qusay H. Mahmoud
 */

public class ArithApp {

    // this method is not really needed.
    public static String localHost() throws Exception {
        InetAddress host = null;
	host = InetAddress.getLocalHost();
	System.out.println("host = "+host.getHostName());
        return host.getHostName();   
    }
    
    public static void main(String argv[]) {
	int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9};
	int b[] = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
	int result[] = new int[10];
	try {
           // replace hostname by the machine the server is running on
	   Arith obj = (Arith)Naming.lookup("//hostname/ArithServer");
	   result = obj.add(a, b);
	} catch (Exception e) {
	   System.out.println("ArithApp exception:"+e.getMessage());
	   e.printStackTrace();
	}
	System.out.println("The sum of the arrays is: ");
	for (int j=0; j<10; j++) {
	   System.out.print(result[j]+"    ");
	}
        System.out.println();
    }
}
