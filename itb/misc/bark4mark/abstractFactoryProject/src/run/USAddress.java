package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
class USAddress extends Address
{
	protected String country = "US";
	
    public String getCountry()
    { 
    	return country; 
    }
    
    public String getFullAddress()
    {
        return getBusinessName() + linebreak + getStreet() + space + getCity() +linebreak+getState() +linebreak+getZip()+ linebreak + country + linebreak;
    }
}