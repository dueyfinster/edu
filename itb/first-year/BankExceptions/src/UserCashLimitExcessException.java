package BankExceptions;

class UserCashLimitExcessException extends Exception{
  String mistake;

//----------------------------------------------
// Default constructor - initializes instance variable to unknown

  public UserCashLimitExcessException(){
    mistake = "unknown";
  }
  

//-----------------------------------------------
// Constructor receives some kind of message that is saved in an instance variable.

  public UserCashLimitExcessException(String err){
    super(err);     // call super class constructor
    mistake = err;  // save message
  }
  

//------------------------------------------------  
// public method, callable by exception catcher. It returns the error message.

  public String getError()
  {
    return mistake;
  }
}
