<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>아파~투게터</title>
</head>
<body>
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stDetail">
		<input name="id" value="seller">
		<input name="stno" value="1">
		<button type="submit">상세보기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stMuDetail">
		<input name="stno" value="1">
		<%-- 실제로는 가게 넘버만 넘어가면 됨--%>
		<button type="submit">가게 메뉴 상세보기</button>
	</form>
	
</body>
</html>