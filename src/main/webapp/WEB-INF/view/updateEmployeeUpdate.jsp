<%@page import="com.beta.services.impl.RoleServiceImpl"%>
<%@page import="com.beta.services.RoleService"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.beta.entity.*" %>
<%
EmployeeAccount account = (EmployeeAccount)session.getAttribute("employee");



String roleName  = account.getRole().getRole();

//String role
%>
</head>
<body>
	<form:form method="post" action="edited" modelAttribute="updateEmployeeUpdate">
		<table>
			<tr>
				<td><form:label path="userName">Employee UserName</form:label></td>
				<td><form:input path="userName"/></td>

			</tr>
			<tr>
				<td><form:label path="employeeId">Employee Id</form:label></td>
				<td><form:input path="employeeId" /></td>
			</tr>
			<tr>
				<td><form:label path="employeeEmail">Employee Email</form:label></td>
				<td><form:input path="employeeEmail" /></td>
			</tr>
			<%-- <tr>
				<td><form:label  path="password">Password</form:label></td>
				<td><form:input  path="password" /></td>
			</tr> --%>
			<tr>
				<td><form:label path="role">Role</form:label></td>
				<%-- <td><input type="text" value="${employee.role.role}"></input></td> --%> 
				<td><select name="role">
				<option value="${employee.role.role}" selected>${employee.role.role}</option>
						<c:forEach var="role" items="${roleNames}">
						<%-- <option>${role} --%>
							<c:if test="${role !=employee.role.role}">
							<option value="${role}">${role}</option>
							 </c:if>
						</c:forEach>
				</select></td>
			</tr>
				<tr>
				<td><form:label path="department">Department</form:label></td>
				<td><select name="department">
				<option value="${employee.department.departmentName}" selected>${employee.department.departmentName}</option>
						<c:forEach var="department" items="${roleNames}">
						<%-- <option>${role} --%>
							<c:if test="${department !=employee.department.departmentName}">
							<option value="${department}">${department}</option>
							 </c:if>
						</c:forEach>
				</select></td>
			</tr>
			</table>
			<input type="submit" value="update" id="update" name="update">
			</form:form>
</body>
</html>			
	<form action="BackToEmployeemanagementPage"><button name="back">Back</button></form>		
			
			
			
			
			
			
			
			
			
			
			
			
			
			<%-- <tr>
		        <td>UserName</td>
				<td>Employee Id</td>
				<td>Employee Email</td>
				<td>Role</td>
				<td>Department</td>
			</tr>
			<tr>
			    <td><input type="text" name="userName" value="${employee.userName}"></td>
				<td><input type="text" name="employeeId" value="${employee.employeeId}"></td>
				<td><input type="text" name="employeeEmail" value="${employee.employeeEmail}"></td>
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
			</tr> --%>

