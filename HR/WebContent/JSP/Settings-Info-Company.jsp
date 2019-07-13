<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Settings-Info_Company</title>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp" />
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SettingsStyle.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />

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
	    
			   	<label for="name"><b>Name *</b></label>
			    <textarea class="area" name="name" required><%=profile.getName()%></textarea>
			    
			    <label for="name"><b>Field *</b></label>
			    <textarea class="area" name="essence" required><%=profile.getEssence()%></textarea>
			    
  				<label for="date"><b>Founded</b></label>
  				<input type="date" class="inp" name="date" value=<%=profile.getFounded() %>> <br>
  				
			    <label for="email"><b>Email *</b></label>
			    <textarea class="area" name="email" required><%=profile.getEmail()%></textarea>
			   		    
			   	<label for="phoneNumber"><b>Phone Number *</b></label>
			    <input type="number"  class="inp" name = "phoneNumber"  value=<%=profile.getPhoneNumber()%> required>
			 
			   	<label for="address"><b>Address</b></label>
			    <textarea class="area" name="address"><%=profile.getAddress()%></textarea>
			    
			    <label><b>About</b></label>
			    <textarea  class="area"style=" resize: vertical;" name="description"><%=profile.getDescription()%></textarea>
			    
			    <label for="image"><b>Image *</b></label>
			    <input type="text"  class="inp" name = "image"  value=<%=profile.getLogo()%> required>
			  
			    <hr>
			   
			    <button type="submit" class="submitButton">Save Changes</button> <br><br>
	  		</form>
  		 </div>
</body>
</html>