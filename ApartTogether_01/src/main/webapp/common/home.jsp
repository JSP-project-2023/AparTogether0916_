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
	<form action="<%=withFormTag%>" method="get">
	<input name="command" value="storeUpdate">
	<input name="id" value="soon">
	<input name="stno" value="1">
	<button type="submit">제출</button>
</form>
</body>
</html>
 