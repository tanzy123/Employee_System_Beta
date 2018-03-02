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
	<table class="table">
		<tr>
			<td>Vendor Name: <strong>${companyApplication.company.companyName}</strong></td>
		</tr>
		<tr>
			<td>Vendor Reference Number:
				<strong>${companyApplication.company.companyReferenceNumber}</strong></td>
		</tr>
		<tr>
			<td>Vendor Address:<strong> ${companyApplication.company.companyAddress}</strong></td>
		</tr>
		<tr>
			<td>Vendor Email:<strong> ${companyApplication.company.companyEmail}</strong></td>
		</tr>
		<tr>
			<td>Vendor Contact Number:
			<strong>	${companyApplication.company.contactNumber}</strong></td>
		</tr>
		<tr>
			<td>Vendor Turnover:<strong> ${companyApplication.company.turnover}</strong></td>
		</tr>
		<tr>
			<td>Application Reference Number:
			<strong>	${companyApplication.application.applicationRef}</strong></td>
		</tr>
		<tr>
			<td>Category of Application:
			<strong>	${companyApplication.application.category.categoryName}</strong></td>
		</tr>
		<tr>
			<td>Point of Contact:<strong> ${companyApplication.application.POC}</strong></td>
		</tr>
		<tr>
			<td>Application Date:
				<strong>${companyApplication.application.applicationDate}</strong></td>
		</tr>
	</table>
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
	<form:form
		action="vetApplication/${companyApplication.application.applicationRef}"
		method="post" modelAttribute="requirementApproval">
		<form:label path="requirements">Requirements to be sent to Vendor</form:label>
		<form:input path="requirements" type="text" />
		<form:select path="approvalStatus">
			<form:option value="APPROVE" />
			<form:option value="REJECT" />
		</form:select>
		<input type="submit" value="Submit" class="btn btn-primary" />
	</form:form>
</div>
</body>
</html>