<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="classes.Account, classes.DBManager,
 classes.Vacancy, classes.Company, classes.EmployeeProfile, java.util.List, java.util.Set, java.util.HashSet" %>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title> MyVacancies </title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyCartStyle.css">
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="Header.jsp"/>
  
  <% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
  	Account acc = (Account)request.getSession().getAttribute("account");
  	
  	List<Vacancy> vacancies = manager.getCompanyVacancies(acc.getID());
  	getServletContext().setAttribute("vacancies", vacancies);

	%>
</head>

<body>

  <div class="split">

    <main class="vacancies">
      <h4 class="titles vacancy_tytle">Vacancies</h4>
      <ol class="unstyled-list">
      	<c:forEach var="vacancy" items="${vacancies}" >
					
        <li class="vacancy" >
        
          <div class="vacancy_header">
          	<a href="VacancyEdit.jsp?vacancyId=${vacancy.getId()}" id = "edit"> Edit </a>
          	
          	  	
            <h2> ${vacancy.getHeading()} </h2>

            <div>
              <ul class="horizontal-list">
                <li>
                  <svg width="18" height="18" viewBox="0 0 24 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                    <g>
                      <path d="M15 11V5l-3-3-3 3v2H3v14h18V11h-6zm-8 8H5v-2h2v2zm0-4H5v-2h2v2zm0-4H5V9h2v2zm6 8h-2v-2h2v2zm0-4h-2v-2h2v2zm0-4h-2V9h2v2zm0-4h-2V5h2v2zm6 12h-2v-2h2v2zm0-4h-2v-2h2v2z"></path>
                      <path d="M0 0h24v24H0z" fill="none"></path>
                    </g>
                  </svg>
                  <% Vacancy curr = (Vacancy)pageContext.getAttribute("vacancy"); %>
                  <% Company company = manager.getCompany(curr.getCompanyId()); %>
                  <%= company.getProfile().getName() %>
                       
                </li>

                <li>
                  <svg width="16" height="18" viewBox="0 0 18 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                    <g>
                      <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                      <path d="M0 0h24v24H0z" fill="none"></path>
                    </g>
                  </svg>
                  ${vacancy.getReq().getLocation()}
                </li>
              </ul>
            </div>

          </div>

          <hr>

          <div class="vacancy_content">
            <h4> Qualifications: </h4>
            <ul class="point">
              <li> As a developer you will participate in all agile processes starting from planning design architecture, implementation,
                everyday meetings, planning and estimation, unit testing, code review </li>
              <li> Write clean, stable and optimized code </li>
              <li> Provide delivery with a team and code quality balance </li>
            </ul>

            <br> <br>
            <a href="${pageContext.request.contextPath}/JSP/VacancyPage.jsp?id=${vacancy.getId()}" class="button read_more open_vacancy">OPEN</a>
          </div>

        </li>
		</c:forEach>
      </ol>
    </main>
  </div>



  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/JS/VacancyCartJS.js" charset="utf-8"></script>
</body>

</html>