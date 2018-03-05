<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Vetters</title>
</head>
<body>
	<div class="container">
		<h1>
			Vetter Assignment for Application
		</h1>
		<form:form method="post" action="/VendorApplication/setVetters">
		<table class="table" id="sequenceTable">
			<thead>
				<tr>
					<th>Sequence Number</th>
					<th>Username</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${vetters}" var="vet">
				   <tr>
					<td>${vet.sequenceNo}</td>
					<td>${vet.userName}</td>
					<td><a href="/VendorApplication/deleteVetter/${vet.userName}" >Delete Vetter</a></td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
		<br /> <input id="saveRequirement" type="submit"
			class="btn btn-success" value="Save"  /> <br>
		</form:form>
		
		
		<form action="/VendorApplication/vetterManagement">
			<input class="btn btn-default" type="submit" value="Go Back" />
		</form>
		<h2>Search for Employee by Name</h2>
		<form action="/VendorApplication/assignVetter/findByEmpName" method="get">
			<input id="empName" name="empName" type="text" /> <input
				type="submit" value="Search" />
		</form>
		<br>
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Employee Id</th>
				<th>Email</th>
				<th>Department</th>
				<th>Role</th>
				<th></th>
			</tr>
			<c:forEach var="emp" items="${empList}">
				<tr>
					<td>${emp.employeeName}</td>
					<td><strong>${emp.userName}</strong></td>
					<td>${emp.employeeId}</td>
					<td>${emp.employeeEmail}</td>
					<td>${emp.department.departmentName}</td>
					<td>${emp.role.role}</td>
					<td><form action="/VendorApplication/addVetter/${emp.userName}"><input class="btn btn-default" type="submit" value="Add Employee"></form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>