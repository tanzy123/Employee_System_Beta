<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Dashboard</title>

</head>
<body>
	<h1>Employee Log In View**</h1>
<<<<<<< HEAD

<form action="pendingvendorapplication">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Pending
			Vendor Application" />
			</form>
			<form action="login">
				<input class="btn btn-default btn-lg btn-block" type="submit" value="Log Out" />
			</form>
=======
	
	<h1>
	Welcome!
		<%= request.getSession().getAttribute("employeeName")%>
    	<%= request.getSession().getAttribute("employeeRefNumber")%>
	
	</h1>
>>>>>>> 57752d93543164e15c466fd83e58e170c00a4cb4
</body>
</html>