<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management</title>
</head>
<body>
	<h1>Employee Management</h1>
	<form action="showCreateEmployee">
	<button id="createEmployee" name="createEmployee" onclick="location.href='createEmployee'">Create New Employee</button> 
	</form>
	<form action="showSearchEmployee">
	<button id="SeacherEmployee" name="SeacherEmployee" onclick="location.href='searchEmployee">Search For Existing Employee</button>
	</form>
	<button id="updateEmployee" name="updateEmployee" onclick="location.href='updateEmployee'">Update Employee</button>
	<button id="deleteEmployee" name="deleteEmployee" onclick="location.href='deleteEmployee'">Delete Employee</button>
	<form action="/VendorApplication-0.0.1-SNAPSHOT/dashboardcompany">
    <input type="submit" value="Go Back" />
</body>
</html>