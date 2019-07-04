<%-- <%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="classes.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Vacancy</title>
</head>

<!--  
-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyAdd.css">


<body>
	
<form action = "${pageContext.request.contextPath}/???" method ="post">
  <div class="container">
    <h1>Add Vacancy</h1>
    <p>Please fill in this form to add a vacancy.</p>
    <hr>
    
    <label for="heading"><b>Heading *</b></label>
    <input type="text" placeholder="heading" name="heading" required>
    
    <label for="position"><b>Position *</b></label>
    <input type="text" placeholder="position" name="position" required>
   
    <label for="description"><b>Description *</b></label>
    <input type="text" placeholder="description" name="description" required>
    
    <label for="jobType"><b>Job Type</b></label>
    <select>
	  <option value="Full-time">Full-time</option>
	  <option value="Part-time">Part-time</option>
	  <option value="Intern"> Intern </option>
	  <option value="Temporary">Temporary</option>
	</select><br>
	
	<label for="endDate"><b>End Date</b></label>
	<input type="date"  name="end-date" required><br>
	
	<label for="yearsofExperience"><b>Years of Experience</b></label>
	<input type="number"  name="Years of Experience"><br>
	
	<label for="Degree"><b>Degree</b></label>
	<select>
	  <option value="Bachelor's">Bachelor's</option>
	  <option value="Associate">Associate</option>
	  <option value="Master's">Master's</option>
	  <option value="Ph.D">Ph.D</option>
	  <option value="Pursuing Degree">Pursuing Degree</option>
	</select><br>
	
	<% 
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		List<Tag> tags = manager.getLocations();
		List<String> locations = new ArrayList<String>();
		for(int i = 0 ; i < tags.size(); i++){
			locations.add(tags.get(i).getTagName());
		}
		locations.add("opa");
		%>
		<label for="Locations"><b>Locations</b></label>
		<select>		
		<%
			for(int i = 0 ; i < locations.size(); i++){
				out.print("<option value = \" ");
				out.print(locations.get(i) + "\">");
				out.print(locations.get(i) + "</option>");
			}		
		%>
		</select><br>
	<%
		for(int j = 1 ; j <= 3 ; j++){
			tags = manager.getLanguages();
			List<String> languages = new ArrayList<String>();
			for(int i = 0 ; i < tags.size(); i++){
				languages.add(tags.get(i).getTagName());
			}
			languages.add("opa");
			out.print("<label for = \" language" + j + " \"><b>language</b></label>");
	%>
			<select>		
	<%
				for(int i = 0 ; i < languages.size(); i++){
					out.print("<option value = \" ");
					out.print(languages.get(i) + "\">");
					out.print(languages.get(i) + "</option>");
				}
	%>
		</select><br>				
	<%
		}
	%>
		
    <hr>
    <button type="submit" class="addVacancy">Add Vacancy</button>
  </div>
  

</form>
	

</body>
</html> --%>