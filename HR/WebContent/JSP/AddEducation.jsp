<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, java.util.List, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile"
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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
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
		     <a href="${pageContext.request.contextPath}/JSP/AddWorkExperience.jsp" id="link">Add Work Experience</a>
		    <a class ="active" href="${pageContext.request.contextPath}/JSP/AddEducation.jsp" id="link">Add Education</a>
		   
		</div>
		
		  <div class="right">
		  	<form action = "${pageContext.request.contextPath}/AddEducation" method ="post">
	
			    <label for="institution"><b>Institution</b></label>
			    <textarea class="area" name="institution"></textarea>
			    
			     <label for="major"><b>Major</b></label>
			    <select id = "major" name="major">
			    <%
					List<String> majors = manager.getProfessions();
			    	/*
			    	professions.add("prof1");
			    	professions.add("prof2");
			    	professions.add("prof3");
			    	professions.add("prof4");
			    	*/
			    	for(int i = 0 ; i < majors.size(); i++){
			    		out.print("<option value = \" ");
						out.print(majors.get(i) + "\">");
						out.print(majors.get(i) + "</option>");
			    	}
			    %>
			    </select><br>
			    
			     <label for="minor"><b>Minor</b></label>
			    <select id = "minor" name="minor">
			    <%
					List<String> minors = manager.getProfessions();
			    	/*
			    	professions.add("prof1");
			    	professions.add("prof2");
			    	professions.add("prof3");
			    	professions.add("prof4");
			    	*/
			    	for(int i = 0 ; i < minors.size(); i++){
			    		out.print("<option value = \" ");
						out.print(minors.get(i) + "\">");
						out.print(minors.get(i) + "</option>");
			    	}
			    %>
			    </select><br>
			     <label for="subject"><b>Subject</b></label>
			    <textarea class="area" name="subject"></textarea>
			  
			  	<label for="Degree"><b>Degree</b></label>
				<select id = degree name="degree">
				  <option value="Bachelor's">Bachelor's</option>
				  <option value="Associate">Associate</option>
				  <option value="Master's">Master's</option>
				  <option value="Ph.D">Ph.D</option>
				  <option value="Pursuing Degree">Pursuing Degree</option>
				</select><br>
  				
  				<br>
  				Start Date: <input type="date" class="inp" name="start" > <br><br>
  				End Date: <input type="date" class="inp" name="end" > <br><br>
  			
			    <hr>
			   
			    <button type="submit" class="submitButton">Save Changes</button>
	  		</form>
  		 </div>
		
	
		
		
</body>
</html>