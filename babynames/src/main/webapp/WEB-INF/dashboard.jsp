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
	<div style="display:flex; justify-content:space-between;">
		<h1 style="color:#009E1C;">Hello, <c:out value="${user.userName}"/>. Here are some suggestions..</h1>
		<a href="/logout">log out</a>
	</div>
	<h1>Baby Names</h1>
	<table style="padding: 10px; text-align: center;">
		<thead>
			<tr>
				<th style="width:30%;"></th>
				<th style="width:30%;"></th>
				<th style="width:30%;"></th>
			</tr>
    	<tbody>
		<c:forEach var="name" items="${names}">
			<tr>
				<td><a href="/names/${name.id}"><c:out value="${name.name}"></c:out></a></td>
				<td><c:out value="${name.gender}"></c:out></td>
				<td><c:out value="${name.origin}"></c:out></td>
			</tr>	
		</c:forEach>
    	</tbody>
	</table>
	
	<div>
		<a href="/names/new">new name</a>
	</div>

</div>
</body>
</html>