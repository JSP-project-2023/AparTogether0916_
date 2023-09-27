<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 자바 스크립트 파일 불러오기 -->
<script src="${pageContext.request.contextPath}/store/storeJS/InsertStoreJS.js"></script>
<!-- 주소 입력api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/InsertStore.css" type="text/css">
<title>내 가게 등록</title>
</head>
<body>
	<div class="container">
	<div class="container_content"><!-- 컨테이너 -->
	<h2 class="mainTitle">내 가게 등록</h2>

		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="stInsert">
		<!-- 회원 아이디 -->
		<input type="hidden" value="${sessionScope.loginfo.id}" name="id" placeholder="회원아이디">
		<!-- 가게 고유번호-->
		<input type="hidden" value="가게 고유번호(시퀀스)" name="stno" placeholder="회원아이디">
		
		<div class="info_items" id="ctname">
			<span class="littleTitle">가게 이름</span>
			<input name="stname" type="text" placeholder="가게 이름">
		</div>
		
		<div class="info_items" id="category">
		<span class="littleTitle cateselect">카테고리</span>
		<select name="category">
			<option value="-">--선택--
			<option value="양식">양식
			<option value="중식">중식
			<option value="일식">일식
			<option value="한식">한식
			<option value="패스트푸드">패스트푸드
			<option value="치킨">치킨
			<option value="피자">피자
			<option value="카페">카페
		</select>
		</div>
			
		<div class="info_items titleDepth1" id="stplace">
			<%--주소 api사용하여 주소지 받아옴.--%>
			<span class="littleTitle">가게 주소</span><input id="stplace1" type="text" placeholder="클릭해서 주소찾기">
		 </div>
		 
		 <div class="info_items" id="stplace2">	
		 	<span class="littleTitle">가게 상세 주소</span><input id="stplace2" type="text" placeholder="가게 상세 주소">
		</div>
		
		<div class="info_items" id="storeNumber">
			<span class="littleTitle">가게 전화번호</span>
			<select name="areacode1">
				<option value="-">-선택-</option>
				<option value="010">010</option>
				<option value="02">02</option>
				<option value="070">070</option>
				<option value="042">042</option>
				<option value="041">041</option>
				<option value="513">513</option>
			</select>
			- <input class="snumber" name="areacode2" type="number" maxlength="4" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
			- <input class="snumber" name="areacode3" type="number" maxlength="4" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
		</div>
		
		<%//TODO 가게 소개 20글자로 제한.%>
		<div class="info_items" id="content">
			<span class="littleTitle">가게 소개</span>
			<textarea name="content" rows="2" cols="30" maxlength="20" placeholder="가게소개를 입력해주세요. (20자 제한)"></textarea>
		</div>
		
		<div class="info_items" id="ceofile">
			<span class="littleTitle">사업자 등록증</span>
			<input name="ceofile" class="imagefile" type="file">
		</div>
		
		<div class="info_items" id="shoptime">
			<span class="littleTitle">가게 운영시간</span>
				<!-- 시작시간 -->
				<select name="startShopAmPm" class="ShopAmPm">
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select name="startShopTime" class="ShopTime">
					<option value="00:00">00:00</option>
					<option value="00:30">00:30</option>
					<option value="01:00">01:00</option>
					<option value="01:30">01:30</option>
					<option value="02:00">02:00</option>
					<option value="02:30">02:30</option>
					<option value="03:00">03:00</option>
					<option value="03:30">03:30</option>
					<option value="04:00">04:00</option>
					<option value="04:30">04:30</option>
					<option value="05:00">05:00</option>
					<option value="05:30">05:30</option>
					<option value="06:00">06:00</option>
					<option value="06:30">06:30</option>
					<option value="07:00">07:00</option>
					<option value="07:30">07:30</option>
					<option value="08:00">08:00</option>
					<option value="08:30">08:30</option>
					<option value="09:00">09:00</option>
					<option value="09:30">09:30</option>
					<option value="10:00">10:00</option>
					<option value="10:30">10:30</option>
					<option value="11:30">11:00</option>
					<option value="11:30">11:30</option>
					<option value="12:00">12:00</option>
					<option value="12:30">12:30</option>
				</select> ~
				<!-- 종료시간 -->
				<select name="endShopAmPm" class="ShopAmPm">
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select name="endShopTime" class="ShopTime">
					<option value="00:00">00:00</option>
					<option value="00:30">00:30</option>
					<option value="01:00">01:00</option>
					<option value="01:30">01:30</option>
					<option value="02:00">02:00</option>
					<option value="02:30">02:30</option>
					<option value="03:00">03:00</option>
					<option value="03:30">03:30</option>
					<option value="04:00">04:00</option>
					<option value="04:30">04:30</option>
					<option value="05:00">05:00</option>
					<option value="05:30">05:30</option>
					<option value="06:00">06:00</option>
					<option value="06:30">06:30</option>
					<option value="07:00">07:00</option>
					<option value="07:30">07:30</option>
					<option value="08:00">08:00</option>
					<option value="08:30">08:30</option>
					<option value="09:00">09:00</option>
					<option value="09:30">09:30</option>
					<option value="10:00">10:00</option>
					<option value="10:30">10:30</option>
					<option value="11:30">11:00</option>
					<option value="11:30">11:30</option>
					<option value="12:00">12:00</option>
					<option value="12:30">12:30</option>
				</select>
		</div>
			
		<div class="info_items" id="stlogo">
			<span class="littleTitle">가게로고</span>
			<input name="stlogo" class="imagefile" type="file"><br>
			<span id="recommend-logo-size" class="recommend-logo-size">* 권장 크기 : 360*360 </span>
			 
		</div>
		
		<div class="info_items" id="fee">
			<span class="littleTitle">배달비</span>
			<input name="fee" type="number" placeholder="배달비">
		</div>
		
		<div class="info_items" id="btime">
			<span class="littleTitle">배달시간(분)</span>
			<input name="btime" type="number"><br>
		</div>
		
		<div class="info_items" id="redday">
			<span class="littleTitle">휴무일</span>
			<input name="redday" type="text" placeholder="예시) 매주 월요일">
		</div>
		
		<div class="info_items" id="ceono">
			<span class="littleTitle">사업자 등록번호</span>
			<input name="ceono" type="text" placeholder="사업자 등록번호">
		</div>
			
		<div class="info_items btnArea" id="buttons">
			<button class="big_ctlbtn insert_bigbtn" type="submit" onclick="return validation()">등록</button>
			<button class="big_ctlbtn else_bigbtn" type="button" onclick="history.go(-1)">취소</button>
			<!-- 초기화 하기전에 컨펌창 출력 -->
			<button class="big_ctlbtn else_bigbtn" type="reset">초기화</button>
		</div>
			
		</form>	
	</div>
	</div>
</body>
</html>