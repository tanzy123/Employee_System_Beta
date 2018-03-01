<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Vendor Management System</title>
</head>
<body style="background-color: lightblue;">

	<div class="container">
		<h1 class="welcome text-center">
			Welcome to <br> Vendor Management System
		</h1>
		<div class="card card-container">
		<div class="center-block" style="width:400px;background-color:#ccc;">
		<div style="padding:20px;">
			<h2 class='login_title text-center'>Login</h2>
			<hr>
			<form class="form-signin" action="loginVerification" method="post">
				<span id="reauth-email" class="reauth-email"></span>
				<p class="input_title">Username</p>
				<input type="text" class="login_box" id="username" name="username"
					required>
				<p class="input_title">Password</p>
				<input type="password" id="password" name="password"
					class="login_box" placeholder="******" required>
				<p class="input_title">Account Type</p>
				<select name="selectLoginType" id="selectLoginType">
					<option value="EMPLOYEE">EMPLOYEE</option>
					<option value="COMPANY_ADMINISTRATOR">COMPANY ADMINISTRATOR</option>
				</select><br><br><input class="btn btn-lg btn-primary" type="submit" value="Login" id="login" name="login">
			</form>
			<br>
			<form action="registration" method="get">
				<input class="btn btn-lg btn-info" type="submit" value="RegisterCompany" id="register"
					name="register">
			</form>
			</div>
			</div>
		</div>
	</div>
</body>
</html>


