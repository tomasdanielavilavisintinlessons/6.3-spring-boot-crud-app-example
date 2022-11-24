<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script>
	$(document).ready(function(){
		$("#iscriviti").on('click', () => {
			let user = {
					username: $("#username").val(),
					password: $("#password").val()
			}
			console.log("parte request")
			$.ajax({
		        url: "doSignIn",
		        type: 'POST',
		        data: JSON.stringify(user),
		        dataType: 'json',
		        contentType: 'application/json',
		        success: function(res) {
		        	console.log(res)
		        	if(res.esito == "alreadyExists") {
		        		alert("Esiste già un utente con questo nome.")
		        	}
		        	else {
		        		window.location.replace("http://localhost:8080/login")
		        	}
		        }
		    });
		})
	})
	</script>
</head>
<body>
<p>Iscriviti:</p>
   
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' id='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' id='password' /></td>
         </tr>
         <tr>
            <td><button id="iscriviti">Iscriviti</button></td>
         </tr>
      </table>

</body>
</html>