<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile, java.util.List"
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

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
			    <textarea class="area" name="company" required></textarea>
			    
			    <label for="profession"><b>Profession</b></label>
			    <select id = "profession" name="profession" class="selectpicker">
			    <%
					List<String> professions = manager.getProfessions();
			    	/*
			    	professions.add("prof1");
			    	professions.add("prof2");
			    	professions.add("prof3");
			    	professions.add("prof4");
			    	*/
			    	for(int i = 0 ; i < professions.size(); i++){
			    		out.print("<option value = \"");
						out.print(professions.get(i) + "\">");
						out.print(professions.get(i) + "</option>");
			    	}
			    %>
			    </select><br>
			    
			    <label for="position"><b>Position *</b></label>
			    <textarea class="area" name="position" required></textarea>
			  
			  	<label for="emptype"><b>Employment Type</b></label>
			  	<select id = "emptype" name = "emptype" class="selectpicker">
				  <option value="Full-time">Full-time</option>
				  <option value="Part-time">Part-time</option>
				  <option value="Intern">Intern</option>
				  <option value="Temporary">Temporary</option>
				</select><br>
  				
  				<label for="duty"><b>Duty *</b></label>
			    <textarea class="area" name="duty" required></textarea>
			    
			    <label for="achievement" ><b>Achievement</b></label>
			    <textarea class="area" name="achievement"></textarea>
			    
  				<label for="start" ><b>Start Date *</b></label>
  				<input type="date" class="inp" name="start" required >
  				
  				<label for="end" ><b>End Date *</b></label>
  				<input type="date" class="inp" name="end" required> <br><br>
  			
			    <hr>
			   
			    <button type="submit" class="submitButton">Add Work Experience</button> <br><br>
	  		</form>
  		 </div>
</body>
</html>