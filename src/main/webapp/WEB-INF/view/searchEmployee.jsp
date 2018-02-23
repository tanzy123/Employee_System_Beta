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
	<form action="searchEmployee" method="get">



		Employee UserName <input type="text" name="employeeUserName" required />
		<input type="submit" value="Search" id="search" name="search">
<table><tr>
			<td>${employee.employeeId}</td>
			<td>${employee.employeeEmail}</td>
			<td>${employee.contactNumber}</td>
			<td>${employee.role.role}</td>
			<td>${employee.logInType}</td>
			<td>${employee.department.department}</td>
			<td>${employee.userName}</td>
			<td>${employee.password}</td>
		</table>
	 


	</form>
</body>
</html>