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
	
	<h3>Documents upload</h3>
  <form:form action="/VendorApplication/uploadDocumentsAndSubmit" method="post" enctype="multipart/form-data" modelAttribute="application">
    <table>
      <tr>
        <td>Upload Relevant Documents</td>
        <td><input type="file" name="file" multiple="multiple"></td>
        <td><button type="submit">Upload and Submit Application</button></td>
      </tr>
    </table>
    <h3>Application Details</h3>
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
				<td><form:input path="POC" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="remarks">Other comments/remarks</form:label></td>
				<td><form:input path="remarks" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="category.categoryName">Category</form:label></td>
				<td><form:input path="category.categoryName" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="vendorPeriod">Vendor Period</form:label></td>
				<td><form:input path="vendorPeriod" readonly="true"/></td>
			</tr>
		</table>
  </form:form>
</body>

</html>