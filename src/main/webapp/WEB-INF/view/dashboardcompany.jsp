<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
</head>
<body>

<h1>
<form action="/VendorApplication/vetterManagement">
    <input type="submit" value="Vetter Management" />
</form>
</h1>

<h1>
<td><button type="button" name="back" 
							onclick="location.href='pendingvendorapplication'">Pending Vendor Application</button>
							
							</td>							
</h1>


<h1>
<td><button type="button" name="back2" 
							onclick="location.href='vendorapplicationhistory'">Vendor Application History</button>
							
							</td>							
</h1>


<h1>
<td><button type="button" name="back3" 
							onclick="location.href='vettermanagement'">Vetter Management</button>
							
							</td>							
</h1>




<h1>
<form action="/VendorApplication/vettermanagement">
    <input type="submit" value="Employee Management" />
</form>
</h1>

<h1>
<form action="/VendorApplication/requestService">
    <input type="submit" value="Request Services from a Vendor" />
</form>
</h1>

</body>
</html>