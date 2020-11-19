package BankExceptions;

public class InCorrectPinException extends Exception{
	


	InCorrectPinException(){
	    int pin = 1234;
	  }


	  InCorrectPinException(String err){
	    String pinError = err;  // save message
	  }

	
}
