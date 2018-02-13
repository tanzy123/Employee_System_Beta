<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Email Verification</title>
</head>
<body>
<h1>Please Key in the 4 digit token sent to your registered Email</h1>
<form action="verifyToken" method="post">
<input>
<table>
				<tr>
					<td>Token</td>
					<td><input type="text" name="token" required /></td>
				</tr>
				<br>
				${message}
</table>

<<input type="submit" value="Submit" id="emailToken" name="emailToken">


</form>
</body>
</html>