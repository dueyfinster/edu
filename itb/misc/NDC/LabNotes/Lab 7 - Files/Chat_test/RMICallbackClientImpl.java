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

import java.awt.*;
import java.rmi.*;
import java.awt.event.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class RMICallbackClientImpl extends UnicastRemoteObject implements RMICallbackClient, ActionListener {
  protected String host;
  protected Frame frame;
  protected TextField input;
  protected TextArea output;

  public RMICallbackClientImpl (String host) throws RemoteException {
    this.host = host;
    frame = new Frame ("RMICallbackClientImpl [" + host + "]");
    frame.add (output = new TextArea (), "Center");
    output.setEditable (false);
    frame.add (input = new TextField (), "South");
    input.addActionListener (this);
    frame.addWindowListener (new WindowAdapter () {
      public void windowOpened (WindowEvent ev) {
        input.requestFocus ();
      }

      public void windowClosing (WindowEvent ev) {
        try {
          stop ();
        } catch (RemoteException ex) {
          ex.printStackTrace ();
        }
      }
    });
    frame.pack ();
  }

  protected RMICallbackServer server;

  public synchronized void start () throws RemoteException, NotBoundException {
    if (server == null) {
      Registry registry = LocateRegistry.getRegistry (host);
      server = (RMICallbackServer) registry.lookup
        (RMICallbackServer.REGISTRY_NAME);
      server.register (this);
      frame.setVisible (true);
    }
  }

  public synchronized void stop () throws RemoteException {
    frame.setVisible (false);
    RMICallbackServer server = this.server;
    this.server = null;
    if (server != null)
      server.deregister (this);
  }

  public void said (String message) {
    output.append (message + "\n");
  }

  public void actionPerformed (ActionEvent ev) {
    try {
      RMICallbackServer server = this.server;
      if (server != null) {
        server.say (ev.getActionCommand ());
        input.setText ("");
      }
    } catch (RemoteException ex) {
      input.setVisible (false);
      frame.validate ();
      ex.printStackTrace ();
    }
  }

  public static void main (String[] args) throws RemoteException, NotBoundException {
    if (args.length != 1)
      throw new IllegalArgumentException
        ("Syntax: RMICallbackClientImpl <host>");
    RMICallbackClientImpl callbackClient =
      new RMICallbackClientImpl (args[0]);
    callbackClient.start ();
  }
}
