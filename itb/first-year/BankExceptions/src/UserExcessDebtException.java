/**
 * In the Package BankExceptions; where the program is located
 */
package BankExceptions;

/**
 * Start of Excess Debt Class - extends the Java Exception Class
 */
class UserExcessDebtException extends Exception{
  String exceptionerror;
  
/**
 * Default Constructor - sets the Error as unknown if no error message is passed
 */
  public UserExcessDebtException(){
	  exceptionerror = "Unknown Error";
  }
  

  /**
   * User Defined Constructor - sets the Error as a String that is passed
   */
  public UserExcessDebtException(String error)
  {
    exceptionerror = error;  // save message
  }
  

  /**
   * Return the Error to where the exception is thrown
   */
  public String getError()
  {
    return exceptionerror;
  }
}
  
