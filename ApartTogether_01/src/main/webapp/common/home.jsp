<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/commonCSS/homeCSS.css" type="text/css">
<title>Apart together</title>
</head>
<body>

	<div class="container">
		<div class="first-screen">
			<div id="main-title">
				모두에게 적용되는<br>
				배달비 혜택!
			</div>
			<!-- 아이콘 -->
			<div class="logo-box">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g1.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g2.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g3.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g4.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g5.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g6.png" width="200px">
				<img src="${pageContext.request.contextPath}/common/homeIMG/g7.png" width="200px">
			</div>
		</div>
		<div class="second-screen">
		</div>
		
	</div>
 
 <script>AOS.init();</script>
</body>
<%-- <%@ include file="/common/footer.jsp"%> --%>
</html>	