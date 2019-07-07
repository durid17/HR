<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="classes.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Vacancy</title>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyAdd.css">

<script>
	function toString(myStringArray){
		if(myStringArray == null) return "";
		var arrayLength = myStringArray.length;
		var res = "";
		for (var i = 0; i < arrayLength; i++) {
			res = res + myStringArray[i].trim() + ",";
			//console.log(myStringArray[i]);
		}
		return res;
	}
	$(document).ready(function(){
		$("#add").click(function(){
			var GivenDate = document.getElementById("endDate").value;
		    var CurrentDate = new Date();
		    //console.log("op");
		    //console.log(GivenDate);
		    if(GivenDate == "") return;
		    GivenDate = new Date(GivenDate);
		    if(document.getElementById("heading").value == ""){
		    	alert('Heading can not be empty');
		        return;
		    }
		    if(document.getElementById("position").value == ""){
		    	alert('Position can not be empty');
		        return;
		    }
		    
		    if(GivenDate < CurrentDate){
		        alert('Given date is not greater than the current date.');
		        return;
		    }
		    
			var data = {
		    	heading : document.getElementById("heading").value.trim() , 
		    	profession : document.getElementById("profession").options[document.getElementById("profession").selectedIndex].value.trim(),
		   	 	position : document.getElementById("position").value.trim() ,
		   	 	description : document.getElementById("description").value.trim() ,
		   	 	jobType : document.getElementById("jobType").options[document.getElementById("jobType").selectedIndex].value.trim(),
		   	 	endDate : document.getElementById("endDate").value.trim(),
		   		yearsOfExperience : document.getElementById("yearsOfExperience").value.trim(),
		   	 	degree : document.getElementById("degree").options[document.getElementById("degree").selectedIndex].value.trim(),
		   	 	location : document.getElementById("locations").options[document.getElementById("locations").selectedIndex].value.trim(),
		    	languages : toString($('#languages').val()),
		    	tags : toString($('#tags').val())
		    }
			var url = "../VacancyAddServlet";
			$.post(url,data, 
					function(data, status){
						var json = JSON.parse(data);
						console.log(json);
						var id = json.vacancyId;
		                location.href = "MyVacancy.jsp?id=" + id;
					}
			);
		});
	})
</script>

</head>

<body>
	
<!-- 
 <form action="">
 -->
 

	<div class="container">
    <h1>Add Vacancy</h1>
    <p>Please fill in this form to add a vacancy.</p>
    <hr>
    
    <label for="heading"><b>Heading *</b></label>
    <input type="text" placeholder="heading" id="heading" required><br>
    
    <label for="profession"><b>Profession</b></label>
    <select id = "profession">
    <%
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		List<String> professions = manager.getProfessions();
    	/*
    	professions.add("prof1");
    	professions.add("prof2");
    	professions.add("prof3");
    	professions.add("prof4");
    	*/
    	for(int i = 0 ; i < professions.size(); i++){
    		out.print("<option value = \" ");
			out.print(professions.get(i) + "\">");
			out.print(professions.get(i) + "</option>");
    	}
    %>
    </select><br>
    
    
    <label for="position"><b>Position *</b></label>
    <input type="text" placeholder="position" id="position" required><br>
   	
     
    <label for="description"><b>Description</b></label><br>
    <textarea  id="description" rows="5" ></textarea>
    <p></p>
    
    <label for="jobType"><b>Job Type</b></label>
    <select id = "jobType">
	  <option value="Full-time">Full-time</option>
	  <option value="Part-time">Part-time</option>
	  <option value="Intern"> Intern </option>
	  <option value="Temporary">Temporary</option>
	</select><br>
	
	<label for="endDate"><b>End Date</b></label>
	<input type="date"  id = "endDate" name="endDate" required><br>
	
	
	<label for="yearsofExperience"><b>Years of Experience</b></label>
	<input type="number"  id="yearsOfExperience"><br>
	
	<label for="Degree"><b>Degree</b></label>
	<select id = degree>
	  <option value="Bachelor's">Bachelor's</option>
	  <option value="Associate">Associate</option>
	  <option value="Master's">Master's</option>
	  <option value="Ph.D">Ph.D</option>
	  <option value="Pursuing Degree">Pursuing Degree</option>
	</select><br>
	
	<% 
		List<String> locations = manager.getLocations();
		%>
		<label for="Locations"><b>Locations</b></label>
		<select id = "locations">		
		<%
			for(int i = 0 ; i < locations.size(); i++){
				out.print("<option value = \" ");
				out.print(locations.get(i) + "\">");
				out.print(locations.get(i) + "</option>");
			}		
		%>
		</select><br>
	<%
		List<String> languages = manager.getLanguages();
		/*
		languages.add("opa");
		languages.add("opa1");
		languages.add("opa2");
		languages.add("opa3");
		languages.add("opa4");
		*/
	%>
		<label for="languages"><b>Languages</b></label>
		<select id = "languages" class="selectpicker" multiple data-live-search="true">
	<%
			for(int i = 0 ; i < languages.size(); i++){
				out.print("<option value = \" ");
				out.print(languages.get(i) + "\">");
				out.print(languages.get(i) + "</option>");
			}
	%>
		</select><br>	
		
		<label for="tags"><b>Tags</b></label>
		<select id = "tags" class="selectpicker" multiple data-live-search="true">
	<%	
			List<String> tags = manager.getTags();
			/*
			tags.add("opa");
			tags.add("opa1");
			tags.add("opa2");
			*/
			for(int i = 0 ; i < tags.size(); i++){
				out.print("<option value = \" ");
				out.print(tags.get(i) + "\">");
				out.print(tags.get(i) + "</option>");
			}
	%>
		</select><br>	
	<button type="submit" id = "add" class="Add Vacancy">Add Vacancy</button>		
			
</div>

</body>
</html>

