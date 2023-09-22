<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreMenuDetail.css" type="text/css">
	<title>가게 주문 상세 화면</title>
	</head>
<body>
	
	<div class="store-container">
	<div class="store-info">
	<div class="store-body">
	<!-- 뱃지 카테고리 들어갈 부분. -->
	<span id="stname">가게명</span>
		<div class="store-content">
			<div class="store-img">
				<img alt="이미지" src="${pageContext.request.contextPath}/uploadStoreImage/스크린샷1.png" border="1px">
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
						<span class="store-delivery-span">3000원</span>
						<span class="store-delivery-span">40분</span>
						<span class="store-intro-span">경양식 돈까스</span>
						<span class="store-intro-span">오후 09:00 ~ 오전 08:00</span>
						<span class="store-intro-span">일요일</span>
						<span class="store-intro-span">서울 서초구 서초대로 38 삼성아파트 22동 201호</span>
						<span class="store-intro-span">010-2544-5698</span>
						<span class="store-intro-span">787-454-11235</span>
					</div>
				</div>
				<div class="order-button">
					<button>주문하기</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<hr style="margin-top: 100px;">
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