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
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 이 파일은 모든 문서에서 공용으로 참조할 파일입니다.  -->
<!-- 자바 관련 변수 및 패키지 임포트, 네비게이션 바, jstl 등등 -->
<!-- for sweet alert -->
<!-- css파일 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/common/commonCSS/common.css" type="text/css">
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


</head>

<body>
	type=${sessionScope.loginfo.mtype}
	
	<header>
		<nav class="navbar">
		
		<!-- 로고 이미지 -->
			<div class="navbar-logo">
				<a href="<%=notWithFormTag%>home"><img alt="logoImg" src="${pageContext.request.contextPath}/image/logo.jpg"></a>
			</div>
			
			<!-- 드롭박스 목록 -->
			<ul class="navbar-nav">
			
				<!-- 가게 -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">가게</a>
					
					<ul class="dropdown-menu">
						<li>
							<a class="dropdown-item"href="<%=notWithFormTag%>stList">가게 목록</a>
						</li>

						<c:if test="${whologin eq 2}">
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>myStoreList&id=${sessionScope.loginfo.id}">내 가게 관리</a>
							</li>
							<li>
								<a class="dropdown-item" href="<%=notWithFormTag%>menuManage">메뉴 관리</a>
							</li>
						</c:if>
					</ul>
				</li>
				
				<!-- 주문 -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">주문</a>
					<ul class="dropdown-menu">
						<c:if test="${whologin ne 0}">
							<li><a class="dropdown-item"
								href="<%=notWithFormTag%>myorList">주문내역(href : null)</a></li>
						</c:if>
						<li><a class="dropdown-item"
							href="<%=notWithFormTag%>roList">모집 중인 주문(href : null)</a></li>
					</ul></li>

				<!-- 이벤트 -->
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

				<!-- 투표게시판 -->
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

				</c:if>
				
				<c:if test="${whologin eq 1 }">
					<li class="tips"><a class="small-tip">주인님 !</a></li>
					<li class="tips"><a class="small-tip"
						href="<%=notWithFormTag%>meList">회원 목록</a></li>
					<li class="tips"><a class="small-tip"
						href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
				</c:if>
				
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
			</ul>
		</nav>
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
	<c:remove var="alertMessage" scope="session" />
	<!--[ed] AlertBox - danger(red) -->
	
	
	<%-- 성공 메세지 --%>
	<c:if test="${not empty sessionScope.successAlertMsg}">
		<div class="alert alert-success alert-dismissible">
	    	<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    	<strong>성공!</strong> ${sessionScope.successAlertMsg}
	  	</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
	<c:remove var="successAlertMsg" scope="session"/>
	
</body>
</html>