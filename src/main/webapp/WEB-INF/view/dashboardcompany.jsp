<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Main</title>
</head>
<body>

	<div class="container">


			<div class="page-heading">
				<h1>Dashboard, Welcome ${username}!</h1>
			</div>
			<form action="vetterManagement">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Vetter Management" />
			</form>
			<form action="pendingCompanyApplication">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Pending Vendor Applications" />
			</form>
			<form action="companyApplication">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Vendor Application History" />
			</form>
			<form action="vendorApplicationForm">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Vendor Application Form" />
			</form>
			<form action="employeeManagement">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Employee Management" />
			</form>
			<form action="requestService">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Request Services from Vendor" />
			</form>
			<form action="serviceRequestFromVendor" method="get">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Service Requests from Vendor" />
			</form>
			<form action="updateCompany">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Edit Company Info" />
			</form>
			<form action="updateDepartment">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Edit Department Info" />
			</form>
			<form action="updateRole">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Edit Role Info" />
			</form>
			<form action="updateCat">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Edit Category Info" />
			</form>
			<form action="login">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Log Out" />
			</form>
	</div>

</body>
</html>