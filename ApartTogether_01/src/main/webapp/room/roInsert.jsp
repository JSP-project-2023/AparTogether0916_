<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		
		<h2>주문 방 정보 입력</h2>
		<h2> 방 제목, 배달 위치를 정하는 페이지 입니다. </h2>
		<form action="<%=withFormTag%>" method="post">
			<input type="text" name="command" value="roDetail"> 
			<input type="text" name="stno" value = "${requestScope.stno}">
			<input type="text" id = "roomname" name="roomname" placeholder="방 제목을 입력해주세요">
			<input type="text" id = "orderplace" name="orderplace" placeholder="주문 장소를 입력해주세요">
			<button type="submit" >입력 완료</button>
		</form>
	</div>
	
	
</body>
</html>