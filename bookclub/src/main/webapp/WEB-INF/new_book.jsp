<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/new.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="width:80%; margin:0 auto;">
	<div style="display:flex; justify-content:space-between; align-items:center;">
	<h1>Add a Book to Your Shelf!</h1>
	<a href="/dashboard">back to the shelves</a>
	</div>
	
	<form:form action="/newBook" method="post" modelAttribute="book">

	<table>
	    <thead>
	    	<tr>
	            <td class="attributes">Title:</td>
	            <td><form:input class="input" path="title"/><p style="color: red;"><form:errors path="title" /></p></td>
	        </tr>
	        <tr>
	            <td class="attributes">Author:</td>
	            <td><form:input class="input" path="author"/><p style="color: red;"><form:errors path="author" /></p></td>
	        <tr>
	            <td class="attributes">My Thoughts:</td>
	            <td><form:textarea rows="3" class="input" path="thoughts"/><p style="color: red;"><form:errors path="thoughts" /></p></td>
	        </tr>   
	    
	    </thead>
	</table>
	<button class="button" type="submit">Submit</button>
	</form:form>
	
	</div>
</body>
</html>