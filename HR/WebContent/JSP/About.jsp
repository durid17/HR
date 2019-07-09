<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>About</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/AboutStyle.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp" />

</head>
<body>
	<!-- Slideshow container -->
	<div class="slideshow-container">

		<!-- Full-width images with number and caption text -->
		<div class="mySlides fade">
			<img src="../Images/main05.jpg" style="width: 100%">
		</div>

		<div class="mySlides fade">
			<img src="../Images/main04.jpg" style="width: 100%">
		</div>

		<div class="mySlides fade">
			<img src="../Images/main03.jpg" style="width: 100%">
		</div>

		<div class="container">
			<div class="subheader">
				<h1>ABOUT US</h1>
			</div>
			<div class="about-us">
				<p>We founded HR in 2019 to tackle the most strategic challenge
					that companies face: how to grow their teams while matching the
					standards they have set to themselves. Our recruiting platform
					empower anyone seeking for job to jump right in whenever they need
					to while keeping the process time-efficient for both parties. Get
					vacancies suitable for your skill set and get in touch with top
					companies. We founded HR in 2019 to tackle the most strategic challenge
					that companies face: how to grow their teams while matching the
					standards they have set to themselves. Our recruiting platform
					empower anyone seeking for job to jump right in whenever they need
					to while keeping the process time-efficient for both parties. Get
					vacancies suitable for your skill set and get in touch with top
					companies.</p>
			</div>
		</div>
	</div>
</body>


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