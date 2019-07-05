<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Settings-Info</title>

<jsp:include page="Header.jsp" />
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SettingsStyle.css">

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	Employee employee = manager.getEmployee(acc.getID());
	EmployeeProfile profile = employee.getProfile();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
		
		<div class = "leftMenu"> 
			<label for="account">Account</label>
			<a href="${pageContext.request.contextPath}/JSP/Settings.jsp">Username and Password</a>
		    <a class ="active"   href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp" id="link">Personal Info</a>
		   
		</div>
		
		  <div class="right">
		  	<form action = "${pageContext.request.contextPath}/UpdateInfo" method ="post">
	
			    <label for="firstName"><b>First Name</b></label>
			    <input type="text"  class="inp" name = "firstName" value=<%=profile.getName()%> required>
			    
			    <label for="lastName"><b>Last Name</b></label>
			    <input type="text"  class="inp" name = "lastName" value=<%=profile.getSurname()%> required>
			    
			    <label for="email"><b>Email</b></label>
			    <input type="text"  class="inp" name = "email"  value=<%=profile.getEmail()%> required>
			    
			    <label for="majorProfession"><b>Major Profession</b></label>
			    <input type="text"  class="inp" name = "majorProfession"  value=<%=profile.getMajorProfession()%> required>
				
				 <label for="image"><b>Image</b></label>
			    <input type="text"  class="inp" name = "image"  value=<%=profile.getProfilePicture()%> required>
				  				
			    <hr>
			   
			    <button type="submit" class="submitButton">Save Changes</button>
	  		</form>
  		 </div>
		
	
		
		
</body>
</html>