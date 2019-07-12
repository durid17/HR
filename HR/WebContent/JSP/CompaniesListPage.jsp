<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="classes.Account, classes.DBManager, classes.Employee, 
    classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile, java.util.List"    
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies List</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">

<jsp:include page="Header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompaniesList.css">

</head>

<% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

	List<Company> list = manager.getCompanies();
	request.setAttribute("companies", list);
%>


<body>	
	<div id = "header"> </div>
	
	<div id="container" class = "container"> 
		
		<c:forEach var="company" items="${companies}">
		
			<a class="cardClass" href="${pageContext.request.contextPath}/JSP/CompanyProfile.jsp?id=${company.getId()}">
			<div class="cardClass">	
				
						<img class="labClass" id = "profileImage" src="${company.getProfile().getLogo()}" alt="Avatar">
						<label class="labClass" id="name"> ${company.getProfile().getName()} </label>
						<label class="labClass" id="essence"> ${company.getProfile().getEssence()} </label> 			
				
			</div>
			</a>
		</c:forEach>
	</div>
</body>
</html>