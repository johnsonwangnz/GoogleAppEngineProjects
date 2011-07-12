<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="guestbookjpa.EMF"%>    
<%@ page import="guestbookjpa.EmployeeJPA"%>     
<%@ page import="java.util.Date"%>
    
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
		EntityManager em = EMF.get().createEntityManager();
		
		EmployeeJPA employee = new EmployeeJPA(firstName,"Smith",new Date());
		
		try
		{
			em.persist(employee);
		
			
		}
		finally 
		{
			em.close();
		}
		
	}else
	{
		// to create this employee
		//PersistenceManager pm = PMF.get().getPersistenceManager();
	

	}
%>

<form action="/employee.jsp" method="post">
      <div>First Name <input type="text"  name="firstname" value="Johnson" ></div>
      <div><input type="submit" value="New Employee" /></div>
</form>


</body>
</html>