<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 자바 스크립트 파일 불러오기 -->
<script src="./storeJS/s.js"></script>

<!-- 주소 입력api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- css파일 불러오기 -->
<link rel="stylesheet" href="./storeCSS/InsertStore.css" type="text/css">
<title>가게 등록 페이지 입니다.</title>
</head>
<body>
		<strong class="title">내가게 등록</strong>

	<hr>
	<div class="container1"><!-- 컨테이너 -->
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data"> <!-- post? get? -->
		<input type="hidden" name="command" value="storeInsert">
		<!-- 회원 아이디 -->
		<input type="hidden" value="admin" name="id" placeholder="회원아이디">
		<!-- 가게 고유번호-->
		<input type="hidden" value="가게 고유번호(시퀀스)" name="stno" placeholder="회원아이디"><br>
		
		<div id="ctname">
			가게이름 <input name="stname" type="text"><br>
		</div>
		
		<div id="category">
		카테고리 <select name="category">
				<option value="-">--선택--
				<option value="양식">양식
				<option value="중식">중식
				<option value="일식">일식
				<option value="한식">한식
				<option value="패스트푸드">패스트푸드
				<option value="치킨">치킨
				<option value="피자">피자
				<option value="카페">카페
			</select><br>
		</div>
			
		<div id="stplace">
			<%--주소 api사용하여 주소지 받아옴.--%>
			가게주소 <input id="stplace1" name="stplace1" type="text"><br>
		가게상세 주소 <input id="stplace2" name="stplace2" type="text"><br>
		</div>
		
		<div id="storeNumber">
			가게 전화번호
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
			- <input class="snumber" name="areacode3" type="number" maxlength="4" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"><br>
		</div>
			
		<div id="content">
			가게 소개 <textarea name="content" rows="2" cols="10"></textarea><br>
		</div>
		
		<div id="ceofile">
			사업자 등록증 <input name="ceofile" type="file"><br>
		</div>
		
		<div id="shoptime">
			가게운영시간
				<!-- 시작시간 -->
				<select name="startShopAmPm">
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select name="startShopTime">
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
				<select name="endShopAmPm">
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select name="endShopTime">
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
			<br>
		</div>
			
		<div id="stlogo">
			가게로고 <input name="stlogo" type="file">파일</input><br>
		</div>
		
		<div id="fee">
			배달비 <input name="fee" type="number"><br>
		</div>
		
		<div id="redday">
			휴무일 <input name="redday" type="text"><br>
		</div>
		
		<div id="ceono">
			사업자 등록번호 <input name="ceono" type="text"><br>
		</div>
			
		<div id="buttons">
			<button type="submit" onclick="return validation()">등록</button>
			<a type="button" href="./../common/home.jsp">취소</a>
			<!-- 초기화 하기전에 컨펌창 출력 -->
			<button type="reset">초기화</button>
		</div>
			
		</form>	
	</div>
</body>
</html>