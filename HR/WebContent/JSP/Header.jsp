<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="classes.Account"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/HeaderStyle.css">

</head>


<body>
	
	<div class="navbar">
		<div class="normButtons">
			<a href="${pageContext.request.contextPath}/JSP/MainPage.jsp">HR</a> 
			<a href="${pageContext.request.contextPath}/JSP/MainPage.jsp">Home</a> 
			<a href="${pageContext.request.contextPath}/JSP/Contact.jsp">Contact</a> 
			<a href="${pageContext.request.contextPath}/JSP/About.jsp">About</a>
			<a href="${pageContext.request.contextPath}/JSP/CompaniesListPage.jsp">Companies</a>
		</div>
		
		<% 	Account acc = (Account)request.getSession().getAttribute("account");
			if(request.getSession().getAttribute("account") != null) { %>
				<div class="dropdown">
		   			 <button class="dropbtn"><%=acc.getUsername()%> <i class="arrow down"></i> </button>
		    			<div class="dropdown-content" style="right:0">
					     	
					      <%if(acc.getAccountType().equals(Account.EMPLOYEE_ACCOUNT_TYPE)) { %>
					       <a href="${pageContext.request.contextPath}/JSP/UserProfile.jsp">Profile</a>
					      <%} else { %>
					      	   <a href="${pageContext.request.contextPath}/JSP/CompanyProfile.jsp">Profile</a>
					      <%} %>
					      
					     
					      <a href="${pageContext.request.contextPath}/JSP/Settings.jsp">Settings</a>
					     
					       <a href="${pageContext.request.contextPath}/logOut">Log Out</a>
					    </div>
		  		</div>
  		
  		<% } else { %>
  				<a style="float:right; right:10px; position: absolute;" href="${pageContext.request.contextPath}/JSP/LogInPage.jsp">Log In</a>
  		 <% } %>
  			 	
	</div>
	
	


</body>
</html>