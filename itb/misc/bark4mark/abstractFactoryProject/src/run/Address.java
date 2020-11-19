package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
public abstract class Address
{
	private String businessName;
    private String street;
    private String city;
    private String county;
    private String region;
    private String postalCode;
    private String state;
    private String zip;
    
	
	protected String space = " ";
	protected char linebreak = '\n';
	
    
    //Getters
	public String getZip() 
	{
		return zip;
	}
	public String getState() 
	{
		return state;
	}
	public String getBusinessName()
	{
		return businessName;
	}
    public String getStreet()
    { 
    	return street; 
    }
    
    public String getCity()
    { 
    	return city; 
    }
    
    public String getCounty()
    {
    	return county;
    }
    public String getPostalCode()
    { 
    	return postalCode; 
    }
    
    public String getRegion()
    { 
    	return region; 
    }
    
    public abstract String getCountry();
    
    public String getFullAddress()
    {
        return null;//street + linebreak +
            //city + space + postalCode + linebreak;
    }
    //End of Getters
    
    //Setters
    public void setZip(String newZip) 
    {
		zip = newZip;
	}
    public void setState(String newState) 
    {
		state = newState;
	}
    public void setBusinessName(String newBusinessName)
    {
    	businessName = newBusinessName;
    }
    public void setStreet(String newStreet)
    { 
    	street = newStreet; 
    }
    
    public void setCity(String newCity)
    { 
    	city = newCity; 
    }
    
    public void setRegion(String newRegion)
    { 
    	region = newRegion; 
    }
    
    public void setPostalCode(String newPostalCode)
    { 
    	postalCode = newPostalCode; 
    }
    //End of Setters
	public void setCounty(String newCounty) {
		county = newCounty;
		
	}
}
