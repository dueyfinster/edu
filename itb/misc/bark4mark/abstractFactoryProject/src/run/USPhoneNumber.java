package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
class USPhoneNumber extends PhoneNumber
{
	private String countryCode;
	private String stateCode;
	
	
	
	public String getStateCode() 
	{
		return stateCode;
	}
	
	public void setStateCode(String stateCode) 
	{
		//Test to see if number meets requirements if not output error to console
		if(stateCode.length() <= 3 && stateCode.length() > 0)
			this.stateCode=stateCode;
		else
			System.out.print("Number cannot be greater than 3 in length");
	}
	public String getLocalNumber() 
	{
		return localNumber;
	}
	public void setLocalNumber(String localNumber) 
	{
		//Test to see if number meets requirements if not output error to console
		if(localNumber.length()<=10&&localNumber.length()>0)
			this.localNumber = localNumber;
		else
			System.out.print("Number Cannot be greater than 10 in length");
		
	}
	public String getCountryCode() 
	{
		return countryCode;
	}
	public void setCountryCode(String countryCode) 
	{
		//Test to see if number meets requirements if not output error to console
		if(countryCode.length()<=3&&countryCode.length()>0)
			this.countryCode = countryCode;
		else
			System.out.print("Number Cannot be greater than 3 in length");
	}
	public String getNumber()
	{
		String fullIrishNumber;
		fullIrishNumber=countryCode+" "+stateCode+" "+localNumber;
		return fullIrishNumber;
	}
	
}