package guestbook;


import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

import guestbook.ContactInfo;

@PersistenceCapable
public class Employee {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;
	
	@Persistent
	private Date hireDate;
	
	@Persistent
	private ContactInfo myContactInfo;
	
	
	public Employee(String firstName, String lastName, Date hireDate)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = hireDate;
	}
	
	// Accessors for the fields. JDO doesn't use these, but your application does.
	public Key getKey()
	{
		return key;
	}
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
		
	}
	
	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public ContactInfo getMyContactInfo() {
        return myContactInfo;
    }

    public void setMyContactInfo(ContactInfo myContactInfo) {
        this.myContactInfo = myContactInfo;
    }
}
