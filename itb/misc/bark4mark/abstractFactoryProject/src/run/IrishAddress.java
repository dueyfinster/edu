package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
class IrishAddress extends Address
{
	protected String country = "Ireland";
	
	
    public String getCountry()
    { 
    	return country; 
    }
    
    public String getFullAddress()
    {
        return getBusinessName() +linebreak+ getStreet() + linebreak + getCity() + space + getCounty() + linebreak + country + linebreak;
    }
}
