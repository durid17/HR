<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp" />

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SettingsStyle.css">
<% 
	Account acc = (Account)request.getSession().getAttribute("account");
%>
</head>
<body>
		<div id = "header"> </div>
		
		<div class = "leftMenu"> 
			<label for="account">Account</label>
			<a class ="active" href="#">Username and Password</a>
		    
		    <% if(acc.getAccountType().equals(Account.COMPANY_ACCOUNT_TYPE)) { %>
		    		<a id="link" href="${pageContext.request.contextPath}/JSP/Settings-Info-Company.jsp">Personal Info</a>
		    <%} else { %>
		      		<a id="link" href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp">Personal Info</a>
		    		 <a href="${pageContext.request.contextPath}/JSP/AddWorkExperience.jsp" id="link">Add Work Experience</a>
		    		  <a href="${pageContext.request.contextPath}/JSP/AddEducation.jsp" id="link">Add Education</a>
		    <%} %>
		     
		     
		</div>
		
		<div class="right">
		  	<form  action="${pageContext.request.contextPath}/UpdatePassword" method="post" >
			    <label for="username">Username - <%=acc.getUsername()%></label>
			    <input type="password"  class="inp" name = "newPassword" placeholder="Enter new Password" required>
			    <hr>
			    <button onclick	="update()" type="submit" id="btn" class="submitButton">Save Changes</button>
	  		</form>
  		</div>
		
		
</body>
</html>