<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="classes.Account, classes.DBManager, classes.Employee, 
    classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile, java.util.ArrayList"    
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies List</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<jsp:include page="Header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompaniesListStyle.css">

</head>

<% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
	//First
	Company one = manager.getCompany(13);
	CompanyProfile oneP = one.getProfile();
	oneP.setLogo("../Images/fb.png");
	oneP.setDescription("Social Network");
	//Second
	Company two = manager.getCompany(14);
	CompanyProfile twoP = two.getProfile();
	twoP.setLogo("../Images/google.jpg");
	twoP.setDescription("Search Platform");
	//Third
	Company three = manager.getCompany(15);
	CompanyProfile threeP = three.getProfile();
	threeP.setLogo("../Images/freeuni.png");
	threeP.setDescription("University");
	//Fourth
	Company four = manager.getCompany(16);
	CompanyProfile fourP = four.getProfile();
	fourP.setLogo("../Images/tbc.png");
	fourP.setDescription("Bank");
	
	
	
	ArrayList<Company> list = new ArrayList<>();
	list.add(one);
	list.add(two);
	list.add(three);
	list.add(four);
	request.setAttribute("companies", list);
%>


<body>	
	<div id = "header"> </div>
	
	<div id="container" class = "container"> 
		
		<c:forEach var="company" items="${companies}">
		
			<a href="${pageContext.request.contextPath}/JSP/CompanyProfile.jsp?id=${company.getId()}">
			
			<div class="cardClass">	
				<img class="labClass" id = "profileImage" src="${company.getProfile().getLogo()}" alt="Avatar">
				<label class="labClass"> ${company.getProfile().getName()} </label>
				<label class="labClass"> ${company.getProfile().getDescription()} </label> 			
			</div>
			</a>
		</c:forEach>
	</div>
</body>
</html>