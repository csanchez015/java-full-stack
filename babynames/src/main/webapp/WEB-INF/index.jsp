<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/index.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<div style="width:80%; margin: 3% auto; text-align:center;">
				<h1 style="color: #009E1C; font-size:4em;">Joy Bundler Names</h1>
			</div>
			<div style="width:80%; display:flex; justify-content: space-between; margin: 0 auto;" >
				<div style="width:40%;">
				<h1 style="font-size: 2.7em; margin-bottom: 10px;">Register</h1>
						<form:form action="/register" method="POST" modelAttribute="newUser" >
				<table>
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					<tbody>
						<tr>
							<td class="attributes">User Name:</td>
							<td><form:input class="input" path="userName" /><p style="color:red"><form:errors path="userName" /></p></td>
						</tr>
						
						<tr>
							<td class="attributes">Email:</td>
							<td><form:input class="input" path="email" /><p style="color:red"><form:errors path="email" /></p></td>	
						</tr>
						
						<tr>
							<td class="attributes">Password:</td>
							<td><form:input class="input" type="password" path="password" /><p style="color:red"><form:errors path="password" /></p></td>
						</tr>
						
						<tr>
							<td class="attributes">Confirm PW:</td>
							<td><form:input class="input" type="password" path="confirm" /><p style="color:red"><form:errors path="confirm" /></p></td>
						</tr>
						
					</tbody>
				</table>
						<button class="button" type="submit">Submit</button>
						</form:form>
				</div>
				<div style="width:40%;">
				<h1 style="font-size: 2.7em;">Login</h1>
						<form:form action="/login" method="POST" modelAttribute="newLogin" >
				<table>
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					<tbody>
						<tr>
							<td class="attributes">Email:</td>
							<td><form:input class="input" path="email" /><p style="color:red"><form:errors path="email" /></p></td>
						</tr>
						
						<tr>
							<td class="attributes">Password:</td>
							<td><form:input class="input" type="password" path="password" /><p style="color:red"><form:errors path="password" /></p></td>	
						</tr>
					</tbody>
				</table>
						<button class="button" type="submit">Submit</button>
						</form:form>
				</div>
			</div>
</body>
</html>