<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Delete Existing Employee</h1>
	<form action="deleteEmployee">

		Please key in the Employee ID to delete <input type="text" name="employeeId" required />
		<input type="submit" value="delete" id="delete" name="delete">
		

	</form>

	
	<form action="BackToEmployeemanagementPage"><button name="back">Back</button></form>

</body>
</html>