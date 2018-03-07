<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="error-template">
				<h1>Oops!</h1>
				<h2>${message}</h2>
				<div class="error-actions">
					<a href="${header.referer}" class="btn btn-primary btn-lg">
						Return to Previous Page </a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>