<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Vendor Application Form</title>
</head>
<body>
<div class="container">
<h3>Vendor Application Form</h3>
        <form:form method="POST" action="/VendorApplication/applyApplicationStage2" modelAttribute="application">
             <table>
                <tr>
                    <td><form:label path="companyReferenceNumber" >Company Reference Number (company applying to)</form:label></td>
                    <td ><form:input path="companyReferenceNumber" value="${companyReferenceNumber}"/></td>
                </tr>
                <tr>
                    <td><form:label path="vendorReferenceNumber">Vendor Reference Number (your company)</form:label></td>
                    <td><form:input path="vendorReferenceNumber" value="${account.companyReferenceNumber}" readonly="true"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Next" class="btn btn-success"/></td>
                 
                    
                </tr>
            </table>
        </form:form>

   					 
   		<h2>Search for Company by Name</h2>
		<form action="/VendorApplication/findCompany" method="get">
			<input id="comName" name="comName" type="text" /> <input
				type="submit" value="Search" />
		</form>
	
	<br>
		<table class="table">
			<tr>
				<th>Company Name</th>
				<th>Company Reference Number</th>
				
				<th></th>
			</tr>
			<c:forEach var="com" items="${comList}">
				<tr>
					<td>${com.companyName}</td>
					<td>${com.companyReferenceNumber}</td>
					
					<td><form action="/VendorApplication/addCompany/${com.companyReferenceNumber}"><input class="btn btn-default" type="submit" value="Add Company"></form></td>
				</tr>
			</c:forEach>
		</table>
      
</div>
</body>
</html>