package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
public class USAddressFactory implements AddressFactory
{
    public Address createAddress()
    {
        return new USAddress();
    }
    
	public PhoneNumber createPhoneNumber()
	{
        return new USPhoneNumber();
    }
}
