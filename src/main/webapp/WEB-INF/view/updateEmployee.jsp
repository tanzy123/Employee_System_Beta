<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Search For Existing Employee</h1>
	<form action="searchEmployeeToUpdate" method="get">
	
		Employee UserName <input type="text" name="employeeUserName" required />
		<input type="submit" value="Search" id="search" name="search">

		<table>
			<tr>
				<td>Employee Id</td>
				<td>Employee Email</td>
				<td>Role</td>
				<td>Department</td>
				
			</tr>
			<tr>
				<td><input type="text" name="employeeId" value="${employee.employeeId}"></td>
				<td><input type="text" name="employeeEmail" value="${employee.employeeEmail}"></td>
				<td><input type="text" name="departmentName" value="${employee.role.role}"></td>
				<td><input type="text" name="role" value="${employee.department.departmentName}"></td>
				
			</tr>
		</table>

		
			<input type="submit" value="Update" id="update" name="update">

	</form>
	<form action="BackToEmployeemanagementPage"><button name="back">Back</button></form>
</body>
</html>