package guestbookjpa;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



import com.google.appengine.api.datastore.Key;

import guestbookjpa.ContactInfoJPA;

@Entity
public class EmployeeJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;
	
	
	private String firstName;
	

	private String lastName;
	

	private Date hireDate;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ContactInfoJPA myContactInfo;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CreditJPA> credits;
	
	
	public EmployeeJPA(String firstName, String lastName, Date hireDate)
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

    public ContactInfoJPA getMyContactInfo() {
        return myContactInfo;
    }

    public void setMyContactInfo(ContactInfoJPA myContactInfo) {
        this.myContactInfo = myContactInfo;
    }

    public List<CreditJPA> getCredits() {
        return credits;
    }

    public void setCredits(List<CreditJPA> credits) {
        this.credits = credits;
    }
    
}
