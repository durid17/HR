<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="classes.Account, classes.DBManager, classes.Employee, 
    classes.EmployeeProfile, classes.Hash, classes.Company, classes.CompanyProfile, java.util.List"    
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies</title>


<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/CompListStyle.css">
<jsp:include page="Header.jsp" />
<% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

	List<Company> list = manager.getCompanies();
	request.setAttribute("companies", list);
%>




</head>
<body>
	<div class="pg">
		<c:forEach var="company" items="${companies}">
			<a class="cardClass" href="${pageContext.request.contextPath}/JSP/CompanyProfile.jsp?id=${company.getId()}">	
				<div class="coupon">
				  <div class="container">
				    <h4>${company.getProfile().getName()}</h4>
				  </div>
				  <img src="${company.getProfile().getLogo()}" alt="Avatar" style="width:100%;">
				  <div class="container">
				    <p>${company.getProfile().getEssence()}</p>
				  </div>
				</div>
			</a>
		</c:forEach>
	</div>
</body>
</html>