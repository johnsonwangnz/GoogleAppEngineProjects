<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.MalformedURLException"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 

String message= URLEncoder.encode("My message","UTF-8");

try
{
	URL url = new URL("http://www.cleancode.co.nz/feed"); 
	
	HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
	// POST out example
	
	/*
	connection.setDoOutput(true);
	
	connection.setRequestMethod("POST");
	
	OutputStreamWriter writer = new OutputStreamWriter (connection.getOutputStream());
	
	writer.write("message="+message);
	
	writer.close();
	
	if(connection.getResponseCode()== HttpURLConnection.HTTP_OK)
	{
	
	}
	else
	{
		
	}
		
	
	*/
	connection.setRequestMethod("GET");
	
	 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
     String line;

     while ((line = reader.readLine()) != null) {
        
    	
    			%>
    			<p>  
    			
    			<%= line %> </p>
    			<%
    	
    	
    			
     }
     reader.close();

}
catch (MalformedURLException ex)
{
	%>
	<p> call is failed and error message is <%= ex.getMessage() %></p>
	<%
	}
catch (IOException ex)
{
	%>
	<p> call is failed and error message is <%= ex.getMessage() %></p>
	<%
	}


%>
</body>
</html>