<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Registration</title>
</head>

<body>
	
	<div class="container">
	<h1>Company Infomation Update</h1>
		<form action="storeNewCompanyInfo" method="post">
			<table class="table">
				<tr>
					<td>Company Reference Number</td>
					<td><input type="text" name="companyReferenceNumber" required value="${company.companyReferenceNumber}" /></td>
				</tr>
				<tr>
					<td>Company Name</td>
					<td><input type="text" name="companyName" required value ="${company.companyName}" /></td>
				</tr>
				<tr>
					<td>Company Address</td>
					<td><input type="text" name="companyAddress" required value ="${company.companyAddress}" /></td>
				</tr>
				<tr>
					<td>Company Email</td>
					<td><input type="text" name="companyEmail" required value ="${company.companyEmail}" /></td>
				</tr>
				<tr>
					<td>Contact Number</td>
					<td><input type="text" name="contactNumber" required value ="${company.contactNumber}" /></td>
				</tr>
				<tr>
					<td>Company Website</td>
					<td><input type="text" name="companyWebsite" value ="${company.companyWebsite}" /></td>
				</tr>
				<tr>
					<td>Company Turnover</td>
					<td><input type="text" name="turnover" required value ="${company.turnover}" /></td>
				</tr>
				
				<tr>
					<td>Administrator Username</td>
					<td><input type="text" name="username" required  /></td>
				</tr>
				<tr>
					<td>Administrator Password</td>
					<td><input type="password" name="password"
						required /></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-success" value="Submit"
						id="submitCompanyRegistration" name="submitCompanyRegistration" />
					</td>
				</tr>
			</table>
		</form>
		
		<form action="dashboardcompany">
			<input type="submit" class="btn btn-default" value="Go Back" />
		</form>
	</div>

</body>
</html>