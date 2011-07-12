<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="guestbook.PMF"%>
<%@ page import="guestbook.Employee"%>
<%@ page import="java.util.Date"%>
<%@ page import="guestbook.ContactInfo"%>
<%@ page import="javax.jdo.Transaction"%>
<%@ page import="javax.jdo.Query"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String firstName=request.getParameter("firstname");


	if(firstName !=null)
	{
		// to create this employee
		PersistenceManager pm = PMF.get().getPersistenceManager();		
		
		Employee employee = new Employee(firstName,"Smith",new Date());
		
		// to creat contact
		
		ContactInfo  contactInfo = new ContactInfo("31 Heath Street"+ " "+firstName,
				"Chch", "Canterbury", "8053");
		
		employee.setMyContactInfo(contactInfo);
		Transaction tx = pm.currentTransaction();
		
		try
		{
			
			tx.begin();
					
			pm.makePersistent(employee);
			
			tx.commit();
			
			
		}
		finally 
		{
			if(tx.isActive())
			{
				tx.rollback();		
			}
			pm.close();
		}
		
	}
	else
	{
		// query to list data
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Query query = pm.newQuery(Employee.class);
		
		query.setFilter("lastName == lastNameParam");
		
		query.setOrdering("hireDate desc");
		
		query.declareParameters("String lastNameParam");
		
		try
		{
			List<Employee> results =  (List<Employee>)query.execute("Smith"); 
			if(!results.isEmpty())
			{
				for (Employee e : results)
				{
					%>
						<p>key : <%= e.getKey().toString() %></p>
						<p>First Name : <%= e.getFirstName() %></p>
						<p>Last Name : <%= e.getLastName() %></p>
						<p>Hire Date : <%= e.getHireDate() %></p>
					<%
					
					ContactInfo contactInfo = e.getMyContactInfo();
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
					
				} 
			} else {
			 %> <p>No employees found</p> <% 

			}
		}
		finally
		{
			query.closeAll();
		}
		
	}
%>

<form action="/employee.jsp" method="post">
<div>First Name <input type="text" name="firstname"
	value="Johnson"></div>
<div><input type="submit" value="New Employee" /></div>
</form>

</body>
</html>