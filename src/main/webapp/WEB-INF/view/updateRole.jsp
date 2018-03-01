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
		<th>List of roles in your company</th>
	</tr>
	<tr>
		<c:forEach var="roles" items="${rolelist}">
			<tr>
			<td>${roles.role}</td>
			<td>
            <a href="javascript:deleteDep(${roles.roleId})">Delete</a>
            </td>
			</tr>
		</c:forEach>
	</tr>
	</table>
	
	<form action="storeNewRoleInfo" method="post">
	<tr>
		<td><input type="submit" value="Submit"
			id="submitCompanyRegistration" name="submitCompanyRegistration" />
		</td>
	</tr>
	
		<tbody>
			<tr id='row1'>
				<td>ADD NEW ROLE HERE, SEPARATE EACH ENTRY USING A COMMA</td>
				<td><input id="roleName" name="roleName"  type="text" /></td>
			</tr>
		</tbody>
	</form>
		<form action="dashboardcompany">
			
   			 <input type="submit" value="Go Back" />
    	</form>
    </body>
  <script type="text/javascript">
   function deleteDep(depId){
	   window.location = 'deleteRoleInfo?depid='+depId;
   }
   </script>
    </html>