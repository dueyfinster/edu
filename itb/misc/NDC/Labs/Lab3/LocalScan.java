package Lab3;

import java.net.*;

public class LocalScan {
  public static void main(String [] args) {
   for (int i=1;i<1023;i++) {
     testPort(i);
     }
   System.out.println("Completed");
   }
  private static void testPort(int i) {
    try {
      ServerSocket sock=new ServerSocket(i);
      }
    catch (java.io.IOException e) {
      System.out.println("Port " + i + " in use.");
      }
    }
 }
