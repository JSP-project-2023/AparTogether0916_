<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/bootstrap5.jsp"%>
<%@page import="java.util.*"%>


<%-- jstl을 위한 태그 라이브러리 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- [st] whologin 변수는 현재 로그인 상태를 알려 주는 변수입니다.  미로그인(0)  관리자 (1) 사업자 (2) 일반회원 (3) -->
<c:if test="${not empty sessionScope.loginfo}">
	<c:set var="whologin" value="0" />
	<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
		<c:set var="whologin" value="1" />
	</c:if>
	<c:if test="${sessionScope.loginfo.mtype == 'biz'}">
		<c:set var="whologin" value="2" />
	</c:if>
	<c:if test="${sessionScope.loginfo.mtype == 'user'}">
		<c:set var="whologin" value="3" />
	</c:if>
</c:if>
<!-- [ed] whologin 변수는 현재 로그인 상태를 알려 주는 변수입니다.  미로그인(0)  관리자 (1) 사업자 (2) 일반회원 (3) -->

<%
// appName : 애플리케이션 이름(프로젝트 이름_Teacher)
String appName = request.getContextPath();
String mappingName = "/Apartogether"; // in FrontController.java file
// form 태그에서 사용할 변수
String withFormTag = appName + mappingName;

// form 태그가 아닌 영역에서 사용할 변수
String notWithFormTag = appName + mappingName + "?command=";

//out.print("프로젝트 이름 : " + appName + "<br/>") ;
//out.print("mappingName : " + mappingName + "<br/>") ;
//out.print("withFormTag : " + withFormTag + "<br/>") ;
//out.print("notWithFormTag : " + notWithFormTag + "<br/>") ;
%>


<!DOCTYPE html>
<html>
<head>
<<<<<<< HEAD
	<meta charset="UTF-8">
	<!-- 이 파일은 모든 문서에서 공용으로 참조할 파일입니다.  -->
	<!-- 자바 관련 변수 및 패키지 임포트, 네비게이션 바, jstl 등등 -->
	<!-- for sweet alert -->
	<script src="/js/sweetalert.js"></script>
	<style type="text/css">
		.alert-dismissible{margin: 10px;}
	</style>
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">

			<a class="navbar-brand" href="<%=notWithFormTag%>home">아파투게더</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item">
						<c:if test="${whologin eq 0}">
							<a class="nav-link" href="#">미로그인</a>
=======
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 이 파일은 모든 문서에서 공용으로 참조할 파일입니다.  -->
<!-- 자바 관련 변수 및 패키지 임포트, 네비게이션 바, jstl 등등 -->
<!-- for sweet alert -->

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<script src="/js/sweetalert.js"></script>
<style type="text/css">
.alert-dismissible {
	margin: 10px;
}

.navbar {
	font-family: "Black Han Sans";
	text-align: center;
}

.bigsize {
	font-size: 80px;
	margin-right: 150px;
	text-decoration: none;
	color: black;
	margin-top: 10px
	
}

.navbar-brand {
	margin-right: 150px;
}

.nav-item {
	font-size: 45px;
	margin-top: 15px;
	margin-right: 30px;
	font-family: 'Nanum Gothic';
	font-weight: bold;
	text-align: center;
}

#margin {
	margin: 50px;
	margin-left: 150px;
	margin-right: 150px;
}

.body {
	align-content: center
}

.navbar-logo {
	margin-right: 25px;
	font-size: 15px
}

.dropdown-item {
	font-size: 20px;
	font-family: 'Nanum Gothic';
	text-align: left;
}

#small {
	margin-top: -16px;
}

#behind {
	display: none;
}

.small-tip {
	font-size: 25px;
	text-decoration: none;
	color: grey;
	font-family: 'Nanum Gothic';
	padding-bottom: 19px;
	vertical-align: middle;
	display: block;
    padding: auto;
    font-weight: 600;
}


.tips {
	margin-top: 75px;
	margin-left: 70px;
	position: relative;
	cursor: pointer;
	font-weight: 600;
	
}
</style>
</head>

