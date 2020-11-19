package Lab3;

import java.io.*;

public class PrintSpooler1{
	// This will be the one and only PrintSpooler instance
	private static PrintSpooler1 ps1;
	private Printer[] printer = new Printer[5];
	
	private PrintSpooler1()
	{	                                               
		boolean free = true;
		
		for(int i=0; i<printer.length; i++) {
			int printerno = (int)(Math.random()*100);
			
			if(i%2==0 && printerno>10){
				free = true;
			} else {
				free = false;
			}
			
			printer[i] = new Printer("Printer " +(i+1), "Office No." + printerno, free);
		}
	}

	// Public synchronized method which will return a PrintSpooler
	public static synchronized PrintSpooler1 getPrintSpooler() throws SingletonException
	{
		// If true then we need to create an instance of PrintSpooler
		if (ps1 == null){
			ps1 = new PrintSpooler1();
		}
		return ps1;
	}

	// Test method so we can ensure that our object works
	public void printPrinterList()
	{
		System.out.println("All Printers:");
		System.out.println("=============");
			for(int i=0; i<printer.length; i++) {
				System.out.println(printer[i].getName() +" - " + printer[i].getLoc() +" - " + printer[i].getAvail());
			}
		
	}
	
	public void printAvailablePrinterList()
	{
		System.out.println("\n Available Printers:");
		System.out.println("====================");
		for(int i=0; i<printer.length; i++) {

			if(printer[i].getAvail() == true ) {
				System.out.println(printer[i].getName() +" - " +printer[i].getLoc() +" - " + printer[i].getAvail());
			}
		}
		
	}
}
