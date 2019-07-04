<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/UserProfileStyle.css">

<style>
body {
	
}
div{
	border: none;
	border-radius: 2px;
}

.container {
	max-width: 80%;
	max-height: 70%;
	padding: 10px;
	background-color: green;
}

.card {
	max-width: 30%;
	max-height: 30%;
	padding: 15px;
	background-color: red;
	
}

#profileImage {
	
}
</style>

<body>	
	<div id = "header"> </div>
	
	
	<div class = "container">
		<div class = "card">
			<div class = "imgAvatar">
				<img id = "profileImage" src="${pageContext.request.contextPath}/Images/fb.png" alt="Avatar">
			</div>
			
			<label> Facebook </label>
			<label> Social Network </label>
		    
		   
		</div>
		
		
	
	</div>
	
	
	
	

</body>
</html>