<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <title>Edit Category</title>
    </head>
   
    <body>
    <div class="container">
    <h1>List of Categories in Your Company</h1>
	<table class="table">
	<tr>
		<c:forEach var="cat" items="${catlist}">
			<tr>
			<td>${cat.categoryName}</td>
			<td>
            <a href="javascript:deleteDep(${cat.categoryId})">Delete</a>
            </td>
			</tr>
		</c:forEach>
	</tr>
	</table>
	
	<form action="storeNewCatInfo" method="post">		
				<p>Add new category(s) here, separate each category by comma, (eg. Category1,Category2,etc.)</p>
				<input id="catName" name="catName"  type="text" />
				<input type="submit" class="btn btn-success" value="Submit"
			id="submitCompanyRegistration" name="submitCompanyRegistration" />
			
	</form>
		<form action="dashboardcompany">
			
   			 <input class="btn btn-default" type="submit" value="Go Back" />
    	</form>
    	</div>
    </body>
  <script type="text/javascript">
   function deleteDep(depId){
	   window.location = 'deleteCatInfo?depid='+depId;
   }
   </script>
    </html>
