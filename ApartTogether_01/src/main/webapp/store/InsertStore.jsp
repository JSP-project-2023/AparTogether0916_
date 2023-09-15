<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 등록 페이지 입니다.</title>
</head>
<body>
	<h3>내가게 등록</h3>

	<div><!-- 컨테이너 -->
	
		<form action="<%=withFormTag%>" method="get"> <!-- post? get? -->
		<!-- 회원 아이디 -->
		<input type="text" value="admin" name="id" placeholder="회원아이디">
		<!-- 가게 고유번호 생성 -->
		<input type="text" value="가게 고유번호" name="StoreNum" placeholder="회원아이디"><br>
		
			가게이름 <input type="text"><br>
			카테고리<input type="text"><br>
			가게위치<input type="text"><br>
			가게 전화번호
			<select name="areacode1">
				<option value="010">010</option>
				<option value="02">02</option>
				<option value="070">070</option>
				<option value="042">042</option>
				<option value="041">041</option>
				<option value="513">513</option>
			</select>
			-<input name="areacode2" type="number">
			-<input name="areacode3" type="number"><br>
			
			가게 소개 <textarea rows="2" cols="10"></textarea><br>
			사업자 등록증<input type="text"><br>
			가게운영시간
				<!-- 시작시간 -->
				<select >
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select>
					<option value="00:00">00:00</option>
					<option value="00:30">00:30</option>
					<option value=" 01:00">01:00</option>
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
					<option value="11:30">11:30</option>
					<option value="12:00">12:00</option>
					<option value="12:30">12:30</option>
				</select> ~
				<!-- 종료시간 -->
				<select>
					<option value="am">오전</option>
					<option value="pm">오후</option>
				</select>
				<select>
					<option value="00:00">00:00</option>
					<option value="00:30">00:30</option>
					<option value=" 01:00">01:00</option>
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
					<option value="11:30">11:30</option>
					<option value="12:00">12:00</option>
					<option value="12:30">12:30</option>
				</select>
			<br>
			
			가게로고<input type="file"><br>
			배달비<input type="number"><br>
			휴무일<input type="text"><br>
			사업자 등록번호<input type="text"><br>
			
			<button type="submit">등록</button>
			<a type="button" href="common/home.jsp">취소</a>
			<!-- 초기화 하기전에 컨펌창 출력 -->
			<button type="reset">초기화</button>
		</form>	
	</div>
</body>
</html>