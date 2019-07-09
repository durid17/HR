<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Work Experience</title>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
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
		    <a href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp" id="link">Personal Info</a>
		    <a class ="active"   href="${pageContext.request.contextPath}/JSP/AddWorkExperience.jsp" id="link">Add Work Experience</a>
		    <a href="${pageContext.request.contextPath}/JSP/AddEducation.jsp" id="link">Add Education</a>
		   
		</div>
		
		  <div class="right">
		  	<form action = "${pageContext.request.contextPath}/AddWorkExperience" method ="post">
	
			    <label for="company"><b>Company *</b></label>
			    <textarea class="area" name="company"></textarea>
			    
			     <label for="position"><b>Position *</b></label>
			    <textarea class="area" name="position"></textarea>
			  
			  	<p>Please select employement type:</p>
 				 	<input type="radio" name="emptype" value="Full-Time" checked> Full-Time <br>
		 			<input type="radio" name="emptype" value="Part-Time"> Part-Time<br>
		 			<input type="radio" name="emptype" value="Internship"> Internship<br>  
  				<br>
  				
  				 <label for="duty"><b>Duty</b></label>
			    <textarea class="area" name="duty"></textarea><br><br>
			    
			    <label for="achievement" ><b>Achievement</b></label>
			    <textarea class="area" name="achievement"></textarea><br><br>
			    
  				Start Date *: <input type="date" class="inp" name="start" > <br><br>
  				End Date *: <input type="date" class="inp" name="end" > <br><br>
  			
			    <hr>
			   
			    <button type="submit" class="submitButton">Save Changes</button>
	  		</form>
  		 </div>
		
	
		
		
</body>
</html>