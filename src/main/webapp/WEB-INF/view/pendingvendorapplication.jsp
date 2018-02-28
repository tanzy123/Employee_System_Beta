<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Vendor Application View**</title>
</head>
<body>
	<h1>Pending Vendor Application</h1>
	<table>
		<tr>
			<th>Company Name</th>
			<th>Category</th>
		</tr>
		<c:forEach var="companyApplication" items="${companyApplicationlist}">
			<tr>
				<td>${companyApplication.company.companyName}</td>
				<td>${companyApplication.application.category.categoryName}</td>
				<td><a href="companyApplicationPending/${companyApplication.application.applicationRef}">Details</a></td>
		</c:forEach>
	</table>

</body>

</html>