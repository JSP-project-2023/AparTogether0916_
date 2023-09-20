<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Insert</title>
</head>
<body>
	<div class="container">
		<!-- 내 가게 이름 나오게 -->
		<h3>메뉴 등록 (${requestScope.myStore.stname})</h3>
		<p>아래 항목을 입력해 메뉴를 등록해보세요.</p>
		
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="menuInsert">
			
			<!-- 가게 번호 -->
			가게 번호 <input type="text" name="stno" value="${requestScope.storeInfo.stno}" disabled="disabled"><br>
			메뉴 이름 <input type="text" name="menuname" id="menuname"><br>
			가격 <input type="text" name="price" id="price"><br>
			메뉴 이미지 <input type="file" name="menuimage" id="menuimage"><br>
			메뉴 상세 설명 <textarea rows="5" cols="50"></textarea>
			
			
			<button type="submit">등록</button>
			<a type="button" href="history.back();">취소</a>
			<!-- 초기화 하기전에 컨펌창 출력 -->
			<button type="reset">초기화</button>
		</form>
	</div>
</body>
</html>