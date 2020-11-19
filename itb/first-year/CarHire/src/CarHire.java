/**
 * Import Java Libraries
 */

/**
 * @author neilgrogan
 * 
 */
class CarHire {
	  private String make;
	  private String model;
	  private int year;
	  private String registration;
	  private int days_hire;
	  private double cost;

	/**
	 * Default Constructor
	 */
	public CarHire() {
		   make  = "Nissan";
		   model = "Micra";
		   year = 1996;
		   registration = "99-KE-54321";
		   days_hire = 3;
		   cost = 40.00;
	}
	
	/**
	 * User-defined Constructor
	 */
	public CarHire(String mak, String mod, int yr, String reg, int dh, double cst) {
		   make   = mak;
		   model  = mod;
		   year = yr;
		   registration = reg;
		   days_hire = dh;
		   cost = cst;
	}
	/**
	 * Display Methods
	 */
	// Display One Instance (Modifier)
	  public void displayOne(){
	    System.out.println("\nMake:		" + make);
	    System.out.println("Model:		" + model);
	    System.out.println("Year:		" + year);
	    System.out.println("Registration:  	" + registration);
	    System.out.println("Days Hired:  	" + days_hire);
	    System.out.println("Cost:		" + cost);
	  }
	  
	  // Display All
	  public void displayAll(){
		 System.out.println("|" + make	+ " : " + model + " : " + year	+ " : " + registration + " : " + days_hire + cost + "|");
	  }


	  
	/**
	 * GET / SET Access/Modifier methods
	 */
	
	 public String getMake(){
			return make;
		 }
	 public void setMake(String mak){
			 make = mak;
	 }

	 public String getModel(){
			return model;
	 }
	 public void setModel(String mod){
			 model = mod;
	 }
	 public int getYear(){
				return year;
	 }
	 public void setYear(int yr){
				 year = yr;
	 }
	 public String getRegistration(){
				return registration;
	 }
	 public void setRegistration(String reg){
				registration = reg;
	 }
	 public int getDays_Hire(){
				return days_hire;
	 }
	 public void setDays_Hire(int dh){
				 days_hire = dh;
	 }
	 public double getCost(){
			return cost;
	 }
	 public void setCost(double cst){
			 cost = cst;
	 }
	 
	 /**
	  * Extra methods
	  */
	 
	 
	 
}// END OF CLASS
