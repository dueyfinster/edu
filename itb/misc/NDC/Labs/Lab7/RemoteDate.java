package Lab7;

//package darwinsys.distdate;

import java.rmi.*;
import java.util.Date;

/** A statement of what the client & server must agree upon. */
public interface RemoteDate extends java.rmi.Remote {

	/** The method used to get the current date on the remote */
	public Date getRemoteDate() throws java.rmi.RemoteException;

	/** The name used in the RMI registry service. */
	//public final static String LOOKUPNAME = "rmi://172.16.5.2/RemoteDate";
	public final static String LOOKUPNAME = "rmi://localhost/RemoteDate";
}
