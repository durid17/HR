<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.Account, classes.DBManager, classes.Company, classes.CompanyProfile" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company profile</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompanyProfileStyle.css">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp" />

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	
	String ida = request.getParameter("id"); //Stalking
	Company company; 
	
	if(ida != null){ //Another user's page
		int id = Integer.parseInt(ida);
		company = manager.getCompany(id);
	} else { 
		company = manager.getCompany(acc.getID());
	}
	
	CompanyProfile profile = company.getProfile();
%>

</head>


<body>
 	
	<div id = "header"> </div>
		<div class = "container">
			<div class = "profileInfoLeft">
				<div class = "imgAvatar">
					<img id = "profileImage" src=<%= profile.getLogo()%> alt="Avatar">
				</div>
		    
			    <div class="mainInfo">
			    	<p> <%= profile.getName()%></p> 
			  	</div>
			  			
			</div>	
			 
			
			<div class = "rightSide">
				<div class = "about">
					<h4 id="title">About</h4>
						<ul>
							<li><p>Name - <%=profile.getName() %></p></li>
							<%if(profile.getFounded() == null){ %>
								<li>  <p>Founded - </p></li>
							<%}else { %>
								<li>  <p>Founded - <%=profile.getFounded() %> </p></li>>
							<%} %>
							
							<li>  <p>Email - <%=profile.getEmail() %></p></li>
							<li>  <p>Phone Number - <%=profile.getPhoneNumber() %></p></li>
							<li>  <p>Address - <%=profile.getAddress() %></p></li>
							<li><p>Description - <%=profile.getDescription() %></p></li>
						</ul>	
				</div>
			</div>>		
		</div>
		
	
	
	
	

</body>
</html>