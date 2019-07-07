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
			<div class="button-container">
				<button id="company">I want to hire</button> <br>
				<button id=worker>I want to work</button>
			</div>
		</div>

		<!-- Next and previous buttons -->
		<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
		<a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>
	
	<!-- The dots/circles -->
	<div style="text-align:center">
	  	<span class="dot" onclick="currentSlide(1)"></span> 
	  	<span class="dot" onclick="currentSlide(2)"></span> 
	  	<span class="dot" onclick="currentSlide(3)"></span> 
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
	var slideIndex = 1;
	showSlides(slideIndex);
	
	// Next/previous controls
	function plusSlides(n) {
		showSlides(slideIndex += n);
	}
	
	// Thumbnail image controls
	function currentSlide(n) {
		showSlides(slideIndex = n);
	}
	
	function showSlides(n) {
		var i;
		var slides = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("dot");
		if (n > slides.length) {slideIndex = 1} 
		if (n < 1) {slideIndex = slides.length}
		for (i = 0; i < slides.length; i++) {
	    	slides[i].style.display = "none"; 
		}
		for (i = 0; i < dots.length; i++) {
	    	dots[i].className = dots[i].className.replace(" active", "");
		}
		slides[slideIndex-1].style.display = "block"; 
		dots[slideIndex-1].className += " active";	
	}
	
	
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