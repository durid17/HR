<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main Page</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/MainPageStyle.css">


</head>
<body>
	<!-- Slideshow container -->
	<div class="slideshow-container">
	
	  	<!-- Full-width images with number and caption text -->
	  	<div class="mySlides fade">
		    <img src="../Images/main05.jpg" style="width:100%">
		</div>
	
	  	<div class="mySlides fade">
	    	<img src="../Images/main04.jpg" style="width:100%">
	  	</div>
	
	  	<div class="mySlides fade">
	    	<img src="../Images/main03.jpg" style="width:100%">
	  	</div>
		
		<div class="container">
			<div class="subheader">
		    	<h1 class="subheader-text">Powerful software that makes hiring easy.</h1>
		    	<p><i>"If you think hiring professionals is expensive, try hiring amateurs"</i></p>
		    	<p align="right"><i>- Anonymous</i></p>
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