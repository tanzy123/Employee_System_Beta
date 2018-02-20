<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Applications</title>
</head>
<body>

<h2>Pending Applications to be Vet</h2>
	<table>
	<tr>
		<th>Company Name</th>
		<th>Category</th>
	</tr>
		<c:forEach var="companyApplication" items="${companyApplicationlist}"><tr>
			<td>${companyApplication.company.companyName}</td>
			<td>${companyApplication.application.category.categoryName}</td>
			<td>details</td>
			<td>Assign Vetters</td>
		</c:forEach>
	</table>
</body>
</html>