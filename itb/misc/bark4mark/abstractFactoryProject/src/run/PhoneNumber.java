package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
public abstract class PhoneNumber
{
    protected String countryCode;
    protected String localCode;
    protected String localNumber;
    protected String stateCode;
    
	public String getCountryCode() 
	{
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getLocalCode() 
	{
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getLocalNumber() {
		return localNumber;
	}
	public void setLocalNumber(String localNumber) {
		this.localNumber = localNumber;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getNumber()
	{
		return "0";//Gets Overridden
	}
    
    
}
