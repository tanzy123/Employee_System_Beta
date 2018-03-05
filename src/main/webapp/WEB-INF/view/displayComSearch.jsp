<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Seems to be a problem while login in</title>
    </head>
    <%@ page import="com.beta.entity.Role, java.util.*" %>
  
    <body>
    
    
	<table>
	<tr>
		<th>Here is a list of employee who matches your search</th>
	</tr>
	<tr>
		<c:forEach var="company" items="${comList}">
			<tr>
			<td>Name : ${company.companyName}</td>
			
			<td>Reference No.  :  ${company.companyReferenceNumber}</td>
			</tr>
		</c:forEach>
	</tr>
	</table>
	
	
		<form action="vendorApplicationForm">
			
   			 <input type="submit" value="Go Back To Application Form" />
    	</form>
    </body>
 
    </html>