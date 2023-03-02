<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<div style="width: 80%; margin: 0 auto;">
			<div style="align-items: center; display:flex; justify-content: space-between;"><h1><c:out value="${book.title}"></c:out></h1>
				<br>
				<p><a href="/dashboard">back to the shelves</a></p>
			</div>
				<br>
			<h3><span style="color: red"><c:out value="${book.user.userName}"></c:out></span> read 
			<span style="color: purple"><c:out value="${book.title}"></c:out></span> by 
			<span style="color: green"><c:out value="${book.author}"></c:out></span>.</h3>
			<h2>Here are <c:out value="${book.user.userName}"></c:out>'s thoughts:</h2>
				<hr>
			<h3><c:out value="${book.thoughts}"></c:out></h3>
			<c:if test = "${userId == book.user.id}">
   					<hr>
				<a href="/books/edit/${book.id}">Edit</a>
				<form:form method="post" action="/books/delete/${id}" >
   					<input type="hidden" name="_method" value="delete">
    				<button type="submit">Delete</button>
				</form:form>
			</c:if>
		</div>
</body>
</html>