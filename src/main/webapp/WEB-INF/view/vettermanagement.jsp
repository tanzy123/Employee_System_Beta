<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Applications to be Vet</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<h1>Pending Applications to be Vet</h1>
	<table class="table">
	<tr>
		<th>Vendor Name</th>
		<th>Category</th>
		<th></th>
		<th></th>
	</tr>
		<c:forEach var="companyApplication" items="${companyApplicationlist}"><tr>
			<td>${companyApplication.company.companyName}</td>
			<td>${companyApplication.application.category.categoryName}</td>
			<td><a href="/VendorApplication/vendorApplication/${companyApplication.application.applicationRef}" >Details</a></td>
			<td><a href="/VendorApplication/assignVetter/${companyApplication.application.applicationRef}" >Assign Vetters</a></td>

		</c:forEach>
		</table>
		<form action="dashboardcompany">
    <input type="submit" value="Go Back" />
    	</form>
	
	</div>
</body>
</html>