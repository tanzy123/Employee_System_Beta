<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Request</title>
<script type="text/javascript">
function redirectPage(use,rname){
for (var val = 0, r1=use.elements; val < r1.length; val++)
if(r1[val].name==rname&&r1[val].checked)
use.action=r1[val].value;
}
</script>
</head>
<body>

<h1>Pending Request</h1>


<form:form action="approve" method="post">
    <table>
        <tr>
            <td colspan="2"><b>Company Name:</b> <c:out value="${bulletin.name}" /></td>
        </tr>
        <tr>
            <td colspan="2"><b>Category:</b> <c:out
                    value="${bulletin.subject}" /></td>
        </tr>
        <tr>
            <td colspan="2"><b>Details:</b> <c:out value="${bulletin.date}" />
                <br></td>
        </tr>
        <tr>
            <td colspan="2"><t:notePrint note="${bulletin.note}" /> <input
                type="hidden" name="id" value="${bulletin.id}" /></td>
        </tr>
        <tr>
            <td><input type="submit" name="approve" value="Approve" /></td>
            <td><input type="submit" name="deny" value="Reject" /></td>
        </tr>
    </table>
    <br />
</form:form>


<div>


</form>
</div>
</html>