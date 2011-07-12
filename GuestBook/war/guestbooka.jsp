<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
<title>Insert title here</title>
</head>
<body>
<%
	String guestbookName = request.getParameter("guestbookName");
	if (guestbookName == null) {
		guestbookName = "default";

	}
	UserService userService = UserServiceFactory.getUserService();
	User user = userService.getCurrentUser();

	if (user != null) {
%>
<p>Hello, <%=user.getNickname()%>!(You can <a
	href="<%=userService.createLogoutURL(request.getRequestURI())%>">sign
out</a>.)</p>
<%
	} else {
%>
<p>Hello! <a
	href="<%=userService.createLoginURL(request.getRequestURI())%>">Sign
in</a> to include your name with greetings you post.</p>
<%
	}

	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);

	Query query = new Query("Greeting", guestbookKey).addSort("date",
			Query.SortDirection.DESCENDING);

	List<Entity> greetings = datastore.prepare(query).asList(
			FetchOptions.Builder.withLimit(5));

	if (greetings.isEmpty()) {
%>
<p>Guestbook '<%=guestbookName%>' has no messages.</p>
<%
	} else {
%>
<p>Messages in Guestbook '<%=guestbookName%>'.</p>
<%
	for (Entity greeting : greetings) {
			if (greeting.getProperty("user") == null) {
%>
<p>An anonymous person wrote:</p>
<%
	} else {
%>
<p><b><%=((User) greeting.getProperty("user"))
								.getNickname()%></b>
wrote:</p>
<%
	}
%>
<blockquote><%=greeting.getProperty("content")%></blockquote>
<%
	}

	}
%>

 <form action="/signa" method="post">
      <div><textarea name="content" rows="3" cols="60"></textarea></div>
      <div><input type="submit" value="Post Greeting" /></div>
      <input type="hidden" name="guestbookName" value="<%= guestbookName %>"/>
 </form>


</body>
</html>