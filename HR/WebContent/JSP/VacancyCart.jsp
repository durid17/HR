<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title> Vacancies </title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/VacancyCartStyle.css">
</head>

<body>

  <div class="vacancies">
    <ol class="unstyled-list">
      <li class="vacancy">

        <div class="vacancy_header">
          <input type="checkbox" class="interest" />
          <h2> Java Developer </h2>

          <div>
            <ul class="horizontal-list">
              <li>
                <svg width="18" height="18" viewBox="0 0 24 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                  <g>
                    <path d="M15 11V5l-3-3-3 3v2H3v14h18V11h-6zm-8 8H5v-2h2v2zm0-4H5v-2h2v2zm0-4H5V9h2v2zm6 8h-2v-2h2v2zm0-4h-2v-2h2v2zm0-4h-2V9h2v2zm0-4h-2V5h2v2zm6 12h-2v-2h2v2zm0-4h-2v-2h2v2z"></path>
                    <path d="M0 0h24v24H0z" fill="none"></path>
                  </g>
                </svg>
                Google
              </li>

              <li>
                <svg width="16" height="18" viewBox="0 0 18 24" role="presentation" xmlns="http://www.w3.org/2000/svg" class="gc-icon">
                  <g>
                    <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
                    <path d="M0 0h24v24H0z" fill="none"></path>
                  </g>
                </svg>
                Palo Alto, CA, USA
              </li>
            </ul>

            <div class="horizontal-list post_date">
              <time datetime="2019-05-31T21:31:59.778Z" itemprop="publishDate" aria-label="Published 24 days ago" class="gc-card__meta-item gc-card__datetime">
                24 days ago
              </time>
            </div>
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
          <button type="button" name="button" class="read_more"> OPEN</button>
        </div>

      </li>

    </ol>
  </div>


  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="post.js" charset="utf-8"></script>
</body>

</html>
