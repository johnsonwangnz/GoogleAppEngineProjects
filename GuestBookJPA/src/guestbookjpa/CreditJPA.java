package guestbookjpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class CreditJPA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	private String department;

	public CreditJPA(String department)
	{
		this.department = department;
	}
	
	// Accessors for the fields. JDO doesn't use these, but your application does.

	public Key getKey()
	{
		return key;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;

	}
}
