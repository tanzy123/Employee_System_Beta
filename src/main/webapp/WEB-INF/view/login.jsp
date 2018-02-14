<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vendor Management System</title>
</head>
<body>
<br>

<form action="loginVerification" method="post">

Enter username :<input type="text" id="username" name="username"> <br>
Enter password :<input type="password" id="password" name="password"><br>
<select name="selectLoginType" id="selectLoginType">
<option value="EMPLOYEE">EMPLOYEE</option>
<option value="COMPANY_ADMINISTRATOR">COMPANY ADMINISTRATOR</option>
</select><br>
<input type="submit" value="Login" id="login" name="login">
</form>
<br>

<form action="registration" method="get">
<input type="submit" value="RegisterCompany" id="register" name="register">
 </form>

</body>
</html>