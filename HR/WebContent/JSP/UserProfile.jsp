<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
<%@ page import="classes.Account, classes.DBManager, classes.Employee, 
	classes.WorkExperience,
classes.EmployeeProfile, classes.EmployeeEducation, java.util.ArrayList" %>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User profile</title>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	
	String ida = request.getParameter("id"); //Stalking
	Employee employee; 
	/*
	if(ida != null){ //Another user's page
		int id = Integer.parseInt(ida);
		employee = manager.getEmployee(id);
	} else {
		employee = manager.getEmployee(acc.getID());
	}
	//education
	EmployeeProfile profile = employee.getProfile();
	*/
	employee = manager.getEmployee(13);
	EmployeeProfile profile = employee.getProfile();
	System.out.println("Image " + profile.getProfilePicture());
	//Educ1
	EmployeeEducation educ = new EmployeeEducation(13, null,  null, "School", "Tbilisi I. Vekua Physics-Mathematical School 42",
			"Math", null, "Medium", 97);
	//Educ2
	EmployeeEducation educ2 = new EmployeeEducation(13, null, null, "University", "Free University of Tbilisi", "Computer Science", null, "Bachelor", 98);

	
	List<EmployeeEducation> educationList = new ArrayList<>();
	educationList.add(educ);
	educationList.add(educ2);
	request.setAttribute("educations", educationList);
	
	
	//Work1
	WorkExperience work = new WorkExperience(13, null, null, "Google", null, "Software Engineer", null, null, null);
	//Work2
	WorkExperience work2 = new WorkExperience(13, null, null, "Buckswood", null, "Leader", null, null, null);
	List<WorkExperience> workList = new ArrayList<>();
	workList.add(work);
	workList.add(work2);
	request.setAttribute("works", workList);
	
%> 

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/UserProfileStyle.css">

<body>
 	
	<div id = "header"> </div>
	<div class = "container">
		<div class = "profileInfoLeft">
			<div class = "imgAvatar">
				<img id = "profileImage" src=<%=profile.getProfilePicture()%> alt="Avatar">
			</div>	    
		    <div class="mainInfo">
		    	<h4><b></b></h4>  
		    	<h3> <%= profile.getName() + " " + profile.getSurname()%></h3> 
		    	<p> @<%= acc.getUsername()%></p> 
		 	 	<hr>
		   		<p><%= profile.getMajorProfession() %></p> 
			</div>
		 
		</div>
		
		
		<div class = "rightSide">
		
			<div class = "about">
				<h4 id="title">About</h4>
				<ul>
					<li>  <p>Gender - <%=profile.getGender()%> </p></li>
					<li>  <p>Age - 20 </p></li>
					<li>  <p>Major Profession - <%=profile.getMajorProfession() %></p></li>
					<li>  <p>Minor Profession - <%=profile.getMinorProfession() %></p></li>
					<li>  <p>Email - <%=profile.getEmail() %></p></li>
					<li>  <p>Phone Number - <%=profile.getPhoneNumber() %></p></li>
					<li>  <p>Address - <%=profile.getAddress() %></p></li>
					<li><p> Other - <%=profile.getDescription() %><p></li>
				</ul>	
			</div>
		
		
			<div id="botoom">
				<div class = "workExpereience" id="bla"> 
						<h4 id="title">Work Experience</h4>
						<ul>
							<c:forEach var="work" items="${works}">
								<li >  
									<h4> ${work.getPostition()} </h4>
									<p> ${work.getCompanyName()} |  </p>
								</li>
								
							</c:forEach>
						</ul>
					</div>
					
					
					<div  class = "educationClass" id="bla"> 
					<h4 id="title">Education</h4>
						<ul>
							<c:forEach var="education" items="${educations}">
								<li>  
									<h4> ${education.getInstitutionName()} </h4>
									<p> ${education.getMajor()} | ${education.getStartDate()} - ${education.getEndDate().getYear()} </p>
								</li>
								
							</c:forEach>
						</ul>
					</div>
			</div>
		</div>>
	</div>
	
	
	
	
	

</body>
</html>