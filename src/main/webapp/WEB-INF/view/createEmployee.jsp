<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Employee Profile</title>
</head>
<body>
<h1>Create New Employee Profile</h1>
<form action="createEmployee" method="post">
			<table>
				<tr>
					<td>Employee ID</td>
					<td><input type="text" name="employeeId" required /></td>
				</tr>
				<tr>
					<td>Employee Email</td>
					<td><input type="text" name="employeeEmail" required /></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber" required /></td>
				</tr>
				<tr>
					<td>Role</td>
					<td><input type="text" name="role" required/></td>
				</tr>
				<tr>
					<td>Department Name</td>
					<td><input type="text" name="departmentName" required /></td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="userName" required /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" required /></td>
				</tr>
			</table>
			<input type="submit" value="Create" id="create" name="create">
				
		</form>
				<form action="employeeManagement">
    			<input type="submit" value="Return to employee management" />
    			</form>


</body>
</html>