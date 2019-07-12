<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Login.css">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
	
	<h2>SIGN IN</h2>

	<form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
			<input type="text" name="username" class="input-field" placeholder=" Username"  required /> 
			<br>
			<br>
			
			<input type="password" id="myInput" name="password" class="input-field" placeholder=" Password"  required>
	
			<br> 
			<br>
				
			<button class ="button" id="log" type="submit">Login</button>
			<br> 
			<br>
			<br>
			
			Not registered? <a href="${pageContext.request.contextPath}/JSP/RegisterPage.jsp">Create an account</a> 
			<br><br>
			<%if(request.getAttribute("wrong") != null){ %>
				<label style="color: red">Wrong Username Or Password</label>
			<%} %>
	</form>

	

</body>
</html>
