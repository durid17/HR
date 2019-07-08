<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, java.util.List, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Settings-Info</title>


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
		    <a class ="active"   href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp" id="link">Personal Info</a>
		    <a href="${pageContext.request.contextPath}/JSP/AddWorkExperience.jsp" id="link">Add Work Experience</a>
		    <a href="${pageContext.request.contextPath}/JSP/AddEducation.jsp" id="link">Add Education</a>
		</div>
		
		  <div class="right">
		  	<form action = "${pageContext.request.contextPath}/UpdateInfo" method ="post">
	
			    <label for="firstName"><b>First Name</b></label>
			    <textarea class="area" name="firstName"><%=profile.getName()%></textarea>
			  
			    <label for="lastName"><b>Last Name</b></label>
			    <textarea class="area" name="lastName"><%=profile.getSurname()%></textarea>
			    
			    <label for="email"><b>Email</b></label>
			     <textarea class="area" name="email"><%=profile.getEmail()%></textarea>
			    
			  
  				<br>
  				Birthday: <input type="date" class="inp" name="bday" value=<%=profile.getBirthDate() %>> <br>
  				
			    <label for="majorProfession"><b>Major Profession</b></label>
			     <textarea class="area" name="majorProfession"><%=profile.getMajorProfession()%></textarea>
			    
			    <label for="phoneNumber"><b>Phone Number</b></label>
			    <input type="number"  class="inp" name = "phoneNumber"  value=<%=profile.getPhoneNumber()%> required>
			    
			     <label for="address"><b>Address</b></label>
			    <textarea class="area" name="address"><%=profile.getAddress()%></textarea>
			    
			  
			   
			    <label >About</label>
			    <textarea  class="area"style=" resize: none;" name="description"><%=profile.getDescription()%></textarea>
				
				 <label for="image"><b>Image</b></label>
			    <input type="text"  class="inp" name = "image"  value=<%=profile.getProfilePicture()%> required>
			    
				  				
			    <hr>
			   
		
				
			    <button type="submit" class="submitButton">Save Changes</button>
	  		</form>
  		 </div>
		
	
		
		
</body>
</html>