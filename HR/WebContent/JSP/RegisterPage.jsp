<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/RegisterPageStyle.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">


</head>

<body>

	<h1>Join Us</h1>

	<div class="container">
		<button id="company">I want to hire</button>
		<button id=worker>I want to work</button>
	</div>

	<script type="text/javascript">
		document.getElementById("company").onclick = function() {
			location.href = "CompanyRegister.jsp";
		};

		document.getElementById("worker").onclick = function() {
			location.href = "ClientRegister.jsp";
		};
	</script>



</body>
</html>