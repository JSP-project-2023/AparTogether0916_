<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ include file="./../common/common.jsp"%> --%>
<%@ include file="/common/bootstrap5.jsp"%>
<!-- 추후 삭제 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreMenuDetail.css" type="text/css">
	<title>가게 주문 상세 화면</title>
	</head>
<body>

	<%--가게명 변수--%>
	<c:set var="bean" value="${requestScope.bean}"/>
	<div class="store-container">
	<div class="store-info">
	<div class="store-body">
	<!-- 뱃지 카테고리 들어갈 부분. -->
	<c:if test="${bean.category eq '양식'}">
		<span class="badge rounded-pill" style="background-color: #51CEA1">양식</span>
	</c:if>
	<c:if test="${bean.category eq '중식'}">
		<span class="badge rounded-pill" style="background-color: #FF4E4E">중식</span>
	</c:if>
	<c:if test="${bean.category eq '일식'}">
		<span class="badge rounded-pill" style="background-color: #359EFF">일식</span>
	</c:if>
	<c:if test="${bean.category eq '한식'}">
		<span class="badge rounded-pill" style="background-color: #72CCFF">한식</span>
	</c:if>
	<c:if test="${bean.category eq '패스트푸드'}">
		<span class="badge rounded-pill" style="background-color: #FFCC15">패스트푸드</span>
	</c:if>
	<c:if test="${bean.category eq '치킨'}">
		<span class="badge rounded-pill" style="background-color: #FF9CBA">치킨</span>
	</c:if>
	<c:if test="${bean.category eq '피자'}">
		<span class="badge rounded-pill" style="background-color: #FFAB2E">피자</span>
	</c:if>
	<c:if test="${bean.category eq '카페'}">
		<span class="badge rounded-pill" style="background-color: #C18F61">카페</span>
	</c:if>
	
	<span id="stname">${bean.stname}</span>
		<div class="store-content">
			<div class="store-img">
				<img alt="이미지" src="${pageContext.request.contextPath}/uploadStoreImage/${bean.stlogo}" border="1px">
			</div>
			<div class="store-box">
				<div class="store-maininfo">
					<div class="store-subinfo">
						<span class="store-delivery-span title-bold">배달비</span>
						<span class="store-delivery-span title-bold">배달시간</span>
						<span class="store-intro-span title-bold">가게소개</span>
						<span class="store-intro-span title-bold">영업 시간</span>
						<span class="store-intro-span title-bold">휴무일</span>
						<span class="store-intro-span title-bold">가게 주소</span>
						<span class="store-intro-span title-bold">전화번호</span>
						<span class="store-intro-span title-bold">사업자등록번호</span>
					</div>
					<div class="store-subinfo">
						<span class="store-delivery-span">${bean.fee}원</span>
						<span class="store-delivery-span">${bean.btime}분</span>
						<span class="store-intro-span">${bean.content}</span>
						<span class="store-intro-span">${bean.sttime}</span>
						<span class="store-intro-span">${bean.redday}</span>
						<span class="store-intro-span">${bean.stplace}</span>
						<span class="store-intro-span">${bean.sttel}</span>
						<span class="store-intro-span">${bean.ceono}</span>
					</div>
				</div>
				<div class="order-button">
					<button>주문하기</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<hr style="margin-top: 100px;" size="10px">
		<div class="menu-container">
		<!-- 메뉴1개 들어가야될 공간 시작-->
		<div class="one-menu-box"> 
			<div class="memu-img">
				<img alt="이미지" src="${pageContext.request.contextPath}/uploadStoreImage/스크린샷1.png" border="1px">
			</div>
			<div class="menu-details">
				<span id="menu-title">수제돈정식 (등심1 + 치즈1 + 치킨1)</span>
				<span id="menu-exp">수제 돈까스 모둠 (양이 많아요)</span>
				<span id="menu-igrnt">등심1장 + 치즈1장 + 치킨1장</span>
				<span id="menu-price">12,900원</span>
			</div>
		</div>			
		<!-- 메뉴 들어가야될 공간 끝 -->
		
		</div>
	</div>
	
	<button id="orderBtn">주문</button>
</body>
</html>