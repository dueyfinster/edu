package Lab3;

public class PrintSpoolerTest {
	public static void main(String[] args){
		Excercise1();
		Excercise2();
	}

	private static void Excercise1() {
		// This will create a new instance of PrintSpooler
		PrintSpooler prntspl1 = PrintSpooler.getPrintSpooler();
		prntspl1.printPrinterList();
		
		// This shows that 2 different variables are the same instance
		PrintSpooler prntspl2 = PrintSpooler.getPrintSpooler();
		
		if(prntspl1 == prntspl2) {
			System.out.println("\n PrintSpooler 1 and 2 are the same \n");
		}else{
			System.out.println("\n PrintSpooler 1 and 2 are not the same! \n");
		}
	}
	
	private static void Excercise2() {
		try {
			// This will create a new instance of PrintSpooler
			PrintSpooler1 ps1 = PrintSpooler1.getPrintSpooler();
			ps1.printPrinterList();
			ps1.printAvailablePrinterList();
		} catch(SingletonException se) {
			System.out.println("Singleton Exception occurred");
		}
	}
}
