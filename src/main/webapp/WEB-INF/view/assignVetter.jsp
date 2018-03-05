<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Vetters</title>
</head>
<body>
	<div class="container">
		<h1>
			Vetter Assignment for Application <span id="applicationRef">${companyApplication.application.applicationRef}</span>
		</h1>
		<input type="button" value="Add New Vetter" id="addNewVetter" class="btn btn-primary" /> <input
			type="button" value="Delete Vetter" id="deleteVetter" />
		<table id="sequenceTable" class="table">
			<thead>
				<tr>
					<th>Sequence Number</th>
					<th>Username</th>
				</tr>
			</thead>
			<tbody>
				<tr id='row1'>
					<td>1</td>
					<td><input id="text1" type="text" class="btn"/></td>
				</tr>
			</tbody>
		</table>
		<br /> <input id="saveRequirement" type="button" value="Save" class="btn btn-success"/>
	</div>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var currSeq = 2;
						$('#addNewVetter')
								.click(
										function() {
											$('#sequenceTable')
													.append(
															"<tr id='row"+currSeq+"'><td>"
																	+ currSeq
																	+ "</td><td><input id='text"+currSeq+"' type='text'/></td></tr>");
											currSeq++;
										});
						$('#deleteVetter').click(function() {
							if (currSeq > 1) {
								currSeq--;
								$('#row' + currSeq).remove();
							}
						});
						$('#saveRequirement')
								.click(
										function() {
											var requirements = [];
											var rows = $('#sequenceTable tbody tr');
											var columns;
											for (var i = 0; i < rows.length; i++) {
												columns = $(rows[i]).find('td');
												var sequence = parseInt($(
														columns[0]).html());
												var userName = $(
														"#text" + (i + 1))
														.val();
												var applicationRef = $(
														'#applicationRef')
														.text();
												var requirement = {
													"sequence" : sequence,
													"userName" : userName,
													"applicationRef" : applicationRef
												};
												console.log(requirement);
												requirements.push(requirement);
											}
											requirements = JSON
													.stringify({
														'requirementList' : requirements
													});
											$
													.ajax({
														url : "/VendorApplication-0.0.1-SNAPSHOT/setVetters",
														type : 'POST',
														data : requirements,
														dataType : "html",
														contentType : 'application/json',
														success : function(
																response) {
															if (response === "success")
																window.location.href = "http://localhost:8080/VendorApplication-0.0.1-SNAPSHOT/dashboardcompany";
															else
																alert("error");
														}
													});
										});
					});
</script>
</html>