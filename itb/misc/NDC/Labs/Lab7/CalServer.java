package Lab7;

import java.rmi.Naming;

public class CalServer {

  public CalServer() {
    try {
      Cal c = new CalImpl();

 	System.out.println("List of bindings in remote registry");
      String[] names = Naming.list("//127.0.0.1");
      for (int i=0; i<names.length; i++)
      	{
			System.out.println(names[i]);
		}


 	System.out.println("DateServer starting...");

    //Naming.rebind("//localhost/CalculatorService", c);
    Naming.rebind("rmi://127.0.0.1:1099/CalServer", c);

    } catch (Exception e) {
      System.out.println("Trouble: " + e);
    }
  }

  public static void main(String args[]) {
    new CalServer();
  }
}

