<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 등록 페이지 입니다.</title>
</head>
<body>
	<h3>내가게 등록</h3>

	<div><!-- 컨테이너 -->
	<input type="text" value="admin" name="" placeholder="회원아이디">
		<form action="" method="get">
			가게이름 <input type="text"><br>
			카테고리<input type="text"><br>
			가게위치<input type="text"><br>
			가게 전화번호
			<select>
				<option value="010">010</option>
				<option value="010">02</option>
				<option value="010">070</option>
				<option value="010">042</option>
				<option value="010">041</option>
				<option value="010"> 513</option>
			</select>
			-<input type="number">
			-<input type="number"><br>
			
			가게 소개 <textarea rows="2" cols="10"></textarea><br>
			사업자 등록증<input type="text"><br>
			가게운영시간
				<!-- 시작시간 -->
				<select>
					<option>오전</option>
					<option>오후</option>
				</select>
				<select>
					<option>00:00</option>
					<option>00:30</option>
					<option>01:00</option>
					<option>01:30</option>
					<option>02:00</option>
					<option>02:30</option>
					<option>03:00</option>
					<option>03:30</option>
					<option>04:00</option>
					<option>04:30</option>
					<option>05:00</option>
					<option>05:30</option>
					<option>06:00</option>
					<option>06:30</option>
					<option>07:00</option>
					<option>07:30</option>
					<option>08:00</option>
					<option>08:30</option>
					<option>09:00</option>
					<option>09:30</option>
					<option>10:00</option>
					<option>11:30</option>
					<option>12:00</option>
					<option>12:30</option>
				</select> ~
				<!-- 종료시간 -->
				<select>
					<option>오전</option>
					<option>오후</option>
				</select>
				<select>
					<option>00:00</option>
					<option>00:30</option>
					<option>01:00</option>
					<option>01:30</option>
					<option>02:00</option>
					<option>02:30</option>
					<option>03:00</option>
					<option>03:30</option>
					<option>04:00</option>
					<option>04:30</option>
					<option>05:00</option>
					<option>05:30</option>
					<option>06:00</option>
					<option>06:30</option>
					<option>07:00</option>
					<option>07:30</option>
					<option>08:00</option>
					<option>08:30</option>
					<option>09:00</option>
					<option>09:30</option>
					<option>10:00</option>
					<option>11:30</option>
					<option>12:00</option>
					<option>12:30</option>
				</select>
			<br>
			
			가게로고<input type="file"><br>
			배달비<input type="number"><br>
			휴무일<input type="text"><br>
			사업자 등록번호<input type="text"><br>
		
			
		</form>	
	</div>
</body>
</html>