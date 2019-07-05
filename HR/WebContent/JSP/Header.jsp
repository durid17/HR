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


<body style="margin:0">
	
	<div class="header123" >
		<div class="normButtons123">
			<a class="left123" href="${pageContext.request.contextPath}/JSP/MainPage.jsp">HR</a> 
			<a class="left123" href="${pageContext.request.contextPath}/JSP/MainPage.jsp">Home</a> 
			<a class="left123" href="${pageContext.request.contextPath}/JSP/Contact.jsp">Contact</a> 
			<a class="left123" href="${pageContext.request.contextPath}/JSP/About.jsp">About</a>
			<a class="left123" href="${pageContext.request.contextPath}/JSP/CompaniesListPage.jsp">Companies</a>
			<a class="left123" href="${pageContext.request.contextPath}/VacanciesServlet">Vacancies</a>
			<a class="left123" href="${pageContext.request.contextPath}/JSP/UserCardList.jsp">Users</a>
		</div>
		
		<% 	Account acc = (Account)request.getSession().getAttribute("account");
			if(request.getSession().getAttribute("account") != null) { %>
				<div class="dropdown123">
		   			 <button class="dropbtn123"><%=acc.getUsername()%> <i class="arrowdown123"></i> </button>
		    			<div class="dropdown-content123" style="right:0">
					     	
					      <%if(acc.getAccountType().equals(Account.EMPLOYEE_ACCOUNT_TYPE)) { %>
					       <a class="right123"  href="${pageContext.request.contextPath}/JSP/UserProfile.jsp">Profile</a>
					      <%} else { %>
					      	   <a class="right123" href="${pageContext.request.contextPath}/JSP/CompanyProfile.jsp">Profile</a>
					      <%} %>
					      
					     
					      <a class="right123" href="${pageContext.request.contextPath}/JSP/Settings.jsp">Settings</a>
					     
					       <a class="right123" href="${pageContext.request.contextPath}/logOut">Log Out</a>
					    </div>
		  		</div>
  		
  		<% } else { %>
  				<a class="left123" style="float:right; right:10px; position: absolute;" href="${pageContext.request.contextPath}/JSP/LogInPage.jsp">Log In</a>
  		 <% } %>
  			 	
	</div>
	
	


</body>
</html>