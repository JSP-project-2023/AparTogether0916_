<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%@ include file="/common/bootstrap5.jsp" %>
<%-- jstl을 위한 태그 라이브러리 선언 --%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- whologin 변수는 현재 로그인 상태를 알려 주는 변수입니다. -->
<!-- 미로그인(0) 일반 사용자(1) 사업자(2) 관리자(3)-->
<c:set var="whologin" value="0"/>
<c:if test="${not empty sessionScope.loginfo}">
	<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
		<c:set var="whologin" value="3"/>
	</c:if>
	<c:if test="${sessionScope.loginfo.mtype == 'biz'}">
		<c:set var="whologin" value="2"/>
	</c:if>
	<c:if test="${sessionScope.loginfo.mtype == 'user'}">
		<c:set var="whologin" value="1"/>
	</c:if>
</c:if>
whologin : ${whologin}
<%
	// appName : 애플리케이션 이름(프로젝트 이름_Teacher)
	String appName = request.getContextPath();
	String mappingName = "/Apartogether" ;  // in FrontController.java file
	
	// form 태그에서 사용할 변수
	String withFormTag = appName + mappingName ;
	
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
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
						</c:if>
						<c:if test="${whologin ne 0}">
							<a class="nav-link" href="#">${sessionScope.loginfo.name}님</a>
						</c:if>
					</li>
					
					<!-- store section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">가게</a>
						<ul class="dropdown-menu">
								<!-- 비로그인,일반회원,사업자,관리자 공통항목 -->
								<li><a class="dropdown-item" href="#">가게목록</a></li>
							<c:if test="${whologin eq 2}">	
								<!-- 사업자(2) -->			
								<li><a class="dropdown-item" href="#">내 가게 목록</a></li>
								<li><a class="dropdown-item" href="#">접수현황</a></li>
							</c:if>
						</ul>
					</li>
					
					<!-- 주문 section -->
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">주문</a>
						<ul class="dropdown-menu">
								<!-- 비로그인,일반회원,사업자,관리자 공통항목 -->
								<li><a class="dropdown-item" href="#">모집중인 주문</a></li>
							<c:if test="${whologin eq 2}">	
								<!-- 사업자(2) -->			
								<li><a class="dropdown-item" href="#">주문내역</a></li>
							</c:if>
						</ul>
					</li>
					
				
					<!-- 비로그인 : 로그인, 회원가입 -->
					<!-- 일반회원 : 김이박님, 마이페이지, 로그아웃 -->
					<!-- 사업자 : 김이박사장님, 마이페이지, 로그아웃 -->
					<!-- 관리자 : 관리자님, 회원목록, 로그아웃 -->
					
					<c:if test="${whologin eq 0}">
							<!-- 비로그인(0) -->
							<a class="nav-link" href="<%=notWithFormTag%>meLogin">로그인</a>
							<a class="nav-link" href="<%=notWithFormTag%>meInsert">회원가입</a>
					</c:if>
					<c:if test="${whologin eq 1}">
							<!-- 일반회원(1) -->
							<a class="nav-link" href="#">${sessionScope.loginfo.name}님</a>
							<a class="nav-link" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a>
							<a class="nav-link" href="<%=notWithFormTag%>meLogout">로그아웃</a>
					</c:if>
					<c:if test="${whologin eq 2}">
							<!-- 사업자(2) -->
							<a class="nav-link" href="#">${sessionScope.loginfo.name}사장님</a>
							<a class="nav-link" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a>
							<a class="nav-link" href="<%=notWithFormTag%>meLogout">로그아웃</a>
					</c:if>
					<c:if test="${whologin eq 3}">
							<!-- 관리자(3) -->
							<a class="nav-link" href="#">${sessionScope.loginfo.name}주인님</a>
							<a class="nav-link" href="<%=notWithFormTag%>meList">회원목록</a>
							<a class="nav-link" href="<%=notWithFormTag%>meLogout">로그아웃</a>
					</c:if>
						
				</ul>
			</div>
		</div>
	</nav>
	
	<c:if test="${not empty sessionScope.alertMessage}">
		<%-- 사용자에게 주의/경고/오류 등을 알려 주기 위한 Alert Box --%>
		<div class="alert alert-danger alert-dismissible">
	    	<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	    	<strong>경고 메세지</strong> ${sessionScope.alertMessage}
	  	</div>
	</c:if>
	<%-- 보여준 Alert Box를 session 영역에서 제거합니다. --%>
	<c:remove var="alertMessage" scope="session"/> 
</body>
</html>
