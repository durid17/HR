<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="classes.*" %>
<%@ page import="org.json.JSONObject" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Vacancy</title>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyAdd.css">


<%
 	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	List<String> professions = manager.getProfessions();
	int id = Integer.parseInt(request.getParameter("vacancyId"));
	//int id = 91;
   	Vacancy vacancy = manager.getVacancy(id);
   	JSONObject jobj = new JSONObject();
	jobj.put("vacancyId", vacancy.getId());
	//System.out.println(jobj.toString());
%>

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
		$("#edit").click(function(){
			var GivenDate = document.getElementById("endDate").value;
		    var CurrentDate = new Date();
		    //console.log("op");
		    //console.log(GivenDate);
		    if(GivenDate == ""){
		        alert('You should choose end date.');
		        return;
		    }
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
		    	tags : toString($('#tags').val()),
		    	vacancyId : '<%= jobj.toString() %>'
		    }
			var url = "../VacancyEditServlet";
			$.post(url,data, 
					function(data, status){
						var json = JSON.parse(data);
						//console.log(json);
						var id = json.vacancyId;
		                location.href = "VacancyPage.jsp?vacancyId=" + id;
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
    <h1>Edit Vacancy</h1>
    <hr>
    
    <label for="heading"><b>Heading *</b></label>
    <input type="text" id="heading" value= <%= vacancy.getHeading() %>><br>
    
    <label for="profession"><b>Profession</b></label>
    <select id = "profession">
    <%
		
    	/*
    	professions.add("prof1");
    	professions.add("prof2");
    	professions.add("prof3");
    	professions.add("prof4");
    	*/
    	for(int i = 0 ; i < professions.size(); i++){
    		out.print("<option value = \" ");
			out.print(professions.get(i) + "\"");
			if(professions.get(i).equals(vacancy.getReq().getProfession())) 
				out.print("selected = \"selected\"");
			out.print(">");
			out.print(professions.get(i) + "</option>");
    	}
    %>
    </select><br>
    
    
    <label for="position"><b>Position *</b></label>
    <input type="text" id="position" value= <%= vacancy.getPosition() %>><br>
   	
     
    <label for="description"><b>Description</b></label><br>
    <textarea  id="description" rows="5"><%= vacancy.getDescription() %></textarea>
    <p></p>
    
    <label for="jobType"><b>Job Type</b></label>
    <select id = "jobType">
	  <option value="Full-time">Full-time</option>
	  <option value="Part-time">Part-time</option>
	  <option value="Intern"> Intern </option>
	  <option value="Temporary">Temporary</option>
	</select><br>
	
	<label for="endDate"><b>End Date</b></label>
	<input type="date"  id = "endDate" name="endDate" value = <%= vacancy.getEndDate() %>><br>
	
	
	<label for="yearsofExperience"><b>Years of Experience</b></label>
	<input type="number"  id="yearsOfExperience" value = <%= vacancy.getReq().getYearsOfExp() %>><br>
	
	<label for="Degree"><b>Degree</b></label>
	<% String degree = vacancy.getReq().getDegree(); %>
	<select id = degree>
	  <option value="Bachelor's"
	  <% if(degree.equals("Bachelor's"))
		  	out.print("selected = \"selected\"");
		  %>
	  >Bachelor's</option>
	  <option value="Associate"
	  <% if(degree.equals("Bachelor's"))
		  	out.print("selected = \"selected\"");
		  %>>Associate</option>
	  <option value="Master's"
	  <% if(degree.equals("Master's"))
		  	out.print("selected = \"selected\"");
		  %>>Master's</option>
	  <option value="Ph.D"
	  <% if(degree.equals("Ph.D"))
		  	out.print("selected = \"selected\"");
		  %>>Ph.D</option>
	  <option value="Pursuing Degree"
	  <% if(degree.equals("Pursuing Degree"))
		  	out.print("selected = \"selected\"");
		  %>>Pursuing Degree</option>
	</select><br>
	
	<% 
		List<String> locations = manager.getLocations();
		%>
		<label for="Locations"><b>Locations</b></label>
		<select id = "locations">		
		<%
			for(int i = 0 ; i < locations.size(); i++){
				out.print("<option value = \" ");
				out.print(locations.get(i) + "\"");
				if(locations.get(i).equals(vacancy.getReq().getLocation())) 
					out.print("selected = \"selected\"");
				out.print(">");
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
			List<Language> lan = manager.getRequirementLanguages(id);
			List<String> myLanguages = new ArrayList<>();
			for(int i = 0; i < lan.size(); i++){
				myLanguages.add(lan.get(i).getLanguage());
			}
				
			for(int i = 0 ; i < languages.size(); i++){
				out.print("<option value = \" ");
				out.print(languages.get(i) + "\"");
				if(myLanguages.contains(languages.get(i))) 
					out.print("selected = \"selected\"");
				out.print(">");
				out.print(languages.get(i) + "</option>");
			}
	%>
		</select><br>	
		
		<label for="tags"><b>Tags</b></label>
		<select id = "tags" class="selectpicker" multiple data-live-search="true">
	<%	
			List<String> tags = manager.getTags();
			List<String> myTags = manager.getVacancyTags(id);
			/*
			tags.add("opa");
			tags.add("opa1");
			tags.add("opa2");
			*/
			for(int i = 0 ; i < tags.size(); i++){
				out.print("<option value = \" ");
				out.print(tags.get(i) + "\"");
				if(myTags.contains(tags.get(i))) 
					out.print("selected = \"selected\"");
				out.print(">");
				out.print(tags.get(i) + "</option>");
			}
	%>
		</select><br>	
	<button type="submit" id = "edit" class="Edit Vacancy">Edit Vacancy</button>		
			
</div>

</body>
</html>

