<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <style type="text/css">
  	#container{margin-left: 150px; margin-right: 150px;}
	.carousel{ margin: 0 auto; width: 80%; height: 700px;}
	.d-block{height: 700px;}
   </style>
</head>
<body>
	<div id = "container">
	<div id="demo" class="carousel slide" data-bs-ride="carousel">

	  <!-- Indicators/dots -->
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
	  </div>
	  
	  <!-- The slideshow/carousel -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="<%=appName%>/image/la.jpg" alt="Los Angeles" class="d-block" style="width:100%">
	      <div class="carousel-caption">
	        <h3>Los Angeles</h3>
	        <p>We had such a great time in LA!</p>
	      </div>
	    </div>
	    <div class="carousel-item">
	      <img src="<%=appName%>/image/chicago.jpg" alt="Chicago" class="d-block" style="width:100%">
	      <div class="carousel-caption">
	        <h3>Chicago</h3>
	        <p>Thank you, Chicago!</p>
	      </div> 
	    </div>
	    </div>
	    <div class="carousel-item">
	      <img src="<%=appName%>/image/ny.jpg" alt="New York" class="d-block" style="width:100%">
	      <div class="carousel-caption">
	        <h3>New York</h3>
	        <p>We love the Big Apple!</p>
	      </div>
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
  <title>아파~투게터</title>
</head>
<body>
	샘플
	<%=request.getContextPath()%>
	
	<form action="<%=withFormTag%>" method="get">
		<!-- 아이디 히든으로 들어감. -->
		<input name="command" type="text" value="stList">
		<button type="submit">가게 목록 보기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get">
		<!-- 아이디 히든으로 들어감. -->
		<input name="command" type="text" value="myStoreList">
		<input name="id" type="text" value="uiui">
		<button type="submit">내 가게 보기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stUpdate">
		<input name="id" value="seller">
		<input name="stno" value="1">
		<button type="submit">수정</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stDetail">
		<input name="id" value="seller">
		<input name="stno" value="1">
		<button type="submit">상세보기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stMuDetail">
		<input name="stno" value="1">
		<%-- 실제로는 가게 넘버만 넘어가면 됨--%>
		<button type="submit">가게 메뉴 상세보기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stSales">
		<input name="stno" value="1">
		<%-- 실제로는 가게 넘버만 넘어가면 됨--%>
		<button type="submit">매출현황</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stOrLog">
		<input name="stno" value="1">
		<%-- 실제로는 가게 넘버만 넘어가면 됨--%>
		<button type="submit">주문내역</button>
	</form>

</body>
</html>