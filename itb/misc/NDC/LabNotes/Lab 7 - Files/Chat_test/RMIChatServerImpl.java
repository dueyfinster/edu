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

public class RMIChatServerImpl extends UnicastRemoteObject implements RMIChatServer {
  protected Vector messages;

  public RMIChatServerImpl () throws RemoteException {
    messages = new Vector ();
  }

  public String[] getMessages (int index) {
    int size = messages.size ();
    String[] update = new String[size - index];
    for (int i = 0; i < size - index; ++ i)
      update[i] = (String) messages.elementAt (index + i);
    return update;
  }

  public void addMessage (String message) {
    messages.addElement (message);
  }

  public static void main (String[] args) throws RemoteException {
    RMIChatServerImpl chatServer = new RMIChatServerImpl ();
    Registry registry = LocateRegistry.getRegistry ();
    registry.rebind (REGISTRY_NAME, chatServer);
  }
}
