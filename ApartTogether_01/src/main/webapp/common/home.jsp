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
<<<<<<< HEAD
		<!-- 아이디 히든으로 들어감. -->
		<input name="command" type="text" value="storeInsert">
		<!-- 가게 고유 번호 -->
		<button type="submit">가게 등록하러 가기</button>
	</form>
	
	<form action="<%=withFormTag%>" method="get">
		<!-- 아이디 히든으로 들어감. -->
		<input name="command" type="text" value="storeList">
		<!-- 가게 고유 번호 -->
		<button type="submit">가게 목록 보기</button>
	</form>
	
	
=======
	<input name="command" value="storeUpdate">
	<input name="id" value="soon">
	<input name="stno" value="82">
	<button type="submit">제출</button>
</form>
>>>>>>> lleebs
</body>
</html>
 