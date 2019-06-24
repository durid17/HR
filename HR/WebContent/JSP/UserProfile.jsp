<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function() {
		$("#header").load("Header.jsp");
	});
</script>

</head>
<link rel="stylesheet" href="../CSS/UserProfileStyle.css">



<body>
 	
	<div id = "header"> </div>
	<div class = "container">
		<div class = "profileInfoLeft">
			<div class = "imgAvatar">
				<img id = "profileImage" src="../Images/avatar.png" alt="Avatar">
			</div>
		    
		    <div class="mainInfo">
		    	<h4><b></b></h4> 
		    	<p> Software Engineer</p> 
		  	</div>
		  	<hr>
		  	<div class = "other">
		  		<p> About</p> 
		  	</div>	  	
		</div>
		
		
		<div class = "workExpereience" > 
			<p>Work Experience</p>
		</div>
		
		<div class = "education" > 
			<p>Education</p>
		</div>
	</div>
	
	
	
	
	

</body>
</html>