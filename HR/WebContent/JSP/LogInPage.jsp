<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="../CSS/Login.css">


</head>
<body>
	
	<h2>SIGN IN</h2>

	<form action="LoginServlet" method="POST">
			<input type="text" name="username" class="input-field" placeholder=" Username"  required /> 
			<br>
			<br>
			
			<input type="password" name="password" class="input-field" placeholder=" Password"  required>
			<br> 
			<br>
				
			<button class ="button" id="log" type="submit">Login</button>
			<br> 
			<br>
			<br>
			
			Not registered? <a href="RegisterPage.jsp">Create an account</a> 
	</form>
	
	
	
	

</body>
</html>