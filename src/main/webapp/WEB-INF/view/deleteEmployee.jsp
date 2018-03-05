<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h1>Delete Existing Employee</h1>
	<form action="deleteEmployee">

		Please key in the Employee UserName to delete <input type="text" name="userName" required />
		<input type="submit" value="delete" id="delete" name="delete" class="btn btn-danger">
		
        <div>${message}</div>
	</form>

	
	<form action="BackToEmployeemanagementPage"><button class="btn" name="back">Back</button></form>
</div>
</body>
</html>