<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

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
		    <a id="link" href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp">Personal Info</a>
		    
		    <% if(acc.getAccountType().equals(Account.COMPANY_ACCOUNT_TYPE)) { %>
		    	<script>
		    	 	document.getElementById("link").href = "Settings-Info-Company.jsp";
		    	</script>
		    <%} %>
		     
		     
		</div>
		
		<div class="right">
		  	<form  action="${pageContext.request.contextPath}/UpdatePassword" method="post" >
	
			    <label for="username">Username - <%=acc.getUsername()%></label>
			   
			  
			    <input type="password"  class="inp" name = "newPassword" placeholder="Enter new Password" required>

				  				
			    <hr>
			   
			    <button onclick	="update()" type="submit" id="btn" class="submitButton">Save Changes</button>
	  		</form>
	  		
	  		
	  		
	  		<!-- 
	  		<script>
	  		console.log("Mevei javascriptshi");
				function update() {
					document.getElementById("label").style.visibility = "visible";
				}
				
				$.post("././UpdatePassword", 
						{
						newPassword: $('.inp').val(),
						},
						function(response){
							
						}
				);
			</script>
  		  -->
  		 </div>
		
		
</body>
</html>