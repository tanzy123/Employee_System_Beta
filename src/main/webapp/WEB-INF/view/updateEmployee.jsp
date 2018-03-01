<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1>Search For Existing Employee</h1>
	<form action="update">
		Employee UserName <input type="text" name="userName" required />
	<input type="submit" value="Search" class="btn btn-success"/>
	</form>
		<!-- <input type="submit" value="search" id="search" name="search"> -->
		


	<form action="BackToEmployeemanagementPage">
		<button name="back" class="btn">Back</button>
	</form>
	</div>
	</body>
</html>
	<%-- <table>
			<tr>
				<td>Employee Id</td>
				<td>Employee Email</td>
				<td>Role</td>
				<td>Department</td>
			</tr>
			<tr>
				<td><input type="text" name="employeeId"
					value="${employee.employeeId}"></td>
				<td><input type="text" name="employeeEmail"
					value="${employee.employeeEmail}"></td>
				<td><select name="role">
						<c:forEach var="role" items="${roleNames}">
							<option
								<c:if test="${role == employee.role.role}"> selected </c:if>>${role}
							</option>
						</c:forEach>
				</select></td>
				<td><select name="department">
						<c:forEach var="department" items="${departmentNames}">
							<option
								<c:if test="${department == employee.department.departmentName}"> selected </c:if>>${department}</option>
						</c:forEach>
				</select></td>
				<td><a href="javascript:editScrap(${employee.userName})"></a>Edit</td>
			</tr>
		</table> --%>




	<%-- 	    <tr>
				<td><form:label path="userName">Employee UserName</form:label></td>
				<td><form:input path="userName" /></td>
			</tr>
			<input type="submit" value="Search" id="search" name="search">
			<tr>
				<td><form:label path="employeeId">Employee Id</form:label></td>
				<td><form:input path="employeeId" /></td>
			</tr>
			<tr>
				<td><form:label path="employeeEmail">Employee Email</form:label></td>
				<td><form:input path="employeeEmail" /></td>
			</tr>
			<tr>
				<td><form:label path="role">Role</form:label></td>
				<td><form:select path="role">
						<c:forEach var="role" items="${roleNames}">
							<option <c:if test="${role == employee.role.role}"> selected </c:if>>${role}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="department">Department</form:label></td>
				<td><form:select path="department">
						<c:forEach var="department" items="${departmentNames}">
							<option <c:if test="${department == employee.department.departmentName}"> selected </c:if>>${department}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
		<input type="submit" value="update" id="update" name="update">
		</form:form> --%>










