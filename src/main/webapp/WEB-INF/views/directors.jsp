<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Directors list</title>
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
		
			let DIRECTORS_ENDPOINT = "director/getDirectors";
			let CREATE_DIRECTOR_ENDPOINT = "director/createDirector";
			let UPDATE_DIRECTOR_ENDPOINT = "director/updateDirector";
			let DELETE_DIRECTOR_ENDPOINT = "director/deleteDirector";
			let FILTER_DIRECTOR_ENDPOINT = "director/filterDirectors";
			
			let directorsList;
			
			$(document).ready(function(){
				
				startUp();
				
				// Dialog per creare e updatare registi
			    $("#addOrUpdateDialog" ).dialog({
			    	autoOpen: false
			    });
			    
				// Dialog per eliminare regista
				$("#deleteDirectorDialog" ).dialog({
			    	autoOpen: false
			    });
				
			    $("#addDirector").on('click', () => {
			        $("#dialogButtons").html('<button type="button" id="save">Save</button><button type="button" id="cancel">Cancel</button>');
			    	$( "#addOrUpdateDialog" ).dialog( "open" );
			    })
			    
			    $(document).on('click', 'button#save', () => {
			    	let newDirector = {
			    			name:  $("#director_name").val()
			    	};
			    	
			    	$.ajax({
				        url: CREATE_DIRECTOR_ENDPOINT,
				        type: 'POST',
				        data: JSON.stringify(newDirector),
				        dataType: 'json',
				        contentType: 'application/json',
				        success: function(res) {
				        	populateTalbe();
				            alert("Director created");
				            cleanDialog()
				        }
				    });
			    	
			    })
			    
			    // Pulsante update del dialog
			     $(document).on('click', 'button#update', () => {
			    	let updatedDirector = {
			    			id: $("#director_id").val(),
			    			name:  $("#director_name").val()
			    	};
			    	
			    	$.ajax({
				        url: UPDATE_DIRECTOR_ENDPOINT,
				        type: 'POST',
				        data: JSON.stringify(updatedDirector),
				        dataType: 'json',
				        contentType: 'application/json',
				        success: function(res) {
				        	populateTalbe();
				            alert("Director updated");
				            cleanDialog()     
				        }
				    });
			    })
			    
			    // Pulsante delete dialog
			    $(document).on('click', 'button#delete', () => {
			    	$.ajax({
				        url: `\${DELETE_DIRECTOR_ENDPOINT}?directorId=\${$("#director_id_delete").val()}`,
				        type: 'DELETE',
				        dataType: 'json',
				        contentType: 'application/json',
				        success: function(res) {
				        	populateTalbe();
				            alert("Director deleted.");
				            cleanDialog()     
				        }
				    });
			    })
			    
			    // Click su icona modifica della tabella
			    $(document).on('click', 'td.edit_director', (event) => {
			    	event.stopPropagation();
			        event.stopImmediatePropagation();
			        
			        directorId = $(event.currentTarget).attr("key");
			        $("#dialogButtons").html('<button type="button" id="update">Update</button><button type="button" id="cancel">Cancel</button>');
			        $("#director_name").val(directorsList[parseInt($(event.currentTarget).attr("index"))].name)
			        $("#director_id").val(parseInt(directorId))

			        $( "#addOrUpdateDialog" ).dialog( "open" );
			        
			    })
			    
			    // Click su nome regista
			    $(document).on('click', 'td.details_director', (event) => {
			    	event.stopPropagation();
			        event.stopImmediatePropagation();
			        
			        directorId = $(event.currentTarget).attr("key");
			        window.location.href = `director/\${directorId}`
			       

			        
			    })
			    
			    
			    
			    // Click su icona cancella della tabella
			    $(document).on('click', 'td.delete_director', (event) => {
			    	event.stopPropagation();
			        event.stopImmediatePropagation();
			        
			        directorId = $(event.currentTarget).attr("key");
			        $("#director_id_delete").val(parseInt(directorId))
			        $("#directorToDelete").html(directorsList[parseInt($(event.currentTarget).attr("index"))].name)

			        $( "#deleteDirectorDialog" ).dialog( "open" );
			    })
		
			    // Searchbar
			    $("#searchBar").keyup(throttle(()=> {
			    	populateTalbe();
			    }, 1000))
			    
			});
			
			// Funzione per temo onkeyup
			function throttle(f, delay){
			    var timer = null;
			    return function(){
			        var context = this, args = arguments;
			        clearTimeout(timer);
			        timer = window.setTimeout(function(){
			            f.apply(context, args);
			        },
			        delay || 500);
			    };
			}
			
			function startUp() {
				populateTalbe();
			}
			
			function populateTalbe() {
				$.ajax({
			        url: `\${FILTER_DIRECTOR_ENDPOINT}?q=\${$("#searchBar").val()}`,
			        type: 'GET',
			        dataType: 'json', // added data type
			        success: function(res) {
			        	$("#directorsTable tbody").html("");
			            directorsList = res;
			            
			            directorsList.forEach((director,i) => {
			            	let row = `<tr><td class="details_director" style="cursor:pointer;" key="\${director.id}">\${director.name}</td><td><ul>`
			            	
			            	director.movies.forEach(movie => {
			            		row += `<li>\${movie.title}</li>`
			            	})
			            	row += `</ul></td><td class="edit_director" key="\${director.id}" index="\${i}" style="cursor:pointer;"><i class="glyphicon glyphicon-pencil" ></i></td><td class="delete_director" key="\${director.id}" index="\${i}" style="cursor:pointer;"><i style="color:red;" class="glyphicon glyphicon-trash"></i></td></tr>`;
			            	$("#directorsTable tbody").append(row);
			            })
			        }
			    });
			}
			
			function cleanDialog() {
		        $("#dialogButtons").html("");
		        $("#director_name").val("")
		        $("#director_id").val("")
				$( "#addOrUpdateDialog" ).dialog( "close" );
		        $( "#deleteDirectorDialog" ).dialog( "close" );
			}
		</script>
</head>
</head>
<body>
	<h1>Directors list:</h1>
	<div style="margin-top:20px; padding-left:30px;">
		<button type="button" id="addDirector">Add director</button>
		<br>
		Search director: <input type="text" id="searchBar" />
	</div>
	<table id="directorsTable">
		<thead>
			<tr>
				<th>Name</th>
				<th>Movies</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>




	<div id="addOrUpdateDialog" title="Basic dialog">
		<p>Inserisci i dati del regista:</p>
		<form>
			<input type="number" hidden=true id="director_id" disabled> <input
				type="text" id="director_name" class="dialogInput" required
				placeholder="Director name...">
			<div id="dialogButtons">
				<button type="button" id="save">Save</button>
				<button type="button" id="cancel">Cancel</button>
			</div>
		</form>
	</div>
	
	<div id="deleteDirectorDialog" title="Basic dialog">
		<p>Are you sure you want to delete director: <span id="directorToDelete"></span>?</p>
		<input type="number" hidden=true id="director_id_delete" disabled>
			<div id="dialogDeleteButtons">
				<button type="button" id="delete">Delete</button>
				<button type="button" id="cancelDelete">Cancel</button>
			</div>
	</div>
</body>
</html>