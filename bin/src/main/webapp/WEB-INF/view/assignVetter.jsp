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
	<h1>Vetter Assignment for Application
		<span id="applicationRef">${companyApplication.application.applicationRef}</span></h1>
	<input type="button" value="Add New Vetter" id="addNewVetter" />
	<input type="button" value="Delete Vetter" id="deleteVetter" />
	<table id="sequenceTable">
		<thead>
			<tr>
				<th>Sequence Number</th>
				<th>Username</th>
			</tr>
		</thead>
		<tbody>
			<tr id='row1'>
				<td>1</td>
				<td><input id="text1" type="text" /></td>
			</tr>
		</tbody>
	</table>
	<br />
	<input id="saveRequirement" type="button" value="Save" />
</body>
</html>