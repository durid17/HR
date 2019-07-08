<%@page import="classes.Employee"%>
<%@ page import="classes.Account, classes.DBManager, java.util.List, classes.Employee, classes.EmployeeProfile, classes.Hash, classes.CompanyProfile"
	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Settings-Info</title>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<jsp:include page="Header.jsp" />
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/SettingsStyle.css">

<% 
	Account acc = (Account)request.getSession().getAttribute("account");
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	Employee employee = manager.getEmployee(acc.getID());
	EmployeeProfile profile = employee.getProfile();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
		$("#save").click(function(){
			var data = {
				firstName : document.getElementById("firstName").value.trim() , 
				lastName : document.getElementById("lastName").value.trim() , 
				email : document.getElementById("email").value.trim() , 
				bday : document.getElementById("bday").value.trim(),
				majorProfession : document.getElementById("majorProfession").value.trim() ,
				phoneNumber : document.getElementById("phoneNumber").value.trim() ,
				address : document.getElementById("address").value.trim() ,
				description : document.getElementById("description").value.trim() ,
				image : document.getElementById("image").value.trim() ,	
				id : <%= employee.getId() %>,	
		    	tags : toString($('#tags').val())
		    }
			var url = "../UpdateInfo";
			$.post(url,data, 
					function(data, status){
		                location.href = "http://localhost:8082/HR/JSP/Settings-Info-User.jsp";
					}
			);
		});
	})
</script>


</head>
<body>
		
		<div class = "leftMenu"> 
			<label for="account">Account</label>
			<a href="${pageContext.request.contextPath}/JSP/Settings.jsp">Username and Password</a>
		    <a class ="active"   href="${pageContext.request.contextPath}/JSP/Settings-Info-User.jsp" id="link">Personal Info</a>
		    <a href="${pageContext.request.contextPath}/JSP/AddWorkExperience.jsp" id="link">Add Work Experience</a>
		    <a href="${pageContext.request.contextPath}/JSP/AddEducation.jsp" id="link">Add Education</a>
		</div>
		
		  <div class="right">
		  <!-- 
		  	<form action = "${pageContext.request.contextPath}/UpdateInfo" method ="post">
		   -->
	
			    <label for="firstName"><b>First Name</b></label>
			    <textarea class="area" id="firstName" name="firstName"><%=profile.getName()%></textarea>
			  
			    <label for="lastName"><b>Last Name</b></label>
			    <textarea class="area" id = "lastName" name="lastName"><%=profile.getSurname()%></textarea>
			    
			    <label for="email"><b>Email</b></label>
			     <textarea class="area" id = "email" name="email"><%=profile.getEmail()%></textarea>
			    
			  
  				<br>
  				Birthday: <input type="date" id= "bday" class="inp" name="bday" value=<%=profile.getBirthDate() %>> <br>
  				
			    <label for="majorProfession"><b>Major Profession</b></label>
			     <textarea class="area" id = "majorProfession" name="majorProfession"><%=profile.getMajorProfession()%></textarea>
			    
			    <label for="phoneNumber"><b>Phone Number</b></label>
			    <input type="number"  class="inp" id = "phoneNumber" name = "phoneNumber"  value=<%=profile.getPhoneNumber()%> required>
			    
			     <label for="address"><b>Address</b></label>
			    <textarea class="area" id = "address" name="address"><%=profile.getAddress()%></textarea>
			    
			  
			   
			    <label >About</label>
			    <textarea  class="area"style=" resize: none;" id = "description" name="description"><%=profile.getDescription()%></textarea>
				
				 <label for="image"><b>Image</b></label>
			    <input type="text"  id = "image" class="inp" name = "image"  value=<%=profile.getProfilePicture()%> required>
			    
			 <br>
			<label for="tags"><b>Tags</b></label>
			<select id = "tags" class="selectpicker" multiple data-live-search="true">
			<%	
					List<String> tags = manager.getTags();
					List<String> myTags = manager.getEmployeeTags(employee.getId());
					for(int i = 0 ; i < tags.size(); i++){
						out.print("<option value = \" ");
						out.print(tags.get(i) + "\"");
						if(myTags.contains(tags.get(i))) 
							out.print("selected = \"selected\"");
						out.print(">");
						out.print(tags.get(i) + "</option>");
					}
			%> 
			</select>  
			    <hr>
			  
			    <button type="submit" id = "save" class="submitButton">Save Changes</button>
	  		<!-- 
	  		</form>
	  		 -->
  		 </div>
		
	
		
		
</body>
</html>