<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="classes.Account, classes.DBManager,
 classes.Vacancy, classes.Company, classes.EmployeeProfile, classes.Employee, 
 java.util.List, java.util.ArrayList, java.util.Set, java.util.HashSet, classes.MyDateFormatter, java.util.Date" %>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title> Vacancies </title>
 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyPageStyle.css">
  <jsp:include page="Header.jsp" />
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  
  <% 
  	 DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
  	 Account acc = (Account)request.getSession().getAttribute("account");
  	 
  	 getServletContext().setAttribute("employeeId", acc.getID());
  	 int id = Integer.parseInt(request.getParameter("id"));
  	 getServletContext().setAttribute("vacanId", id);
  	 Vacancy vac = manager.getVacancy(id);
  	 Company company = manager.getCompany(vac.getCompanyId());
  	 
  	long millis=System.currentTimeMillis();  
    java.sql.Date date=new java.sql.Date(millis); 
  %>
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
  padding: 2px 16px;
  width: 100%;
height: 100%;
}

#profileImage{
display:inline-block;
  width:100%;
  height:200px;
  background-position:center;
  background-size:cover;

}


</style>

<body>

  <div class="vacancies">
    <ol class="unstyled-list">
      <li class="vacancy">

        <div class="vacancy_header">
          <a href="VacancyEdit.jsp?vacancyId=${vacanId}" id = "edit"> Edit </a>
          
          <h2> <%= vac.getHeading() %> </h2>

          <div>
            <ul class="horizontal-list">
              <li>
                <svg width="18" height="18" viewBox="0 0 24 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                  <g>
                    <path d="M15 11V5l-3-3-3 3v2H3v14h18V11h-6zm-8 8H5v-2h2v2zm0-4H5v-2h2v2zm0-4H5V9h2v2zm6 8h-2v-2h2v2zm0-4h-2v-2h2v2zm0-4h-2V9h2v2zm0-4h-2V5h2v2zm6 12h-2v-2h2v2zm0-4h-2v-2h2v2z"></path>
                    <path d="M0 0h24v24H0z" fill="none"></path>
                  </g>
                </svg>
                <%= company.getProfile().getName() %>
              </li>

              <li>
                <svg width="16" height="18" viewBox="0 0 18 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                  <g>
                    <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                    <path d="M0 0h24v24H0z" fill="none"></path>
                  </g>
                </svg>
                <%= vac.getReq().getLocation() %>
              </li>
            </ul>

            <div class="horizontal-list post_date">
              <time datetime="2019-05-31T21:31:59.778Z" itemprop="publishDate" aria-label="Published 24 days ago" class="gc-card__meta-item gc-card__datetime">
                <%= MyDateFormatter.daysBetween(vac.getStartDate(), date) %> days ago
              </time>
            </div>
          </div>

        </div>

        <hr>
        <br>

        <div class="vacancy_details">
          <ul class="vacancy_details_left">
            <li><strong>Employee Type: </strong> <%= vac.getEmpType() %> </li>
            <li><strong> Experience: </strong> <%= vac.getReq().getYearsOfExp() %> years</li>
          </ul>

          <ul class="vacancy_details_right">
            <li> <strong>Email: </strong> <%= company.getProfile().getEmail() %> </li>
            <li> <strong>Phone: </strong> <%= company.getProfile().getPhoneNumber() %> </li>
          </ul>

          <ul class="vacancy_details_right">
            <li> <strong>Degree: </strong> <%= vac.getReq().getDegree() %> </li>
            <li> <strong>Salary </strong> 1000$ </li>
          </ul>



        </div>

        <br>
        <hr>

        <div class="vacancy_content">
          <p> We are looking for <strong><%=vac.getPosition() %></strong> </p>
   
          <h4> Qualifications: </h4>
          <ul class="point">
              <li> <%= vac.getReq().getQualification1() %> </li>
              <li> <%= vac.getReq().getQualification1() %> </li>
              <li> <%= vac.getReq().getQualification1() %> </li>
           </ul>
           
           <br>
          <h4> About Vacancy: </h4>
          <p> <%= vac.getDescription() %></p>


          <p> <strong> If you are interested, do not hesitate to click Interest Button </strong> </p>
          <p> <strong> Deadline: </strong> <%=vac.getEndDate() %> </p>
        </div>

      </li>

    </ol>
  </div>
	
	<!-- Kalaaaaaaaaaaaaaa ###################3 Useeeeeeeeeeeeeeeeeeeeeeersssssssssssssssssssssssss -->
	<%	
	//First
		List<Employee> list = manager.getVacancyApplicants(vac.getId());	
		request.setAttribute("employees", list);
	%>
	
	<div id="container" class = "container"> 
	
	<c:forEach var="employe" items="${employees}">
		<a href="${pageContext.request.contextPath}/JSP/UserProfile.jsp?id=${employe.getId()}">
		<div class="cardClass">	
			<img  id = "profileImage" src="${employe.getProfile().getProfilePicture()}" alt="Avatar" >
			<div class="info">
				<h4 > ${employe.getProfile().getName()} </h4>
				<p > ${employe.getProfile().getMajorProfession()}  </p>			
		 	</div>
		</div>
		
		</a>
	</c:forEach>
	
	</div>
	

	
	
	<!-- #########################################################################################3 -->

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/JS/VacancyCartJS.js" charset="utf-8"></script>
</body>

</html>
