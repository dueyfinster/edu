package BankExceptions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


public class Listeners implements ActionListener{
	Balance bal = new Balance();
	double currentbalance = bal.getBalance();
	double newbal = 30.00;
	
	public void actionPerformed(ActionEvent e) {
		String result = e.getActionCommand();
		String check_balance = "ChkBal";
		String credit_account = "CredAC";
		String debit_account = "DebAC";
		String check_pin = "ChkPin";
		String exit = "Exit";
		
		BankExceptions b = new BankExceptions();
		
			if (result.equals(check_balance)){
				
				Balance bal = new Balance();
					b.jft1(Double.toString(bal.getBalance()));
					b.jft1.revalidate();
					b.jft1.repaint();
			}else if (result.equals(credit_account)){ 
				
				String text = b.jtf1();
				
				
				 try {
			          newbal = Double.valueOf(text.trim()).doubleValue();
			         System.out.println("New Value = " + newbal);
			         try
				      {
				    	  checkBalAdd();   // this method throws SpellException
				      }
				      catch (UserCashLimitExcessException uclee)   // but it is caught here
				      {
				        System.out.println("The Error is: " + uclee.getError());
				      }

				  } catch (NumberFormatException nfe) {
			         System.out.println("Number Error: " + nfe.getMessage());
			      } 
			      
				b.jtf1.revalidate();
				b.jtf1.repaint();
			
			} else if (result.equals(debit_account)){ 
			
			} else if (result.equals(check_pin)){ 
	
			} else if(result.equals(exit)){
				String message = "You clicked Exit!"; 
			JOptionPane.showMessageDialog(null, message, "Bye!", JOptionPane.ERROR_MESSAGE); System.exit(0);
			} 
			//System.out.println(e.toString());
			//System.out.println(e());

		}
	
	  public void checkBalAdd() throws UserCashLimitExcessException{
	    if (currentbalance + newbal < 3000.00){
	    	bal.setBalance(newbal);
	    	System.out.println("OK");
	    }else{
	      throw new UserCashLimitExcessException("Account Limit Cannot Exceed 3000");
	    }
	   }
	  
	  public void checkBalDeb() throws UserExcessDebtException{
		    if (currentbalance + newbal > -1000.00){
		    	bal.setBalance(newbal);
		    	System.out.println("OK");
		    }else{
		      throw new UserExcessDebtException("Account Limit Cannot Exceed -1000");
		    }
		   }

}
	