<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>

<body>
<h1>Company Registration</h1>
<div class="companyRegistration"  style="float: left;">
		<form action="registration" method="post">
			<table>
				<tr>
					<td>Company Reference Number</td>
					<td><input type="text" name="companyReferenceNumber" required /></td>
				</tr>
				<tr>
					<td>Company Name</td>
					<td><input type="text" name="companyName" required /></td>
				</tr>
				<tr>
					<td>Company Address</td>
					<td><input type="text" name="companyAddress" required /></td>
				</tr>
				<tr>
					<td>Company Email</td>
					<td><input type="text" name="companyEmail" required/></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber" required /></td>
				</tr>
				<tr>
					<td>Company Website</td>
					<td><input type="text" name="companyWebsite" /></td>
				</tr>
				<tr>
					<td>Company Turnover</td>
					<td><input type="text" name="turnover" required/></td>
				</tr>
				<tr>
					<td>Vendor Category</td>
					<td><input type="text" name="category" /></td>
				</tr>
				
			</table>
		</form>
	</div>
	<div class="adminRegistration" style="float: right;">
	<form action="registration" method="post">
	            <tr>
					<td>Administrator Username</td>
					<td><input type="text" name="companyAdminUsername" required /></td>
				</tr>
				<br>
				<tr>
					<td>Administrator Password</td>
					<td><input type="password" name="companyAdminPassword" required /></td>
				</tr>
	</form>
	</div>
	<div>
<form action="emailToken" method="post">
<input type="submit" value="Submit" id="submitCompanyRegistration" name="submitCompanyRegistration">
</form>
 </div>
 
</body>
</html>