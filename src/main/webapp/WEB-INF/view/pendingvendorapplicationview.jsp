<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>pending vendor application view</h1>

		<table class="table">
			<tr>

				<td>Employee Id</td>
				<td>Employee Email</td>


			</tr>
			<c:forEach var="pendingvendorapplication"
				items="${vendorApplicationList}">
				<tr>
					<td><c:out value="${pendingvendorapplication.employeeId}" /></td>
					<td><c:out value="${pendingvendorapplication.employeeEmail}" /></td>
				</tr>
			</c:forEach>
		</table>
		</div>
</body>
</html>