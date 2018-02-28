<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Application Details</title>
</head>
<body>
<table><tr><td>Vendor Name: ${companyApplication.company.companyName}</td></tr>
<tr><td>Vendor Reference Number: ${companyApplication.company.companyReferenceNumber}</td></tr>
<tr><td>Vendor Address: ${companyApplication.company.companyAddress}</td></tr>
<tr><td>Vendor Email: ${companyApplication.company.companyEmail}</td></tr>
<tr><td>Vendor Contact Number: ${companyApplication.company.contactNumber}</td></tr>
<tr><td>Vendor Turnover: ${companyApplication.company.turnover}</td></tr>
<tr><td>Application Reference Number: ${companyApplication.application.applicationRef}</td></tr>
<tr><td>Category of Application: ${companyApplication.application.category.categoryName}</td></tr>
<tr><td>Point of Contact: ${companyApplication.application.POC}</td></tr>
<tr><td>Application Date: ${companyApplication.application.applicationDate}</td></tr>
			</table>
<form:form action="vetApplication/${companyApplication.application.applicationRef}" method="post" modelAttribute="requirementApproval">
<form:label path="requirements">Requirements to be sent to Vendor</form:label>
    <form:input path="requirements" type="text" />
    <form:select path="approvalStatus">
    	<form:option value="APPROVE"/>
    	<form:option value="REJECT"/>
    </form:select>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>