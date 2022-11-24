<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.z9devs.entities.Director" %>
    
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
	let CREATE_MOVIE_ENDPOINT = "/movie/addMovie";
	let DIRECTOR_MOVIES_ENDPOINT = "/movie/movieForDirector"
	
	$(document).ready(function(){ 
		
		startUp();
		
		$("#addMovieDialog" ).dialog({
	    	autoOpen: false
	    });
		
		$("#addMovie").on('click', () => {
			$("#movie_title").val("");
			$("#movie_year").val("");
			$("#addMovieDialog").dialog("open");
		})
		
		$("#save").on('click', () => {
			let newMovie = {
					title: $("#movie_title").val(),
					year: $("#movie_year").val(),
					director: {
						id: ${directorData.id}
					}
			}
			
			$.ajax({
		        url: CREATE_MOVIE_ENDPOINT,
		        type: 'POST',
		        data: JSON.stringify(newMovie),
		        dataType: 'json',
		        contentType: 'application/json',
		        success: function(res) {
		            alert("Movie created");
		            populateMovieList()
					$("#addMovieDialog").dialog("close");
		            $("#movie_title").val("");
					$("#movie_year").val("");

		        }
		    });
		})
	})
	
	function populateMovieList() {
		$.ajax({
	        url: `\${DIRECTOR_MOVIES_ENDPOINT}?directorId=${directorData.id}`,
	        type: 'GET',
	        dataType: 'json',
	        contentType: 'application/json',
	        success: function(res) {
	        	console.log(res)
	        	let movieList ="";
	        	$("#movieList").html("")

	        	res.forEach(mov => {
	        		movieList += `<li>\${mov.title} (\${mov.year})</li>`
	        	})
	        	$("#movieList").html(movieList)
	        }
	    });
		
	}
	
	function startUp() {
		populateMovieList();	
	}
</script>
</head>
<body>
<h1>${directorData.name}</h1>
<button type="button" id="addMovie">Add Movie</button>
<p>Lista film:</p>
<ul id="movieList">
	<%
		if(((Director)request.getAttribute("directorData")).getMovies() != null) {
	%>
	
	<% } %>
</ul>

<div id="addMovieDialog" title="Basic dialog">
		<p>Inserisci i dati del regista:</p>
		<form>
			<input
				type="text" id="movie_title" class="dialogInput" required
				placeholder="Movie title...">
			<input
				type="number" id="movie_year" class="dialogInput" required
				placeholder="Movie year...">
			<div id="dialogButtons">
				<button type="button" id="save">Save</button>
				<button type="button" id="cancel">Cancel</button>
			</div>
		</form>
</div>
</body>
</html>