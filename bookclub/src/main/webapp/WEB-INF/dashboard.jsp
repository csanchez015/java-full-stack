<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="width:80%; margin:0 auto;">
	<div style="display:flex; justify-content:space-between; align-items: center;">
	<h1>Welcome, <c:out value="${user.userName}"/>!</h1>
	<a href="/logout">Logout</a>
	</div>
	<div style="display:flex; justify-content:space-between; align-items: center;">
	<h3>Books from everyones shelves</h3>
	<a href="/newBook">Create an New Book</a>
	</div>
	<div class=table>
	<table style="padding: 10px; text-align: center;">
		<thead>
			<tr>
				<th style="width:10%;">ID</th>
				<th style="width:30%;">Title</th>
				<th style="width:30%;">Author Name</th>
				<th style="width:30%;">Posted By</th>
			</tr>
    	<tbody>
		<c:forEach var="book" items="${books}">
			<tr>
				<td><c:out value="${book.id}"></c:out></td>
				<td><a href="/books/${book.id}"><c:out value="${book.title}"></c:out></a></td>
				<td><c:out value="${book.author}"></c:out></td>
				<td><c:out value="${book.user.userName}"></c:out></td>
			</tr>	
		</c:forEach>
    	</tbody>
	</table>
	</div>
	</div>
</body>
</html>