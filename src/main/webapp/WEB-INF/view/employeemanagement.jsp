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
		<button id="createEmployee" name="createEmployee"
			onclick="location.href='createEmployee'">Create New Employee</button>
	</form>
	<form action="showSearchEmployee">
		<button id="SeacherEmployee" name="SeacherEmployee"
			onclick="location.href='searchEmployee">Search For Existing
			Employee</button>
	</form>
	<form action="showUpdateEmployee">
		<button id="updateEmployee" name="updateEmployee"
			onclick="location.href='updateEmployee'">Update Employee</button>
	</form>
	<form action="showDeleteEmployee">
		<button id="deleteEmployee" name="deleteEmployee"
			onclick="location.href='deleteEmployee'">Delete Employee</button>
	</form>
	<form action="dashboardcompany">
    <input type="submit" value="Go Back" />
    </form>

</body>
</html>