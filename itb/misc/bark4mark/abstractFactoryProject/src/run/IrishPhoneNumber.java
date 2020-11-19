package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
class IrishPhoneNumber extends PhoneNumber
{
	private String countryCode="353";
	
	public String getLocalCode() 
	{
		return localCode;
	}
	public void setLocalCode(String localCode) 
	{
		if (localCode.length() <= 4 && localCode.length() > 0)
			this.localCode = localCode;
		else
			System.out.print("Must be less than 4 numbers in length");
		}
	
	public String getLocalNumber() 
	{
		return localNumber;
	}
	
	public void setLocalNumber(String localNumber) 
	{
		if (localNumber.length() <= 8 && localNumber.length()>0)
			this.localNumber = localNumber;
		else
			System.out.print("Must be less than 8 numbers in length");
			
		
	}
	
	public String getNumber()
	{
		String fullIrishNumber;
		fullIrishNumber=countryCode+" "+localCode+" "+localNumber;
		return fullIrishNumber;
	}
}//End Class
