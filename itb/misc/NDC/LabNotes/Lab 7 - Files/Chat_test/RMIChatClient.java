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
import java.rmi.registry.*;

public class RMIChatClient implements Runnable, ActionListener {
  protected static final int UPDATE_DELAY = 10000;

  protected String host;
  protected Frame frame;
  protected TextField input;
  protected TextArea output;

  public RMIChatClient (String host) {
    this.host = host;

    frame = new Frame ("RMIChatClient [" + host + "]");
    frame.add (output = new TextArea (), "Center");
    output.setEditable (false);
    frame.add (input = new TextField (), "South");
    input.addActionListener (this);
    frame.addWindowListener (new WindowAdapter () {
      public void windowOpened (WindowEvent ev) {
        input.requestFocus ();
      }
      
      public void windowClosing (WindowEvent ev) {
        stop ();
      }
    });
    frame.pack ();
  }

  protected RMIChatServer server;
  protected Thread updater;

  public synchronized void start () throws RemoteException, NotBoundException {
    if (updater == null) {
      Registry registry = LocateRegistry.getRegistry (host);
      server = (RMIChatServer) registry.lookup (RMIChatServer.REGISTRY_NAME);
      updater = new Thread (this);
      updater.start ();
      frame.setVisible (true);
    }
  }

  public synchronized void stop () {
    if (updater != null) {
      updater.interrupt ();
      updater = null;
      server = null;
    }
    frame.setVisible (false);
  }

  public void run () {
    try {
      int index = 0;
      while (!Thread.interrupted ()) {
        String[] messages = server.getMessages (index);
        int n = messages.length;
        for (int i = 0; i < n; ++ i)
          output.append (messages[i] + "\n");
        index += n;
        Thread.sleep (UPDATE_DELAY);
      }
    } catch (InterruptedException ignored) {
    } catch (RemoteException ex) {
      input.setVisible (false);
      frame.validate ();
      ex.printStackTrace ();
    }
  }

  public void actionPerformed (ActionEvent ev) {
    try {
      RMIChatServer server = this.server;
      if (server != null)
        server.addMessage (ev.getActionCommand ());
      input.setText ("");
    } catch (RemoteException ex) {
      Thread tmp = updater;
      updater = null;
      if (tmp != null)
        tmp.interrupt ();
      input.setVisible (false);
      frame.validate ();
      ex.printStackTrace ();
    }
  }

  public static void main (String[] args) throws RemoteException, NotBoundException {
    if (args.length != 1)
      throw new IllegalArgumentException ("Syntax: RMIChatClient <host>");
    RMIChatClient chatClient = new RMIChatClient (args[0]);
    chatClient.start ();
  }
}
