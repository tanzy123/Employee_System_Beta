<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Search For Existing Employee</h1>
	<form action="update">
		Employee UserName <input type="text" name="userName" required />

		
	
	<input type="submit" value="Search" />
	</form>
		<!-- <input type="submit" value="search" id="search" name="search"> -->
		


	<form action="BackToEmployeemanagementPage">
		<button name="back">Back</button>
	</form>
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






</body>
</html>
<!-- <script>

function editScrap(username)
{
	window.location.href="http://localhost:8080/VendorApplication-0.0.1-SNAPSHOT/update?employeeUserName="+username;
	
	}

<<<<<<< HEAD
</script> -->
=======
</script> -->
>>>>>>> 124640a223d1cdf0320f3607c577b51121408e0b
