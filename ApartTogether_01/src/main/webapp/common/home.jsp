<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonDark.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/common/commonCSS/homeCSS.css" type="text/css">
<script src="${pageContext.request.contextPath}/common/homeJS.js"></script>
<title>Apart together</title>
</head>
<body>
	<div class="container">
		<div class="first-screen">
			<div id="main-title">
				모두에게 적용되는 배달비 혜택!<br>
				아파투게더!
			</div>
			<!-- 아이콘 -->
			<div class="logo-box">
			<c:set var="logo" value="1"/>
				<c:forEach var="p" begin="0" end="40" step="1">
					<c:if test="${p%2 eq 0}">
						<div class="logo-items-even">
							<img src="${pageContext.request.contextPath}/common/homeLogo/g${logo}.png" width="200px">
						</div>
					</c:if>
					<c:if test="${p%2 eq 1}">	
						<div class="logo-items-odd">
							<img src="${pageContext.request.contextPath}/common/homeLogo/g${logo}.png" width="200px">
						</div>
					</c:if>
					<c:set var="logo" value="${logo=logo+1}"/>
					<c:if test="${logo eq 11}">
						<c:set var="logo" value="${logo=1}"/>
					</c:if>
				</c:forEach>
			</div>
			<div class="scroll-info">
				<span>스크롤</span>
				<img id="scroll-img" src="${pageContext.request.contextPath}/common/homeIMG/arrow.png">
			</div>
		</div>
		<div class="second-screen">
			<div class="second-screen-coment coments-size" data-aos="fade-up" data-aos-delay="400" data-aos-duration="500">
				<span class="coment-thin">군침 싹~</span>
				<span class="coment-thin">돌게하는</span>
				<span class="coment-bold">다양한 음식들.</span>
			</div>
			<div data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
				<img src="${pageContext.request.contextPath}/common/homeImage/ig1.png" width="450px">
			</div>
		</div>
		<div class="third-screen">
			<div data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
				<img src="${pageContext.request.contextPath}/common/homeImage/ig2.png" width="450px">
				<img id="coins1" src="${pageContext.request.contextPath}/common/homeImage/coins.png" width="120px" data-aos="fade-down" data-aos-delay="700" data-aos-duration="1000">
				<img id="coins2" src="${pageContext.request.contextPath}/common/homeImage/coins.png" width="120px" data-aos="fade-down" data-aos-delay="1000" data-aos-duration="2000">
			</div>
			<div class="third-screen-coment coments-size" data-aos="fade-up" data-aos-delay="400" data-aos-duration="500">
				<span class="coment-thin">모이는 만큼</span>
				<span class="coment-bold">저렴해지는</span>
				<span class="coment-bold">배달비.</span>
			</div>	
		</div>
		<div class="fourth-screen">
			<div class="fourth-screen-coment coments-size" data-aos="fade-up" data-aos-delay="400" data-aos-duration="500">
				<span class="coment-thin">정산</span>
				<span class="coment-thin">편하게,</span>
				<span class="coment-bold">깔끔하게 처리.</span>
			</div>
			<div data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
				<img src="${pageContext.request.contextPath}/common/homeImage/ig3.png" width="450px">
			</div>
		</div>
		<div class="fifth-screen">
			<div data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
				<img src="${pageContext.request.contextPath}/common/homeImage/ig4.png" width="450px">
				<img id="cross-icon" src="${pageContext.request.contextPath}/common/homeImage/ig4-1.png" width="200px" data-aos="fade-down" data-aos-delay="700" data-aos-duration="1000">
			</div>
			<div class="fifth-screen-coment coments-size" data-aos="fade-up" data-aos-delay="400" data-aos-duration="500">
				<span class="coment-thin">배달앱</span>
				<span class="coment-thin">더 이상</span>
				<span class="coment-bold">구독하지 마세요.</span>
			</div>
		</div>
		<div class="empty-space">
			
		</div>
	</div>
 	<div class="sixth-screen">
 		<div class="end-coment coments-size" data-aos="fade-up" data-aos-delay="500" data-aos-duration="1000">
 			<span>다양한 프렌차이즈를</span>
			<span>지금 아파투게더에서 만나보세요!</span>
 		</div>
 		<div class="end-img">
 			<img id="last-img" src="${pageContext.request.contextPath}/common/homeImage/end-img.png" width="650px">
 		</div>
 		<div class="goButton">
 			<a type="button" id="btn1" href="/ApartTogether_01/Apartogether?command=meInsert" data-aos="zoom-in-up" data-aos-delay="900">
 				<span>&lt;&lt; together하러가기!</span>
 			</a>
 			<a type="button" id="btn2" href="/ApartTogether_01/Apartogether?command=stList" data-aos="zoom-in-up" data-aos-delay="900">
 				<span>가게 구경하러 가기 >></span>
 			</a>
 		</div>
 		<div class="move-up" data-aos="zoom-in-up" data-aos-delay="1100">
 			<span id="arrow">
 				<a type="button" href="#">
 					<span>⬆</span>
 					<span>위로 올라가기</span>
 				</a>
 			</span>
 		</div>
	</div>
 <script>AOS.init();</script>
</body>
<%@ include file="/common/footer.jsp"%>
</html>	