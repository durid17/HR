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
<link rel="stylesheet" href="../CSS/SettingsStyle.css">

</head>
<body>
		<div id = "header"> </div>
		
		<div class = "leftMenu"> 
			<label for="account">Account</label>
			<a class ="active" href="#">Username and Password</a>
		    <a href="#">Personal Info</a>
		</div>
		
		<div class = "right"> 
			<label for="username">Username</label>
			<label for="password">Password</label>
			<label for="email">Email</label>
		</div>
		
		
</body>
</html>