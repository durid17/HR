<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/RegisterPageStyle.css">


</head>
<body>
	<!-- Slideshow container -->
	<div class="slideshow-container">
	
	  	<!-- Full-width images with number and caption text -->
	  	<div class="mySlides fade">
		    <img src="../Images/main01.png" style="width:100%">
		</div>
	
	  	<div class="mySlides fade">
	    	<img src="../Images/main02.png" style="width:100%">
	  	</div>
	
	  	<div class="mySlides fade">
	    	<img src="../Images/main03.png" style="width:100%">
	  	</div>
		
		<div class="container">
			<div class="subheader">
		    	<h1>JOIN US</h1>
				<h4>Our recruiting platform is made to meet the needs of any business. Powerful software that makes hiring easy.</h4>
		  	</div>
			
			<div class="button-container">
				<button class="my-button" id="company">I want to hire</button> <br>
				<button class="my-button" id=worker>I want to work</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	document.getElementById("company").onclick = function() {
		location.href = "CompanyRegister.jsp";
	};

	document.getElementById("worker").onclick = function() {
		location.href = "ClientRegister.jsp";
	};
</script>


<script>	
	var slideIndex = 0;
	showSlides();

	function showSlides() {
	  	var i;
	  	var slides = document.getElementsByClassName("mySlides");
	  	for (i = 0; i < slides.length; i++) {
	    	slides[i].style.display = "none"; 
	  	}
	  	slideIndex++;
	  	if (slideIndex > slides.length) {slideIndex = 1} 
	 	slides[slideIndex-1].style.display = "block"; 
	  	setTimeout(showSlides, 4500);
	}
</script>
</html>