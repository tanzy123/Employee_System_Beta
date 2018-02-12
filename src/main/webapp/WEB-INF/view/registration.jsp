<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registratopn</title>
</head>

<body>
<h1>Company Registration</h1>
<div class="ex">
		<form action="storeRegistration" method="post">
			<table>
				<tr>
					<td>Company Reference Number</td>
					<td><input type="text" name="companyReferenceNumber" /></td>
				</tr>
				<tr>
					<td>Company Name</td>
					<td><input type="text" name="companyName" /></td>
				</tr>
				<tr>
					<td>Company Address</td>
					<td><input type="text" name="companyAddress" /></td>
				</tr>
				<tr>
					<td>Company Email</td>
					<td><input type="text" name="companyEmail" /></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber" /></td>
				</tr>
				<tr>
					<td>Company Website</td>
					<td><input type="text" name="companyWebsite" /></td>
				</tr>
				<tr>
					<td>Company Turnover</td>
					<td><input type="text" name="companyTurnover" /></td>
				</tr>
				
			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>