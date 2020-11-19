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

public interface RMICallbackServer extends Remote {
  public static final String REGISTRY_NAME = "Callback Server";
  public abstract void register (RMICallbackClient client) throws RemoteException;
  public abstract void deregister (RMICallbackClient client) throws RemoteException;
  public abstract void say (String message) throws RemoteException;
}
