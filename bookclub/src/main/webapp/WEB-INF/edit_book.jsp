<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/edit.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/books/edit/${book.id}" method="post" modelAttribute="book">
	<input type="hidden" name="_method" value="put">
	<form:input type="hidden" name="user" path="user"/>
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
</body>
</html>

