<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Applications to be Vet</title>
</head>
<body>

<h2>Pending Applications to be Vet</h2>
	<table>
	<tr>
		<th>Vendor Name</th>
		<th>Category</th>
	</tr>
		<c:forEach var="companyApplication" items="${companyApplicationlist}"><tr>
			<td>${companyApplication.company.companyName}</td>
			<td>${companyApplication.application.category.categoryName}</td>
			<td><a href="vendorApplication/${companyApplication.application.applicationRef}" >Details</a></td>
			<td><a href="assignVetter/${companyApplication.application.applicationRef}" >Assign Vetters</a></td>

			<td><a href="vetterDisplay/${companyApplication.application.applicationRef}" >Assign Vetters</a></td>	

		</c:forEach>
		
		<form action="dashboardcompany">
    <input type="submit" value="Go Back" />
    	</form>
	</table>
</body>
</html>