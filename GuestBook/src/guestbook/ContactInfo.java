package guestbook;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ContactInfo {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String streetAddress;

    @Persistent
    private String city;

    @Persistent
    private String stateOrProvince;

    @Persistent
    private String zipCode;

    public ContactInfo(String streetAddress, String city, String stateOrProvince, String zipCode)
    {
    	this.streetAddress = streetAddress;
    	this.city = city;
    	this.stateOrProvince = stateOrProvince;
    	this.zipCode = zipCode;
    }
    
    public Key getKey ()
    {
    	return key;
    }
    
    public String getStreetAddress()
    {
    	return streetAddress;
    
    }
    
    public void setStreetAddress(String streetAddress)
    {
    	this.streetAddress = streetAddress;
    }
    
    
    public String getCity()
    {
    	return city;
    
    }
    
    public void setCity(String city)
    {
    	this.city = city;
    }
    
    public String getStateOrProvince()
    {
    	return stateOrProvince;
    
    }
    
    public void setStateOrProvince(String stateOrProvince)
    {
    	this.stateOrProvince = stateOrProvince;
    }
    
    public String getZipCode()
    {
    	return zipCode;
    
    }
    
    public void setZipCode(String zipCode)
    {
    	this.zipCode = zipCode;
    }
        
}
