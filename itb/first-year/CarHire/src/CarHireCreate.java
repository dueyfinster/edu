/**
 * Import Java Libraries
 */
import java.util.*;
import java.io.*;

/**
 * @author neilgrogan
 *
 */
class CarHireCreate {
	/**
	 * Main
	 */
	public static void main(String[] args) {
		Vector v = new Vector();
		readBookings(v);
		Menu(v);
	}
	/**
	 * Read Text File
	 */
	static void readBookings(Vector v) {
	    String inputLine = new String();
	    try {
	      File inFile = new File("carbookings.txt");
	      BufferedReader br = new BufferedReader(new InputStreamReader(
	          new FileInputStream(inFile)));
	      while ((inputLine = br.readLine()) != null) {
	      	System.out.println(inputLine.trim());
	        v.addElement(inputLine.trim());
	      }
	      br.close();
	    } // Try
	    catch (FileNotFoundException ex) {
	    	System.out.print("Sorry that file is not found!");
	    } catch (IOException ex) {
	    	System.out.print("Input/Output Error!");
	    }
	  }


	/**
	 * Menu Method
	 */
	public static void Menu(Vector v){
		char menu;
		System.out.println("============================================================================================");
		System.out.println("| A. Hire a car | B. Delete a transaction | C. Modify | D. Display all | E. Save | F. Quit |");
		System.out.println("============================================================================================");
		System.out.println("\nYour choice: ");
		menu = Keyboard.readChar();
		menu = Character.toLowerCase(menu);
			switch(menu){
			case 'a': addBooking(v); break;
			case 'b': removeBookings(v); break;
			case 'c': modifyBookings(v); break;
			case 'd': displayallBookings(v); break;
			case 'e': saveBookings(v); break;
			case 'f': Quit(v); break;
			default: Menu(v);break;
			}
	}
	/**
	 * Add Bookings
	 */
	static void addBooking(Vector v) {
		System.out.print("How many cars would you like to enter? : ");
		int choice = Keyboard.readInt();

	      for(int i =0; i<choice; i++){
	  		CarHire c = new CarHire(); // Create a new Object each time around as 'c' using the Car class in a seperate file
	  		System.out.println("\nCar Number " + i);

	  	 	System.out.print("Set Make: "); c.setMake(Keyboard.readString());

	  	 	System.out.print("Set Model: "); c.setModel(Keyboard.readString());

	  	 	System.out.print("Set Year: ");c.setYear(Keyboard.readInt());

	  	 	System.out.print("Set Registration: ");
	  	 	c.setRegistration(Keyboard.readString());

	  	 	System.out.print("Set Days Hired for: ");
	  	 	c.setDays_Hire(Keyboard.readInt());

	  	 	v.add((Object) c); // Add Object to the Vector
	      }
	      Menu(v);
	}

	/**
	 * Remove Bookings
	 */
	static void removeBookings(Vector v) {
		Menu(v);
	}

	/**
	 * Modify Bookings
	 */
	static void modifyBookings(Vector v){
		Menu(v);
	}
	/**
	 * Display Bookings
	 */
		static void displayallBookings(Vector v) {
			System.out.println("==================================================");
			System.out.println("|Make"	+ " : " + "Model" + " : " + "Year"	+ " : " + "Reg No" + " : " + "Days Hired" + " : " + "Cost|");
			System.out.println("--------------------------------------------------");
			for(int i=0;i<v.size();i++){
			   Object o = v.elementAt(i);
			   CarHire c = (CarHire) o;
			   c.displayAll();
			 }
			System.out.println("--------------------------------------------------");
			System.out.println("|Make"	+ " : " + "Model" + " : " + "Year"	+ " : " + "Reg No" + " : " + "Days Hired" + " : " + "Cost|");
			System.out.println("==================================================");
			Menu(v);
		} // end pf Displaying Bookings

	/**
	 * Save Bookings
	 */
	static void saveBookings(Vector v){
		for(int i=0; i<v.size(); i++){ // data.size is the size of the Vector
			CarHire c = new CarHire(); 			  // Make new Object each time around
			c.setCost(10.00);
			c = (CarHire) v.elementAt(i);  // Display the Car Object thats stored in the Vector at element/position (i)
			String carline = new String(); // Public string
			try{
				FileWriter fw = new FileWriter("carbookings.txt");  // open the file for writing
				PrintWriter pw = new PrintWriter(fw);

				pw.println(carline);

				fw.close();
			}catch (IOException e) {System.out.print("Non existant file");}
		} // end of For
	}

	/**
	 * Quit the Program
	 */
	static void Quit(Vector v){
		char QuitAns;
 		System.out.println("Save file before quitting or else all changes are lost! \n\nReally Quit? Are You sure? (Y/N):"); // seek user input for rerun
 		QuitAns = Keyboard.readChar();
 		QuitAns = Character.toLowerCase(QuitAns);
		if(QuitAns=='n') {
			Menu(v);
		}
		else if (QuitAns=='y') {
		System.exit(0);
		}
	}

}// END OF PROGRAM
