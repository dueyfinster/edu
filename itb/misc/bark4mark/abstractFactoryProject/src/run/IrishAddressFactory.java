package run;
/*
 * 
 * Title: OODP Assignment 1
 * Student Number: B00017310
 * Name: Mark Howard
 * Due Date: 12/11/09
 * 
 */
public class IrishAddressFactory implements AddressFactory
{
    public Address createAddress()
    {
        return new IrishAddress();
    }
    
    public PhoneNumber createPhoneNumber()
    {
        return new IrishPhoneNumber();
    }
}
