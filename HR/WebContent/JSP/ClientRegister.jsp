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

	<label for="userName"><b>User Name *</b></label>
    <input type="text"  name="userName" required>
    
    <label for="email"><b>Email *</b></label>
    <input type="text" name="email" required>
   
    <label for="psw"><b>Password *</b></label>
    <input type="password" name="psw" required>
   
   
    <label for="firstName"><b>First Name *</b></label>
    <input type="text"  name="firstName" required>
    
     <label for="lastName"><b>Last Name *</b></label>
    <input type="text"  name="lastName" required>
   
    <label for = "gender"><b>Please select your gender:</b></label>
 	<input type="radio" name="gender" value="male"> Male
  	<input type="radio" name="gender" value="female"> Female
  	<br><br><br>   
  	
  	
  	<label for = "age"><b>Age:</b></label>
  	<input type="number" name="age" value="">
  	<br><br>
  	<!-- Countrys -->
	<label for = "country"><b>Country:</b></label>
	<select id="select">
	 <option id="country" value = "default">default</option>
	</select>
	
	<script>
            var select = document.getElementById("select"),
                     arr = ["Georgia","UK","USA","Italy","France",];
             
             for(var i = 0; i < arr.length; i++)
             {
                 var option = document.createElement("OPTION"),
                 txt = document.createTextNode(arr[i]);
                 option.appendChild(txt);
                 option.setAttribute("value",arr[i]);
                 select.insertBefore(option,select.lastChild);
             }
             
    </script>
        
      <br><br>
      
      <label for = "abut"><b>About:</b></label>
      <textarea name="comments" rows="3" maxlength="200" cols="60"></textarea>
        
	 
  	
    
    
    
    
    <hr>
    <button type="submit" class="registerbtn">Register</button>
  </div>
  

</form>

</body>
</html>