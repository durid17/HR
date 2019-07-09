<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="classes.Account, classes.DBManager, classes.Employee, 
    classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile"
    
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserCardList</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

</head>


<style>

.container {
	background-color: white;
	padding: 15px; 
}

.cardClass {
border: none;
border-radius: 2px;
	box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
 	 transition: 0.3s;
	margin-left: 15px;
	margin-bottom: 15px;
	display: inline-block;
	background-color: white;
	cursor: pointer;	
	width: 15%;
}

.cardClass:hover{
     transform: scale(1.05); 
}

.info {
width: 100%;
height: 100%;
  padding: 2px 16px;
}

img{
display:inline-block;
  width:100%;
  height:200px;
  background-position:center;
  background-size:cover;

}



</style>



<% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	
	//First
	Employee one = manager.getEmployee(13);
	EmployeeProfile oneP = one.getProfile();
	oneP.setProfilePicture("../Images/avatar.png");
	oneP.setMajorProfession("Student");
	//Second
	Employee two = manager.getEmployee(13);
	EmployeeProfile twoP = two.getProfile();
	twoP.setProfilePicture("../Images/woman.png");
	twoP.setMajorProfession("Software Engineer");
	//Third
	Employee three = manager.getEmployee(13);
	EmployeeProfile threeP = three.getProfile();
	threeP.setProfilePicture("../Images/ChatBubble.png");
	threeP.setMajorProfession("Data Base Manager");
	//
	Employee four = manager.getEmployee(15);
	EmployeeProfile fourP = four.getProfile();
	fourP.setProfilePicture("../Images/Location.png");
	fourP.setMajorProfession("Web Developer");
	
	//
	//First
	Employee five = manager.getEmployee(13);
	EmployeeProfile fiveP = five.getProfile();
	fiveP.setProfilePicture("../Images/compImage.png");
	fiveP.setMajorProfession("Student");
	//Second
	Employee six = manager.getEmployee(13);
	EmployeeProfile sixP = six.getProfile();
	sixP.setProfilePicture("../Images/freeuni.png");
	sixP.setMajorProfession("Software Engineer");
	//Third
	Employee sev = manager.getEmployee(13);
	EmployeeProfile sevP = sev.getProfile();
	sevP.setProfilePicture("../Images/Phone.png");
	sevP.setMajorProfession("Data Base Manager");
	//
	Employee eig = manager.getEmployee(15);
	EmployeeProfile eigP = eig.getProfile();
	eigP.setProfilePicture("../Images/tbc.png");
	eigP.setMajorProfession("Web Developer");
	
	
	
	ArrayList<Employee> list = new ArrayList<>();
	list.add(one);
	list.add(two);
	list.add(three);
	list.add(four);
	list.add(five);
	list.add(six);
	list.add(sev);
	list.add(eig);
	
	request.setAttribute("employees", list);
%>

<body>	
	<div id = "header"> </div>
	

	<div id="container"  class = "container"> 
	
	<c:forEach var="employe" items="${employees}">
		<a href="${pageContext.request.contextPath}/JSP/UserProfile.jsp?id=${employe.getId()}">
		<div class="cardClass">	
			<img  id = "profileImage" src="${employe.getProfile().getProfilePicture()}" alt="Avatar" >
			<div class="info">
				<h4 > ${employe.getProfile().getName()} </h4>
				<p> ${employe.getProfile().getMajorProfession()} </p>			
		 	</div>
		</div>
		
		</a>
	</c:forEach>
	
	</div>
	 
	
	
	

</body>
</html>