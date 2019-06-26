<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	Employee employee = manager.getEmployee(acc.getID());
	
	//education
	EmployeeProfile profile = employee.getProfile();
%>

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/UserProfileStyle.css">

<body>
 	
	<div id = "header"> </div>
	<div class = "container">
		<div class = "profileInfoLeft">
			<div class = "imgAvatar">
				<img id = "profileImage" src="${pageContext.request.contextPath}/Images/avatar.png" alt="Avatar">
			</div>
		    
		    <div class="mainInfo">
		    	<h4><b></b></h4>  
		    	<p> <%= profile.getMajorProfession() %></p> 
		  	</div>
		  	<hr>
		  	<div class = "other"> 
		  		<p> About <%= profile.getDescription() %></p> 
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