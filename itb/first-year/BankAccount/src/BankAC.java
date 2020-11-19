class BankAC{
  // Attributes
  private String name;
  private double balance;
  private String accountno;

 //****** Methods********

// Constructor Method
BankAC(){   // Default Constructor
	name  = "My Account";
	balance = 1200;
	accountno = "B058672988";
 }

BankAC(String n, double bal, String acno){   // User defined Constructor
	name   = n;
	balance = bal;
	accountno = acno;
 }



public void display(){
	System.out.println("\n\n\nName          : " + name);
	System.out.println("Balance       : " + balance);
	System.out.println("Account Number: " + accountno);
}

public void debit(char ans){
	System.out.println("Would you like to make a withdrawal? (Y/N)");
	ans = Keyboard.readChar();
}

public void credit(char ans){
	System.out.println("Would you like to make a lodgement? (Y/N)");
	ans = Keyboard.readChar();
}

 // Access / Modifier Methods
 // Get     /  Set Methods
 public String getName(){
	return name;
 }
 public void setName(String n){
	 name = n;
 }

 public double getBalance(){
	return balance;
 }
 public void setBalance(double bal){
	 balance = bal;
 }
 public String getAccountno(){
	return accountno;
 }
 public void setAccountno(String acno){
	 accountno = acno;
 }

 ///////////////////////////////////////////////////////
 // Extra
 ///////////////////////////////////////////////////////
  public void Lodgement(double credit){
 	 balance = balance + credit;
 }

  public void Withdrawal(double debit){
  	 balance = balance - debit;
 }

}