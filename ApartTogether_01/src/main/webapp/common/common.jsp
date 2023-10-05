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
	<c:set var="whologin_id" value="${sessionScope.loginfo.id}"/>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/commonCSS/common.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/common/commonCSS/header.css" type="text/css">
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
	
	
	<script type="text/javascript">
		function openHam() {
			if ($('#ham_bg').hasClass('dontshow') == true) {
				$('#ham_bg').removeClass('dontshow');
				
			} else {
				$('#ham_bg').addClass('dontshow');
			}
		}
	</script>
	
</head>

<body>
	<div class="header_underline">
		<header class="header">
			<nav class="navbar_hole">
				<div class="navbar_logo">
					<a class="navbar-logo" href="<%=notWithFormTag%>home">
						<img width="150px" alt="logoIMG" src="${pageContext.request.contextPath}/image/logo.png">
					</a>
				</div>
				
				<!-- Store section -->
				<ul class="navbar_menu">
					<li class="">
						<a class="" href="#" role="button">가게</a>
						<ul class="subnavbar_menu">
							<li>
								<a class="" href="<%=notWithFormTag%>stList">가게 목록</a>
							</li>
		
							<c:if test="${whologin eq 2}">
								<li>
									<a href="<%=notWithFormTag%>myStoreList&id=${sessionScope.loginfo.id}">내 가게 관리</a>
								</li>
								<li>
									<a href="<%=notWithFormTag%>menuManage">메뉴 관리</a>
								</li>
							</c:if>
						</ul>
					</li>
					
					<!-- Order section -->
					<li class="">
						<a class="" href="#" role="button">주문</a>
						<ul class="subnavbar_menu">
							<c:if test="${whologin ne 0}">
								<li>
									<a class="" href="<%=notWithFormTag%>myorList">주문내역(href : null)</a>
								</li>
							</c:if>
							<li>
								<a class="" href="<%=notWithFormTag%>roList">모집 중인 주문(href : null)</a>
							</li>
						</ul>
					</li>
					
					<%-- <!-- Event section -->
					<li class="">
						<a class="" href="#" role="button">이벤트</a>
						<ul class="subnavbar_menu">
							<li>
 								<a class="" href="<%=notWithFormTag%>prList">이벤트(href : null)</a>
 							</li>
							<c:if test="${whologin eq 1}">
								<li>
									<a class="" href="<%=notWithFormTag%>prInsert">이벤트 등록(href : null)</a>
								</li>
							</c:if>
						</ul>
					</li> --%>
					
					<!-- Community section -->
					<li class="">
						<a class="" href="#" role="button">커뮤니티</a>
						<ul class="subnavbar_menu">
							<li>
								<a class="" href="<%=notWithFormTag%>voteList">투표</a>
							</li>
							<c:if test="${whologin ne 0}">
								<li>
									<a class="" href="<%=notWithFormTag%>voteInsert">투표 등록</a>
								</li>
							</c:if>
						</ul>
					</li>
				</ul>
				
				<ul class="navbar_member">
					<!-- [st] 로그인 섹션 -->							
					<c:if test="${whologin eq null }">
						<li class="">
							<a class="" href="<%=notWithFormTag%>meLogin">로그인</a>
						</li>
						<li class="">
							<a class="" href="<%=notWithFormTag%>meInsert">회원 가입</a>
						</li>
					</c:if>
					
					<c:if test="${whologin eq 1 }">
						<li class=""><a class="">주인님 !</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meList">회원 목록</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					
					<c:if test="${whologin eq 2 }">
						<li class=""><a class="">${sessionScope.loginfo.name} 사장님 </a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					
					<c:if test="${whologin eq 3 }">
						<li class=""><a class="">${sessionScope.loginfo.name}님</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					<!-- [ed] 로그인 섹션 -->
				</ul>
				
				<a href="#" class="navbar_toggleBtn"><i class="fa fa-bars" aria-hidden="true"></i></a> <!-- aria-hidden="true" -->
			</nav>
			
