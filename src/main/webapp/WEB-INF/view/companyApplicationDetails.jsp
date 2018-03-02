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
<title>Company Application Details</title>
</head>
<body>
<div class="container">
	<table class="table">
		<tr>
			<td>Company Name: ${companyApplication.company.companyName}</td>
		</tr>
		<tr>
			<td>Company Reference Number:
				${companyApplication.company.companyReferenceNumber}</td>
		</tr>
		<tr>
			<td>Company Address:
				${companyApplication.company.companyAddress}</td>
		</tr>
		<tr>
			<td>Company Email: ${companyApplication.company.companyEmail}</td>
		</tr>
		<tr>
			<td>Company Contact Number:
				${companyApplication.company.contactNumber}</td>
		</tr>
		<tr>
			<td>Company Turnover: ${companyApplication.company.turnover}</td>
		</tr>
		<tr>
			<td>Application Reference Number:
				${companyApplication.application.applicationRef}</td>
		</tr>
		<tr>
			<td>Category of Application:
				${companyApplication.application.category.categoryName}</td>
		</tr>
		<tr>
			<td>Point of Contact: ${companyApplication.application.POC}</td>
		</tr>
		<tr>
			<td>Application Date:
				${companyApplication.application.applicationDate}</td>
		</tr>
	</table>
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
	</div>
</body>
</html>