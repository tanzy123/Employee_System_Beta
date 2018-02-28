<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>pending Vendor Application View** </title>
</head>
<body>
	<h1>pending Vendor Application View** </h1>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
String id = request.getParameter("userid");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String database = "vendormanagement";
String userid = "root";
String password = "training123";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<!DOCTYPE html>
<html>
<body>

<table border="1">
<tr>
<td>Company Name</td>
<td>Category</td>
<td>Details</td>
<td>Approve</td>
<td>Reject</td>

</tr>
<%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement=connection.createStatement();
String sql ="select * from company";
resultSet = statement.executeQuery(sql);
while(resultSet.next())
{
%>

<tr>
<td><%=resultSet.getString("companyName") %></td>

<td><button type="submit" value="Accept">Accept</button></td>
<td><button type="submit" value="Reject">Reject</button></td>
</tr>
<%
}





connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>