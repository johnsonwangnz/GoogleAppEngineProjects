<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="javax.persistence.EntityTransaction"%>
<%@ page import="javax.persistence.Query"%>

<%@ page import="guestbookjpa.EMF"%>
<%@ page import="guestbookjpa.EmployeeJPA"%>
<%@ page import="guestbookjpa.ContactInfoJPA"%>
<%@ page import="guestbookjpa.CreditJPA"%>

<%@ page import="com.google.appengine.api.NamespaceManager"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	// to display namespace
	String currentNamespace = NamespaceManager.get();
	if (currentNamespace!=null)
	{
		%> <p>Current Namespace :<%= currentNamespace %> </p> <% 
	}

	String firstName=request.getParameter("firstname");


	if(firstName !=null)
	{
		// to create this employee
		EntityManager  em = EMF.get().createEntityManager();
		
		
		EmployeeJPA employee = new EmployeeJPA(firstName,"Smith",new Date());
		
		// to creat contact
		
		ContactInfoJPA  contactInfo = new ContactInfoJPA("31 Heath Street"+ " "+firstName,
				"Chch", "Canterbury", "8053");
		
		employee.setMyContactInfo(contactInfo);
		
		// to create credits
		List<CreditJPA> credits = new ArrayList<CreditJPA>();
		
		credits.add(new CreditJPA("Sales"));
		credits.add(new CreditJPA("Development"));
		
		employee.setCredits(credits);
		
		EntityTransaction tx = em.getTransaction();
		
		try
		{
			
			tx.begin();
					
			em.persist(employee);
			
			tx.commit();
			
			
		}
		finally 
		{
			if(tx.isActive())
			{
				tx.rollback();		
			}
			em.close();
		}
		
	}
	else
	{
		// query to list data
		
		EntityManager  em = EMF.get().createEntityManager();
		
		Query query = em.createQuery("select from " +EmployeeJPA.class.getName() 
				+" where lastName = :lastNameParam")
				.setParameter("lastNameParam","Smith");
				
				
		
		try
		{
			List<EmployeeJPA> results =  (List<EmployeeJPA>)query.getResultList(); 
			if(!results.isEmpty())
			{
				for (EmployeeJPA e : results)
				{
					%>
						<p>key : <%= e.getKey().toString() %></p>
						<p>First Name : <%= e.getFirstName() %></p>
						<p>Last Name : <%= e.getLastName() %></p>
						<p>Hire Date : <%= e.getHireDate() %></p>
					<%
					
					ContactInfoJPA contactInfo = e.getMyContactInfo();
					if (contactInfo!=null)
					{
						%>
						<p>Contact key : <%= contactInfo.getKey().toString() %></p>
						<p>Street Address : <%=  contactInfo.getStreetAddress() %></p>
						<p>State or Province : <%= contactInfo.getStateOrProvince() %></p>
						<p>City : <%= contactInfo.getCity() %></p>
						<p>Zip Code : <%= contactInfo.getZipCode() %></p>
					   <%
						
					}
					else
					{
						%> <p>No contact info found</p> <% 
					}
					
					List<CreditJPA> credits = e.getCredits();
					if (credits!=null && credits.size()>0)
					{
						for (CreditJPA credit : credits)
						{
						%>
						<p>Credit key : <%= credit.getKey().toString() %></p>
						<p>Department : <%=  credit.getDepartment() %></p>
					   <%
						}
						
					}
					else
					{
						%> <p>No credits found</p> <% 
					}
				} 
			} else {
			 %> <p>No employees found</p> <% 

			}
		}
		finally
		{
			em.close();
		}
		
	}
%>

<form action="/employee1.jsp" method="post">
<div>First Name <input type="text" name="firstname"
	value="Johnson"></div>
<div><input type="submit" value="New Employee" /></div>
</form>

</body>
</html>