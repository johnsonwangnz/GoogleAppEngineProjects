package guestbookjpa;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




public final class EMF {
	
	private static final EntityManagerFactory 
		emfInstance = Persistence.createEntityManagerFactory("strong-reads-short-deadlines");
	
	private EMF(){}
	
	public static EntityManagerFactory get()
	{
		return emfInstance;
	}
	
	

}
