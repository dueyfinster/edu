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

public interface RMIChatServer extends Remote {
  public static final String REGISTRY_NAME = "Chat Server";
  public abstract String[] getMessages (int index) throws RemoteException;
  public abstract void addMessage (String message) throws RemoteException;
}
