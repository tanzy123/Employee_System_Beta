<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Email Verification</title>
</head>
<body>
<div class="container">
	<h1>Please Key in the 4 digit token sent to your registered Email</h1>
	<form action="verifyToken" method="post">
		<table class="table">
			<tr>
				<td>Token</td>
				<td><input type="text" name="token" required /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" value="${username}"
					required /></td>
			</tr>
		</table>

		<input type="submit" class="btn btn-primary" value="Submit" id="emailToken" name="emailToken">


	</form>
	</div>
</body>
</html>