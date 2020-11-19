package Lab6;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * @(#) Server.java
 * @author Qusay H. Mahmoud
 */


public class ArithImpl extends UnicastRemoteObject implements Arith {
    private String name;

    public ArithImpl(String s) throws RemoteException {
	super();
	name = s;
    }

    public int[] add(int a[], int b[]) throws RemoteException {
	int c[] = new int[10];
	for (int i=0; i<10; i++) {
	   c[i] = a[i] + b[i];
	}  
        return c;
    }

    public static void main(String argv[]) {
	System.setSecurityManager(new RMISecurityManager());

	try {
	   ArithImpl obj = new ArithImpl("ArithServer");
	   Naming.rebind("//localhost/ArithServer", obj);
	   System.out.println("ArithServer bound in registry");
	} catch (Exception e) {
	   System.out.println("ArithImpl err: " + e.getMessage());
	   e.printStackTrace();
	}
    }
}
