<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Vetters</title>
</head>
<body>
	<h1>
		Vetter Assignment for Application <span id="applicationRef">${appRef}</span>
	</h1>
	
	<table id="sequenceTable">
		
		<c:forEach var="vetter" items="${vetterList}">
			<tr>
			<td>${vetter.employeeName}</td>
			<td>${vetter.employeeId}</td>
			<td>
            <button><a href="javascript:deleteDep(${roles.roleId})">Delete</a></button>
            </td>
			</tr>
		</c:forEach>
		
		
		
	</table>
	<form action="findByEmpName/${comRef}/${appRef}" method="post">
	<tr>
		<td><input type="submit" value="Submit"
			id="submitCompanyRegistration" name="submitCompanyRegistration" />
		</td>
	</tr>
	
		<tbody>
			<tr id='row1'>
				<td>SEARCH BY EMPLOYEE NAME TO ADD</td>
				<td><input id="empName" name="empName"  type="text" /></td>
			</tr>
		</tbody>
	</form>
</body>

<script type="text/javascript">
	
</script>
</html>