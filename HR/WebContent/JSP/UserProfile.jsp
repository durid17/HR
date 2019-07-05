<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User profile</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	
	String ida = request.getParameter("id"); //Stalking
	Employee employee; 

	if(ida != null){ //Another user's page
		int id = Integer.parseInt(ida);
		employee = manager.getEmployee(id);
	} else {
		employee = manager.getEmployee(acc.getID());
	}
	//education
	EmployeeProfile profile = employee.getProfile();
	System.out.println(profile.getProfilePicture());
	
%>

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/UserProfileStyle.css">

<body>
 	
	<div id = "header"> </div>
	<div class = "container">
		<div class = "profileInfoLeft">
			<div class = "imgAvatar">
				<img id = "profileImage" src=<%= profile.getProfilePicture()%> alt="Avatar">
			</div>
		    
		    <div class="mainInfo">
		    	<h4><b></b></h4>  
		    	<p> <%= profile.getName() + " " + profile.getSurname()%></p> 
		  	</div>
		  	<hr>
		  	<div class = "other"> 
		  		<p> About </p>
		  		<p><%= profile.getMajorProfession() %></p> 
		  	</div>	  	
		</div>
		
		
		<div class = "workExpereience" > 
			<p>Work Experience </p>
		</div>
		
		<div class = "education" > 
			<p>Education</p>
		</div>
	</div>
	
	
	
	
	

</body>
</html>