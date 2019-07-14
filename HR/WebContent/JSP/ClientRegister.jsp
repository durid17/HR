<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompanyRegisterStyle.css">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">

<body>
<form action = "${pageContext.request.contextPath}/ClientRegister" method ="post" >

  <div class="container">
    <h1>Register 
    <%if(request.getAttribute("used") != null){ %>
		<h3 style="color: red"> User name is already taken </h3>
	<%} %>
	</h1> 
    <p>Please fill in this form to create an account.</p>
    <hr>

	<label for="userName"><b>User Name </b></label>
    <input type="text"  name="userName" required>
    
    <label for="psw"><b>Password </b></label>
 	<input name="psw" id="password" type="password" required/>
	
    <label for="email"><b>Email </b></label>
    <input type="text" name="email" required>
   
    <label for="firstName"><b>First Name </b></label>
    <input type="text"  name="firstName" required>
    
     <label for="lastName"><b>Last Name </b></label>
    <input type="text"  name="lastName" required>
   
    <hr>
    <button type="submit" id="subBtn" class="registerbtn" >Register</button>
 
  </div>
  		

  </form>


</body>
</html>