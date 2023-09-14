<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <style>
  	#container{margin-left: 250px; margin-right: 250px;}
  </style>
</head>
<body>
	<div id = "container">
		<div id="demo" class="carousel slide" data-bs-ride="carousel" style ="height : 500px">
		
		  <!-- Indicators/dots -->
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
		    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
		    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
		  </div>
		
		  <!-- The slideshow/carousel -->
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="./../image/test.jpeg" alt="Los Angeles" class="d-block w-100" style="height: 500px;">
		    </div>
		    <div class="carousel-item">
		      <img src="./../image/test.jpeg" alt="Chicago" class="d-block w-100" style="height: 500px;">
		    </div>
		    <div class="carousel-item">
		      <img src="./../image/test.jpeg" alt="New York" class="d-block w-100" style="height: 500px;">
		    </div>
		  </div>
		
		  <!-- Left and right controls/icons -->
		  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </button>
		</div>
		
		
		
		
	</div>
</body>
</html>
 