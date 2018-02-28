<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<td><form:label path="department">Department</form:label></td>
				<td><form:select path="department" name="department">
						<c:forEach var="department" items="${departmentNames}">
							<option>${department}</option>
						</c:forEach>
					</form:select></td>
            </tr>
			<tr>
				<td><form:label path="role">Role</form:label></td>
				<td><form:select path="role" name="role">
						<c:forEach var="role" items="${roleNames}">
							<option>${role}</option>
						</c:forEach>
					</form:select></td>
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

<form action="BackToEmployeemanagementPage"><button name="back">Back</button></form>



</body>
</html>