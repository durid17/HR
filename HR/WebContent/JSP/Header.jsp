<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>

<link rel="stylesheet" href="../CSS/HeaderStyle.css">

</head>


<body>
	
	<div class="navbar">
		<div class="normButtons">
			<a href="#Home Page">HR</a> 
			<a href="#home">Home</a> 
			<a href="#contact">Contact</a> 
			<a href="#about">About</a>
			<a href="#companies">Companies</a>
		</div>
		
		<div class="dropdown">
   			 <button class="dropbtn">User <i class="arrow down"></i> </button>
    			<div class="dropdown-content" style="right:0">
			      <a href="#UserProfile">Profile</a>
			      <a href="#">Settings</a>
			      <a href="MainPage.jsp">Log Out</a>
    			</div>
  		</div> 
	</div>
	
	


</body>
</html>