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
			<div style= "display:flex; justify-content:space-between; align-items: center;">
			<h1><c:out value="${name.name}"></c:out></h1>
			<a href="/dashboard">back to names</a>
			</div>
			<h2>(added by <c:out value="${name.user.userName}"></c:out>)</h2>
			<br>
			<h2>Gender: <c:out value="${name.gender}"></c:out></h2>
			<h2>Origin: <c:out value="${name.origin}"></c:out></h2>
			<br>
			<h3>Meaning: <c:out value="${name.meaning}"></c:out></h3>
			<hr>
			<c:if test = "${userId == name.user.id}">
   					<hr>
				<a href="/names/edit/${name.id}">Edit</a>
				<form:form method="post" action="/names/delete/${id}" >
   					<input type="hidden" name="_method" value="delete">
    				<button type="submit">Delete</button>
				</form:form>
			</c:if>
			</div>
</body>
</html>