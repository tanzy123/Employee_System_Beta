<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Vendor Application Form</title>
</head>
<body>
	<h3>Vendor Application Form</h3>
	<form:form method="POST"
		action="/VendorApplication/applyApplicationStage3"
		modelAttribute="application">
		<table>
			<tr>
				<td><form:label path="companyReferenceNumber">Company Reference Number (company applying to)</form:label></td>
				<td><form:input path="companyReferenceNumber" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="vendorReferenceNumber">Vendor Reference Number (your company)</form:label></td>
				<td><form:input path="vendorReferenceNumber" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="POC">Point of Contact</form:label></td>
				<td><form:input path="POC" /></td>
			</tr>
			<tr>
				<td><form:label path="remarks">Other comments/remarks</form:label></td>
				<td><form:input path="remarks" /></td>
			</tr>
			<tr>
				<td><form:label path="category">Category</form:label></td>
				<td><form:select path="category">
						<c:forEach var="category" items="${categoryNames}">
							<option>${category}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="vendorPeriod">Vendor Period (Months)</form:label></td>
				<td><form:input type="number" path="vendorPeriod" min="0"
						step="1" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>

</html>