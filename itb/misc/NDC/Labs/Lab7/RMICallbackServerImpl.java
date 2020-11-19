package Lab7;

/*
 * Java Network Programming, Second Edition
 * Merlin Hughes, Michael Shoffner, Derek Hamner
 * Manning Publications Company; ISBN 188477749X
 *
 * http://nitric.com/jnp/
 *
 * Copyright (c) 1997-1999 Merlin Hughes, Michael Shoffner, Derek Hamner;
 * all rights reserved; see license.txt for details.
 */

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class RMICallbackServerImpl extends UnicastRemoteObject implements RMICallbackServer {
  protected Vector clients;

  public RMICallbackServerImpl () throws RemoteException {
    clients = new Vector ();
  }

  public void register (RMICallbackClient client) {
    try {
      say (getClientHost () + " has joined.");
    } catch (ServerNotActiveException ignored) {
    }
    clients.addElement (client);
  }

  public void deregister (RMICallbackClient client) {
    clients.removeElement (client);
    try {
      say (getClientHost () + " has left.");
    } catch (ServerNotActiveException ignored) {
    }
  }

  public void say (String message) {
    Vector clients = (Vector) this.clients.clone ();
    for (int i = 0; i < clients.size (); ++ i) {
      RMICallbackClient client = (RMICallbackClient) clients.elementAt (i);
      try {
        client.said (message);
      } catch (RemoteException ex) {
        this.clients.removeElement (client);
      }
    }
  }

  public static void main (String[] args) throws RemoteException {
    RMICallbackServerImpl callbackServer = new RMICallbackServerImpl ();
    Registry registry = LocateRegistry.getRegistry ();
    registry.rebind (REGISTRY_NAME, callbackServer);
  }
}
