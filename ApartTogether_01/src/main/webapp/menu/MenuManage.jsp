<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 관리</title>
</head>
<body>
<!-- stno, stname -->
	<h3>${sessionScope.loginfo.name} 사장님의 가게 메뉴 관리</h3>
	<p>가게 선택 후 버튼을 클릭하여 메뉴를 관리하세요</p>
	<div class="container">
		<form action="<%=withFormTag%>menuManage" method="post">
			<input type="hidden" name="command" value="menuManage">
			
			<select>
				<option value="none">가게를 선택해주세요
			</select>
			
		</form>
	</div>
</body>
</html>