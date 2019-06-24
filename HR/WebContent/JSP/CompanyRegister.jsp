<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../CSS/CompanyRegisterStyle.css">
<body>

<form action="/action_page.php">
  <div class="container">
    <h1>Register</h1>
    <p>Please fill in this form to create an account. Required fields are marked with an asterisk (*).</p>
    <hr>

    <label for="email"><b>Email *</b></label>
    <input type="text" placeholder="Enter Email" name="email" required>
   
    <label for="psw"><b>Password *</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
   
    <label for="psw-repeat"><b>Repeat Password *</b></label>
    <input type="password" placeholder="Repeat Password" name="psw-repeat" required>
   
    <label for="companyName"><b>Company Name *</b></label>
    <input type="text"  name="companyName" required>
   
    <label for="description"><b>Company Description *</b></label>
    <input type="text"  name="description" required>
   
    
    
    
    
    <hr>
    <button type="submit" class="registerbtn">Register</button>
  </div>
  

</form>

</body>
</html>