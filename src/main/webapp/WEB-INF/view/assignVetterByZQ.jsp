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
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Assign Vetters</title>
</head>

<body>
<<<<<<< HEAD
	<h1>
		Vetter Assignment for Application <span id="applicationRef">${appRef}</span>
	</h1>
	
	<table id="sequenceTable">
		
		<c:forEach var="vetter" items="${vetterList}">
			<tr>
			<td>Name :  ${vetter.employeeName}</td>
			<td>Id   :  ${vetter.employeeId}</td>
			<td>
            <button><a href="javascript:deleteVet('${vetter.userName}','${appRef}')">Delete</a></button>
            </td>
			</tr>
		</c:forEach>
		
	</table>
	<form action="findByEmpName" method="get">
	<input type="submit" value="Submit"
			id="submitCompanyRegistration" name="submitCompanyRegistration" />
	
		<tbody>
			<tr id='row1'>
				<td>SEARCH BY EMPLOYEE NAME TO ADD</td>
				<td>Employee Name<input id="empName" name="empName"  type="text" /></td>
				<td>Company Reference<input id="empName" name="comRef"  type="text" value ="${comRef}" /></td>
				<td>Application Reference<input id="empName" name="appRef"  type="text" value ="${appRef}" /></td>
			</tr>
		</tbody>
	</form>
=======
	<div class="container">
		<h1>
			Vetter Assignment for Application <span id="applicationRef">${appRef}</span>
		</h1>

		<table id="sequenceTable" class="table">

			<c:forEach var="vetter" items="${vetterList}">
				<tr>
					<td>Name : ${vetter.employeeName}</td>
					<td>Id : ${vetter.employeeId}</td>
					<td>
						<button class="btn btn-danger">
							<a href="javascript:deleteVet('${vetter.userName}','${appRef}')">Delete</a>
						</button>
					</td>
				</tr>
			</c:forEach>



		</table>
		<form action="findByEmpName" method="get">
			<table class="table">
				<tr>
					<td><input type="submit" value="Submit"
						id="submitCompanyRegistration" name="submitCompanyRegistration"
						class="btn btn-success" /></td>
				</tr>

				<tbody>
					<tr id='row1'>
						<td>SEARCH BY EMPLOYEE NAME TO ADD</td>
						<td>Employee Name<input id="empName" name="empName"
							type="text" /></td>
						<td>Company Reference<input id="empName" name="comRef"
							type="text" value="${comRef}" /></td>
						<td>Application Reference<input id="empName" name="appRef"
							type="text" value="${appRef}" /></td>
					</tr>
					</tbody>
			</table>
			
		</form>
	</div>
>>>>>>> 4ed8c61b1370bb682a7c3e3c7763d0befa0e1c21
</body>

<script type="text/javascript">
	function deleteVet(userName, appRef) {
		window.location = 'deleteVetInfo?userName=' + userName + '&appRef='
				+ appRef;
	}
</script>
</html>