<body>
	type=${sessionScope.loginfo.mtype}
	<header id="margin">
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<a class="navbar-logo" href="#">로고 이미지</a> <a
					class=" navbar-brand bigsize" href="<%=notWithFormTag%>home">APTogether</a>
				
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						
						<!-- member section -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">가게</a>
								<ul class="dropdown-menu">
									<li>
										<a class="dropdown-item"href="<%=notWithFormTag%>meInsert">가게 목록(href : null)</a>
									</li>
									<li>
										<a class="dropdown-item" href="<%=notWithFormTag%>meLogin">내 가게 보기(href : null)</a>
									</li>
									<li>
										<a class="dropdown-item" href="<%=notWithFormTag%>meLogin">접수 현황(href : null)</a>
									</li>
								</ul>
						</li>
						
						<!-- board section -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">주문</a>
							<ul class="dropdown-menu">
								<c:if test="${whologin ne 0}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>myorList">주문내역</a></li>
								</c:if>
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>roList">모집 중인 주문</a></li>
							</ul></li>

						<!-- product section -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">이벤트</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>prList">이벤트(href : null)</a></li>
								<c:if test="${whologin eq 1}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>prInsert">이벤트 등록(href : null)</a></li>
								</c:if>
							</ul></li>

						<!-- view section -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">커뮤니티</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>vwList">투표(href : null)</a></li>
								<c:if test="${whologin eq 1}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>prInsert">투표 등록(href : null)</a></li>
								</c:if>
							</ul></li>
							
						<!-- [st] 로그인 섹션 -->							
						<c:if test="${whologin eq null }">
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogin">로그인</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meInsert">회원 가입</a></li>
>>>>>>> member_merge01
						</c:if>
						
						<c:if test="${whologin eq 1 }">
							<li class="tips"><a class="small-tip">주인님 !</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meList">회원 목록</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
<<<<<<< HEAD
					</li>
					
					<!-- member section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">회원</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meInsert">회원 가입</a></li>								
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogin">로그인</a></li>
							</c:if>		
							<c:if test="${whologin ne 0}">				
								<li><a class="dropdown-item" href="#">정보 수정</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meLogout">로그 아웃</a></li>
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">상세 보기</a></li>
								<li><a class="dropdown-item" href="#">탈퇴하기</a></li>
							</c:if>
							<c:if test="${whologin eq 2}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>meList">목록 보기</a></li>
							</c:if>
						</ul>
					</li>
					
					<!-- board section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">게시물</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin ne 0}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>boInsert">게시물 등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>boList">목록 보기</a></li>
						</ul>
					</li>
					
					<!-- product section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">상품</a>
						<ul class="dropdown-menu">
							<c:if test="${whologin eq 2}">
								<li><a class="dropdown-item" href="<%=notWithFormTag%>prInsert">상품 등록</a></li>
							</c:if>
							<li><a class="dropdown-item" href="<%=notWithFormTag%>prList">목록 보기</a></li>
						</ul>
					</li>		
					
					<!-- view section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">데이터 보기</a>
						<ul class="dropdown-menu">
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>vwList">목록 보기
								</a>
							</li>
						</ul>
					</li>	
					
					
					<!-- Store section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">가게</a>
						<ul class="dropdown-menu">
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>myStoreList&id=uiui">내 가게 보기
								</a>
							</li>
						</ul>
					</li>	
					
				</ul>
=======
						<c:if test="${whologin eq 2 }">
							<li class="tips"><a class="small-tip">${sessionScope.loginfo.name}
									사장님 </a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meDetail">마이페이지</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
						<c:if test="${whologin eq 3 }">
							<li class="tips"><a class="small-tip">${sessionScope.loginfo.name}님
							</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meDetail">마이페이지</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
						<!-- [ed] 로그인 섹션 -->
					</ul>
				</div>
>>>>>>> member_merge01
			</div>
		</nav>
		<hr id="small" />
	</header>
	<!--[st] AlertBox - danger(red) -->
	<c:if test="${not empty sessionScope.alertMessage}">
		<%-- 주의/경고/오류 메세지 --%>
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
			<strong>경고 메시지</strong> ${sessionScope.alertMessage}
		</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
<<<<<<< HEAD
	<c:remove var="alertMessage" scope="session"/> 
	
	
	<%-- 성공 메세지 --%>
	<c:if test="${not empty sessionScope.successAlertMsg}">
		<div class="alert alert-success alert-dismissible">
	    	<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    	<strong>성공!</strong> ${sessionScope.successAlertMsg}
	  	</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
	<c:remove var="successAlertMsg" scope="session"/>
	
	
=======
	<c:remove var="alertMessage" scope="session" />
	<!--[ed] AlertBox - danger(red) -->
>>>>>>> member_merge01
</body>
</html>