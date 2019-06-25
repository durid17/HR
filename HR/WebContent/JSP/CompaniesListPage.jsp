<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Companies</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
	$(function() {
		$("#header").load("Header.jsp");
	});
</script>

</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/UserProfileStyle.css">

<body>	
	<div id = "header"> </div>
	
	<div id="container"></div>
	
	<script>
		
		function createItem(logo, name, description){
			var div = document.createElement("div");
			div.innerHTML = "Hello";
			div.innerHTML = "asd";
			
			var i = document.createElement('img');
			var p = document.createElement('p');
			var l = document.createElement('label');
			
			p.textContent = name;
			return p;
		}
		
		function appendChildren(parent, children){
			children.forEach(function (child){
				parent.appendChild(child);
			});
		}
		
		var container = document.getElementById('container');
		var items = [createItem('Dom','asd','asd'), createItem('ALex')];
		appendChildren(container, items);
	</script>
	
	

</body>
</html>