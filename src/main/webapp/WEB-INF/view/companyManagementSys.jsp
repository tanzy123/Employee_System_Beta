<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function redirectPage(use,rname){
for (var val = 0, r1=use.elements; val < r1.length; val++)
if(r1[val].name==rname&&r1[val].checked)
use.action=r1[val].value;
}
</script>
</head>
<body>
<h1>Company Management System</h1>
</body>


<div>
<form action="#" method="post" onsubmit="redirectPage(this, 'r1');">
<input type="radio" name="r1" value="vetterManagement.com">Vetter Management<br><br>
<input type="radio" name="r1" value="pendingVendorApplication.com">Pending Vendor Application<br><br>
<input type="radio" name="r1" value="vendorApplicationHistory.com">Vendor Application History<br><br>
<input type="radio" name="r1" value="applyToBeAVendor.com">apply To Be Vendor<br><br>
<input type="radio" name="r1" value="employeeManagement.com">Employee Management<br><br>
<input type="radio" name="r1" value="requestServiceFromAVendor.com">request Service From A Vendor<br><br>


<input type="submit" value="Next"/>
</form>
</div>
</html>
</html>