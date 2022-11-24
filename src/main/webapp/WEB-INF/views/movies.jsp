<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Movie list</title>
		<link href="/css/global.css" rel="stylesheet"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script>
			$(document).ready(function(){
			    $("#addOrUpdateDialog" ).dialog({
			    	autoOpen: false
			    });
			    
			    $("#addMovie").on('click', () => {
			    	$( "#addOrUpdateDialog" ).dialog( "open" );
			    })
			    
			});
		</script>
	</head>
	<body>
		<h1>Lista film, tramite ${method}</h1>
		<div>
			<button type="button" id="addMovie">Add movie</button>
		</div>
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Year</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mov}" var="film">
					<tr>
						<td>${film.title}</td>
						<td>${film.year}</td>
						<td>
							<button type="button">Update</button>
						</td>
						<td>
							<button type="button">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div id="addOrUpdateDialog" title="Basic dialog">
			<p>Inserisci i dati del film:</p>
			<form>
				<input type="text" id="movie_title" class="dialogInput" required placeholder="Movie title...">	
				<input type="number" id="movie_year" class="dialogInput" required placeholder="Movie year...">
				<select id="movie_director" class="dialogInput" required>
					<option value="">Select a director...</option>
					<c:forEach items="${directors}" var="dir">
						<option value="${dir.id}">${dir.name}</option>
					</c:forEach>
				</select >
			</form>
		</div>
	</body>
</html>