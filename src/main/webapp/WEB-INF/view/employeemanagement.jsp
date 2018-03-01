<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Employee Management</title>
</head>
<body>
<div class="container">
	<h1>Employee Management</h1>
	<form action="showCreateEmployee">
		<button id="createEmployee" name="createEmployee" class="btn btn-default btn-lg btn-block"
			onclick="location.href='createEmployee'">Create New Employee</button>
	</form>
	<form action="showSearchEmployee">
		<button id="SeacherEmployee" name="SeacherEmployee" class="btn btn-default btn-lg btn-block"
			onclick="location.href='searchEmployee">Search For Existing
			Employee</button>
	</form>
	<form action="showUpdateEmployee">
		<button id="updateEmployee" name="updateEmployee" class="btn btn-default btn-lg btn-block"
			onclick="location.href='updateEmployee'">Update Employee</button>
	</form>
	<form action="showDeleteEmployee">
		<button id="deleteEmployee" name="deleteEmployee" class="btn btn-default btn-lg btn-block"
			onclick="location.href='deleteEmployee'">Delete Employee</button>
	</form>
	<form action="dashboardcompany">
    <input type="submit"  class="btn" value="Go Back" />
    </form>
</div>
</body>
</html>