<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee Profile</title>
</head>
<body>
<h1>Update Employee Profile</h1>
<form action=updateEmployee  method="get">



		<!-- Employee UserName <input type="text" name="employeeUserName" required />
		<input type="submit" value="Search" id="search" name="search">
		 -->

			<table>
				<tr>
					<td>Employee ID</td>
					<td><input type="text" name="employeeId"<%--  value="${employee.employeeId}" --%>/></td>
				</tr>
				<tr>
					<td>Employee Email</td>
					<td><input type="text" name="employeeEmail" <%-- value="${employee.employeeEmail}"  --%>/></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber" <%-- value="${employee.contactNumber}" --%>/></td>
				</tr>
				<tr>
					<td>Role</td>
					<td><input type="text" name="role" <%-- value="${employee.role.role}" --%>/></td>
				</tr>
				<tr>
					<td>Department Name</td>
					<td><input type="text" name="departmentName" <%-- value="${employee.department.departmentName}" --%> /></td>
				</tr>
				<tr>
					<td>UserName</td>
					<td><input type="text" name="userName" <%-- value="${employee.userName}" --%> /></td>
				</tr>
			</table>
			<input type="submit" value="Update" id="update" name="update">
		</form>



</body>
</html>