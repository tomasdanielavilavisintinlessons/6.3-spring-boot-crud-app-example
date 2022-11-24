<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/global.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script>

</script>
</head>
<body>
	<h1>Spring Boot CRUD with Tomcat 10 and Apache24</h1>
	<p>Select the entity type:</p>
	<ul>
		<li><a href="movie/listJPA">Movies</a></li>
		<li><a href="director">Directors</a></li>
	</ul>
	
	<form method="POST" action="/logout">
		<button type="submit">Logout</button>
	</form>
</body>
</html>