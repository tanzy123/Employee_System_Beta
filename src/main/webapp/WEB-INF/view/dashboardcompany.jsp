<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>

<form action="/VendorApplication/vetterManagement">
    <input type="submit" value="Vetter Management" />
</form>
<form action="/VendorApplication/pendingVendorApplication">
    <input type="submit" value="Pending Vendor Applications" />
</form>
<form action="/VendorApplication/vendorApplication">
    <input type="submit" value="Vendor Application History" />
</form>
<form action="/VendorApplication/vendorApplicationForm">
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
</body>
</html>