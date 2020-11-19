package Lab3;

class PrintSpooler
{
	// This will be the one and only PrintSpooler instance
	private static PrintSpooler printSpooler;
	private Printer[] printer = new Printer[5];
	
	private PrintSpooler()
	{	
		for(int i=0; i<printer.length; i++) {
			printer[i] = new Printer("Printer " +(i+1), "Office No." +(i+1), true);
		}
	}

	// Public synchronized method which will return a PrintSpooler
	public static synchronized PrintSpooler getPrintSpooler()
	{
		// If true then we need to create an instance of PrintSpooler
		if (printSpooler == null)
			printSpooler = new PrintSpooler();

		return printSpooler;
	}

	// Test method so we can ensure that our object works
	public void printPrinterList()
	{
		System.out.print("Name - Location - Queue Free? \n");
		System.out.println("==========================");
		for(int i=0; i<printer.length; i++) {
			System.out.println(printer[i].getName() + " - " + printer[i].getLoc() + " - " + printer[i].getAvail());
		}
	}
	
	public void printAvailPrinterList()
		{
			System.out.println("\nAvailable Printers:\n");
			
			for(int i=0; i<printer.length; i++) {

				if(printer[i].getAvail() == true ) {
					System.out.println(printer[i].getName() +"  |  " +printer[i].getLoc() +"  |  " + printer[i].getAvail());
				}
			}
		
	}
}
