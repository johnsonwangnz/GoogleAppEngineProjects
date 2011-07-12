package guestbookjpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ContactInfoJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    
    private String streetAddress;

   
    private String city;

  
    private String stateOrProvince;

  
    private String zipCode;

    public ContactInfoJPA(String streetAddress, String city, String stateOrProvince, String zipCode)
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
