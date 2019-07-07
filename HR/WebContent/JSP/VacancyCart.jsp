<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="classes.Account, classes.DBManager,
 classes.Vacancy, classes.Company, classes.EmployeeProfile, java.util.List, java.util.Set, java.util.HashSet" %>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title> Vacancies </title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyCartStyle.css">
  <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
  <jsp:include page="Header.jsp"/>
  
  <% 
	DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
  	Account acc = (Account)request.getSession().getAttribute("account");	
	%>
</head>

<body>

  <div class="split">

    <aside class="split_sidebar">
      <h4 class="titles">Filter</h4>

      <div class="filter">
        <div class="filter_header">
          <a class="clear_filter" href="${pageContext.request.contextPath}/VacanciesServlet"> Clear Filters </a>
          <div class="clear"></div>
          <button type="button" name="recomended_button" class="read_more update" id = "recomend"> Recomended Filter</button>
        </div>

        <hr>

        <div class="filter_body">
          <div class="Professions">


            <h5>
              Professions
            </h5>

            <select id = "professions" class="selectpicker" multiple data-live-search="true">
              <c:forEach var="profession" items="${professions}">
					<option>	
						${profession}
					</option>
				</c:forEach>
            </select>
          </div>

          <br>


          <div class="companies">
            <h5>
              Companies
            </h5>

            <select id = "companies" class="selectpicker" multiple data-live-search="true">
              <c:forEach var="company" items="${companies}">
					<option>	
						${company.getProfile().getName()}
					</option>
				</c:forEach>
            </select>
          </div>

          <br>

          <div class="locations">
            <h5 class="locations">
              Locations
            </h5>

            <select id = "locations" class="selectpicker" multiple data-live-search="true">
              <c:forEach var="location" items="${locations}">
					<option>	
						${location}
					</option>
				</c:forEach>
            </select>
          </div>
          <br>

          <div class="tags">
            <h5>
              Tags
            </h5>

            <select id = "tags" class="selectpicker" multiple data-live-search="true">
              <c:forEach var="tag" items="${tags}">
					<option>	
						${tag}
					</option>
				</c:forEach>
            </select>
          </div>

          <br>
          <div class="jobs_type">
            <h5 class="jobs_type">
              Jobs Type
            </h5>

            <select id = "jobs_type" class="selectpicker" multiple data-live-search="true">
              <option>Full-Time</option>
              <option>Part-Time</option>
              <option>Intenship</option>
            </select>
          </div>

          <br>
          <div class="degree">
            <h5 class="degree" style="cursor: pointer">
              Degree

            </h5>

            <select id = "degree" class="selectpicker" multiple data-live-search="true">
              <c:forEach var="degree" items="${degrees}">
					<option>	
						${degree}
					</option>
				</c:forEach>
            </select>
          </div>

          <br>
          <button type="button" name="update" class="read_more update" id = "update"> UPDATE</button>


        </div>
      </div>
    </aside>




    <main class="vacancies">
      <h4 class="titles vacancy_tytle">Vacancies</h4>
      <ol class="unstyled-list">
      	<c:forEach var="vacancy" items="${vacancies}" >
					
        <li class="vacancy" >
        
          <div class="vacancy_header">
          
          	<c:if test="${DBManager.hasApplied(employeeId,vacancy.getId())}">
	            <input id = "interest" type="checkbox" class="interest" value = "${vacancy.getId()}" checked />          	
          	</c:if>
          	
          	<c:if test="${!DBManager.hasApplied(employeeId,vacancy.getId())}">
	            <input id = "interest" type="checkbox" class="interest" value = "${vacancy.getId()}"  />          	
          	</c:if>
          	
          	
          	  	
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

    