<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  
</head>
<body>
	샘플
	<%=request.getContextPath()%>
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stUpdate">
		<input name="id" value="soon">
		<input name="stno" value="1">
		<button type="submit">수정</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get" target="_blank">
		<input name="command" value="stDetail">
		<input name="id" value="soon">
		<input name="stno" value="1">
		<button type="submit">상세보기</button>
	</form>
</body>
</html>
 