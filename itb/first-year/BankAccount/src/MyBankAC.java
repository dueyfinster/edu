class MyBankAC
{
	public static void main(String[]args)
	{
		// Create 2 of object variables
		// of type BankAC
		BankAC ac1 = new BankAC();
		BankAC ac2 = new BankAC("Neil Grogan", 7000, "B00021325");
		BankAC ac3 = new BankAC();
		BankAC ac4 = new BankAC();


        ac1.display();
        ac2.display();
        ac3.display();
        ac4.display();


	ac1.Lodgement(500);
	System.out.println("Account 1 after Lodgement: " + ac1.getBalance());

	ac1.Withdrawal(500);
	System.out.println("Account 1 after Withdrawal: " + ac1.getBalance());
	}
}