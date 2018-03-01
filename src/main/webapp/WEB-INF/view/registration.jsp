<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Registration</title>
</head>

<body>
<div class="container">
	<h1>Company Registration</h1>
	<form action="storeRegistration" method="post">
	<div class="form-group">
		<div class="form-group">
      <label for="companyReferenceNumber">Company Reference Number:</label>
      <input type="text" class="form-control" placeholder="Enter Company Reference Number" name="companyReferenceNumber" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="companyName">Company Name:</label>
      <input type="text" class="form-control" placeholder="Enter Company Name" name="companyName" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="companyAddress">Company Address:</label>
      <input type="text" class="form-control" placeholder="Enter Company Address" name="companyAddress" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="companyEmail">Company Email:</label>
      <input type="text" class="form-control" placeholder="Enter Company Email" name="companyEmail" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="contactNumber">Contact Number:</label>
      <input type="text" class="form-control" placeholder="Enter Contact Number (include country code)" name="contactNumber" required>
    </div>
     <div class="form-group">
		<div class="form-group">
      <label for="contactNumber">Company Website:</label>
      <input type="text" class="form-control" placeholder="Enter Company Website" name="companyWebsite">
    </div>
     <div class="form-group">
		<div class="form-group">
      <label for="turnover">Company Turnover:</label>
      <input type="text" class="form-control" placeholder="Enter Turnover in USD" name="turnover" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="category">Vendor Category:</label>
      <input type="text" class="form-control" placeholder="Enter Category in format (Category1,Category2,Category3,etc.)" name="category" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="department">Departments:</label>
      <input type="text" class="form-control" placeholder="Enter Department in format (Dept1,Dept2,Dept3,etc.)" name="department" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="role">Roles:</label>
      <input type="text" class="form-control" placeholder="Enter Department in format (Role1,Role2,Role3,etc.)" name="role" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="username">Administrator Username:</label>
      <input type="text" class="form-control" placeholder="Username" name="username" required>
    </div>
    <div class="form-group">
		<div class="form-group">
      <label for="password">Administrator Password:</label>
      <input type="password" class="form-control" placeholder="Enter Password" name="password" required>
    </div>
				<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</div>
</body>
</html>