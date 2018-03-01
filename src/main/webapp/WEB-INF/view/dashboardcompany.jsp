<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>

	<p>${message}</p>
	<form action="vetterManagement">
		<input type="submit" value="Vetter Management" />
	</form>
	<form action="pendingCompanyApplication">
		<input type="submit" value="Pending Vendor Applications" />
	</form>
	<form action="companyApplication">
		<input type="submit" value="Vendor Application History" />
	</form>
	<form action="vendorApplicationForm">
		<input type="submit" value="Vendor Application Form" />
	</form>
	<form action="employeeManagement">
		<input type="submit" value="Employee Management" />
	</form>
	<form action="requestService">
		<input type="submit" value="Request Services from Vendor" />
	</form>
	<form action="serviceRequestFromVendor" method="get">
		<input type="submit" value="Service Requests from Vendor" />
	</form>
	<form action="updateCompany">
		<input type="submit" value="Edit Company Info" />
	</form>
	<form action="updateDepartment">
		<input type="submit" value="Edit Department Info" />
	</form>
	<form action="updateRole">
		<input type="submit" value="Edit Role Info" />
	</form>
	<form action="updateCat">
		<input type="submit" value="Edit Category Info" />
	</form>
	<form action="login">
		<input type="submit" value="Log Out" />
	</form>
	
</body>
</html>