<!-- 			햄버거 버튼 -->
			<nav class="hamnav">
				<div class="navbar_logo">
					<a class="navbar-logo" href="<%=notWithFormTag%>home">
						<img width="150px" alt="logoIMG" src="${pageContext.request.contextPath}/image/logo.png">
					</a>
				</div>
				<div class="hamarea">
					<div class="hamburger" id="hamburger">
						<a href="javascript:openHam();">
							<span class="hamline" id="hamline"></span>
							<span class="hamline" id="hamline"></span>
							<span class="hamline" id="hamline"></span>
						</a>
					</div>
				</div>
			</nav>
			
<!-- 			버튼 눌렀을 때 나오는 영역 -->
			<div class="ham_bg dontshow" id="ham_bg">
				<!-- Store section -->
				<ul class="hamnav_hole">
					<li class="hamnav_menu">
						<span>가게</span>
						
						<ul class="sub_hamnav_menu">
							<li>
								<a class="" href="<%=notWithFormTag%>stList">가게 목록</a>
							</li>
		
							<c:if test="${whologin eq 2}">
								<li>
									<a class="" href="<%=notWithFormTag%>myStoreList&id=${sessionScope.loginfo.id}">내 가게 관리</a>
								</li>
								<li>
									<a class="" href="<%=notWithFormTag%>menuManage">메뉴 관리</a>
								</li>
							</c:if>
						</ul>
					</li>
					
					<!-- Order section -->
					<li class="hamnav_menu">
						<span>주문</span>
						<ul class="sub_hamnav_menu">
							<c:if test="${whologin ne 0}">
								<li>
									<a class="" href="<%=notWithFormTag%>myorList">주문내역(href : null)</a>
								</li>
							</c:if>
							<li>
								<a class="" href="<%=notWithFormTag%>roList">모집 중인 주문(href : null)</a>
							</li>
						</ul>
					</li>
					
					<%-- <!-- Event section -->
					<li class="hamnav_menu">
						<span>이벤트</span>
						<ul class="sub_hamnav_menu">
							<li>
								<a class="" href="<%=notWithFormTag%>prList">이벤트(href : null)</a>
							</li> 
							<c:if test="${whologin eq 1}">
								<li>
									<a class="" href="<%=notWithFormTag%>prInsert">이벤트 등록(href : null)</a>
								</li>
							</c:if>
						</ul>
					</li> --%>
					
					<!-- Community section -->
					<li class="hamnav_menu">
						<span>커뮤니티</span>
						<ul class="sub_hamnav_menu">
							<li>
								<a class="" href="<%=notWithFormTag%>voteList">투표</a>
							</li>
							<c:if test="${whologin eq 1}">
								<li>
									<a class="" href="<%=notWithFormTag%>voteInsert">투표 등록(href : null)</a>
								</li>
							</c:if>
						</ul>
					</li>
				</ul>
				
				<ul class="hamnav_member">
					<!-- [st] 로그인 섹션 -->							
					<c:if test="${whologin eq null }">
						<li class="">
							<a class="" href="<%=notWithFormTag%>meLogin">로그인</a>
						</li>
						<li class="">
							<a class="" href="<%=notWithFormTag%>meInsert">회원 가입</a>
						</li>
					</c:if>
					
					<c:if test="${whologin eq 1 }">
						<li class=""><a class="">주인님 !</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meList">회원 목록</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					
					<c:if test="${whologin eq 2 }">
						<li class=""><a class="">${sessionScope.loginfo.name} 사장님 </a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					
					<c:if test="${whologin eq 3 }">
						<li class=""><a class="">${sessionScope.loginfo.name}님</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meDetail&id=${sessionScope.loginfo.id}">마이페이지</a></li>
						<li class=""><a class="" href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
					</c:if>
					<!-- [ed] 로그인 섹션 -->
				</ul>
			</div>
		</header>
	</div>
	
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