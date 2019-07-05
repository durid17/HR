<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Settings-Info_Company</title>

<jsp:include page="Header.jsp" />
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SettingsStyle.css">

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	Company company = manager.getCompany(acc.getID());
	CompanyProfile profile = company.getProfile();
			
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
		
		<div class = "leftMenu"> 
			<label for="account">Account</label>
			<a  href="${pageContext.request.contextPath}/JSP/Settings.jsp">Username and Password</a>
		    <a class ="active" href="${pageContext.request.contextPath}/JSP/Settings-Info-Company.jsp" id="link2">Personal Info</a>
		</div>
		
		  <div class="right">
		  	<form action = "${pageContext.request.contextPath}/UpdateInfoCompany" method ="post">
	    
			   	 <label for="name"><b>Name</b></label>
			    <input type="text"  class="inp" name = "name" value=<%=profile.getName()%> required>
			    
			    <label for="email"><b>Email</b></label>
			    <input type="text"  class="inp" name = "email"  value=<%=profile.getEmail()%> required>
			   		    
			 
			     <label for="description"><b>Description</b></label>
			    <textarea name="description"  cols="60"><%=profile.getDescription()%></textarea>
			    
			     <label for="image"><b>Image</b></label>
			    <input type="text"  class="inp" name = "image"  value=<%=profile.getLogo()%> required>

			  
			    <hr>
			   
			    <button type="submit" class="submitButton">Save Changes</button>
	  		</form>
  		 </div>
		
	
		
		
</body>
</html>