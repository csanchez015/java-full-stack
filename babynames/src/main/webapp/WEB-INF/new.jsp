<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="width:80%; margin:0 auto;">
	<h1>Add a name!</h1>
	
	<form:form action="/names/new" method="post" modelAttribute="name">

	<table>
	    <thead>
	    	<tr>
	            <td class="attributes">New Name:</td>
	            <td><form:input class="input" path="name"/><p style="color:red;"><form:errors path="name" /></p></td>
	        </tr>
	        <tr>
	            <td class="attributes">Typical Gender:</td>
	            <td><form:input class="input" path="gender"/><p style="color: red;"><form:errors path="gender" /></p></td>
	        <tr>
	            <td class="attributes">Origin:</td>
	            <td><form:input class="input" path="origin"/><p style="color: red;"><form:errors path="origin" /></p></td>
	        </tr> 
	        <tr>
	            <td class="attributes">Meaning:</td>
	            <td><form:textarea rows="3" class="input" path="meaning"/><p style="color: red;"><form:errors path="meaning" /></p></td>
	        <tr>  
	    
	    </thead>
	</table>
	<div style="display:flex;">
	
	<button class="button" type="submit">Submit</button>
	</div>
	
	</form:form>
	
	</div>
</body>
</html>