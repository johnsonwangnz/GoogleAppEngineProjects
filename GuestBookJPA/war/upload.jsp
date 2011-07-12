<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>    
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>    

<% BlobstoreService service = BlobstoreServiceFactory.getBlobstoreService(); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <form action="<%= service.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="fileinfo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
</body>
</html>