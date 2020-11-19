import java.net.*;
import java.io.*;

public class AMTServer extends Thread {
    Socket csocket;
    AMTServer(Socket csocket) { this.csocket = csocket; }
    public static void main(String args[]) throws Exception {
      ServerSocket ssock=new ServerSocket(1234);
      while (true) {
        new AMTServer(ssock.accept()).start();
      }
    
    }

   public void run() {
// client processing code here
    }
  }
