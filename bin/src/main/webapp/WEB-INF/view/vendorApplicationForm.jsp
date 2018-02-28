<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Application Form</title>
</head>
<body>
<h3>Vendor Application Form</h3>
        <form:form method="POST" action="/VendorApplication/applyApplicationStage2" modelAttribute="application">
             <table>
                <tr>
                    <td><form:label path="companyReferenceNumber">Company Reference Number (company applying to)</form:label></td>
                    <td><form:input path="companyReferenceNumber"/></td>
                </tr>
                <tr>
                    <td><form:label path="vendorReferenceNumber">Vendor Reference Number (your company)</form:label></td>
                    <td><form:input path="vendorReferenceNumber" value="${account.companyReferenceNumber}" readonly="true"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Next"/></td>
                </tr>
            </table>
        </form:form>
</body>
</html>