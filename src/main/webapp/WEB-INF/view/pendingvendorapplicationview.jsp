<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>pending vendor application view</h1>

<table>
<tr>

	<td>Employee Id</td>
	<td>Employee Email</td>
	
	
	</tr>
	<c:forEach var="pendingvendorapplication" items="${vendorApplicationList}">
			
			<tr>
			<td><c:out value="${pendingvendorapplication.employeeId}" /></td>
			
			
			<td><c:out value="${pendingvendorapplication.employeeEmail}" /></td>
			
	
			
			</tr>
	</c:forEach>
			
	</tr>
	
		
		</table>	
	
	
	

</tr>
<td>
</body>
</html>