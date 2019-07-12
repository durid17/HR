<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/ContactPageStyle.css">

<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico">
<jsp:include page="Header.jsp"/>
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
		
		<div class = "container">
			<div class="subheader">
		    	<h1>CONTACT US</h1>
				<h4>We will be happy to assist you with any question</h4>
		  	</div>
		  	<div class="contacts-features">
		  		<div class="contact-type">
		  			<img src="../Images/ChatBubble.png">
		  			<p>info@hr.lenini.ge</p>
		  		</div>
		  		<div class="contact-type">
		  			<img src="../Images/Phone.png">
		  			<p>+995 322 13 11 31</p>
		  		</div>
		  		<div class="contact-type">
		  			<img src="../Images/Location.png">
		  			<p>Kakha Bendukidze Campus</p>
		  		</div>
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