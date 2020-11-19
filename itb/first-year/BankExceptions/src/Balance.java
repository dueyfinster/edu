/**
 * 
 */
package BankExceptions;

/**
 * @author ngrogan
 *
 */
public class Balance{
	private double balance = 1500.00;

	public Balance() {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = this.balance + balance;
	}
	
}	

