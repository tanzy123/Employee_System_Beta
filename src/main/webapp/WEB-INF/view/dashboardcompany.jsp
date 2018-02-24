<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>

<form action="/VendorApplication-0.0.1-SNAPSHOT/vetterManagement">
    <input type="submit" value="Vetter Management" />
</form>
<form action="/VendorApplication-0.0.1-SNAPSHOT/pendingVendorApplication">
    <input type="submit" value="Pending Vendor Applications" />
</form>
<form action="/VendorApplication-0.0.1-SNAPSHOT/vendorApplication">
    <input type="submit" value="Vendor Application History" />
</form>
<form action="/VendorApplication-0.0.1-SNAPSHOT/vendorApplicationForm">
    <input type="submit" value="Vendor Application Form" />
</form>
<form action="employeeManagement">
    <input type="submit" value="Employee Management" />
</form>
<form action="/VendorApplication/requestService">
    <input type="submit" value="Request Services from Vendor" />
</form>
<form action="serviceRequestFromVendor" method="get">
    <input type="submit" value="Service Requests from Vendor" />
</form>

<form action="/VendorApplication-0.0.1-SNAPSHOT/login">
    <input type="submit" value="Log Out" />
</body>
</html>