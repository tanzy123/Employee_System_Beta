<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Vendor Application Details</title>
</head>
<body>
<div class="container">
<h1>Vendor Application Details</h1>
<ul class="list-group">
		<li>Vendor Name: <strong>${companyApplication.company.companyName}</strong></li>
		<li>Vendor Reference Number: <strong>${companyApplication.company.companyReferenceNumber}</strong></li>
		<li>Vendor Address: <strong>${companyApplication.company.companyAddress}</strong></li>
		<li>Vendor Email: <strong>${companyApplication.company.companyEmail}</strong></li>
		<li>Vendor Contact Number: <strong>${companyApplication.company.contactNumber}</strong></li>
		<li>Vendor Turnover: <strong>${companyApplication.company.turnover}</strong></li>
		<li>Application Reference Number: <strong>${companyApplication.application.applicationRef}</strong></li>
		<li>Category of Application:<strong> ${companyApplication.application.category.categoryName}</strong></li>
		<li>Point of Contact:<strong> ${companyApplication.application.POC}</strong></li>
		<li>Application Date: <strong>${companyApplication.application.applicationDate}</strong></li>
		</ul>
	<h2>Documents</h2>
	<table class="table">
		<tr>
			<th>Filename</th>
			<th>File</th>
		</tr>
		<c:forEach var="file" items="${files}">
			<tr>
				<td>${file.filename}</td>
				<td><a href="${file.url}">Download File</a></td>
		</c:forEach>
	</table>
	<a class="btn btn-info" href="/VendorApplication/assignVetter/${companyApplication.application.applicationRef}">Assign Vetters</a>
		<br><br>
		<form action="/VendorApplication/dashboardcompany/vendorApplication">
    <input type="submit" class="btn btn-default" value="Go Back" />
    	</form>
		</div>
</body>

</html>