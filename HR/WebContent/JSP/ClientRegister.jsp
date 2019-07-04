<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompanyRegisterStyle.css">

<body>

<form action = "${pageContext.request.contextPath}/ClientRegister" method ="post">

  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account. Required fields are marked with an asterisk (*).</p>
    <hr>

	<label for="userName"><b>User Name </b></label>
    <input type="text"  name="userName" required>
    
    <label for="psw"><b>Password </b></label>
    <input type="password" name="psw" required>
    
    <label for="email"><b>Email </b></label>
    <input type="text" name="email" required>
   
    <label for="firstName"><b>First Name </b></label>
    <input type="text"  name="firstName" required>
    
     <label for="lastName"><b>Last Name </b></label>
    <input type="text"  name="lastName" required>
   
    <hr>
    <button type="submit" class="registerbtn">Register</button>
  </div>
  

</form>

</body>
</html>