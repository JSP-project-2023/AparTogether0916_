<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreMenuDetail.css" type="text/css">

<style type="text/css">
	.menu-container {
		margin-bottom: 150px;
	}
</style>

<title>메뉴 관리</title>
</head>
<body>
<!-- stno, stname -->
	<h3>${sessionScope.loginfo.name} 사장님의 가게 메뉴 관리</h3>
	<p>가게 선택 후 버튼을 클릭하여 메뉴를 관리하세요</p>
	<div class="container">
		<form action="<%=withFormTag%>" method="post">
			<c:set var="myStoreList" value="${requestScope.obj.get(0)"/>
			
			<input type="hidden" name="command" value="menuManage">
<%-- 			<input type="hidden" name="bizid" value="${requestScope.myStoreList[0].id}"> --%>
<%-- 			<br>${requestScope.obj[1]} --%>
			<select name="stno">
				<option value="-1">가게를 선택해주세요
				<c:forEach var="myStList" items="${myStoreList}">
					<option value="${myStList.stno}">${myStList.stname}
				</c:forEach>
			</select>
			
			<button type="submit">가게 선택</button>
			<br>
		</form>
	</div>
	
	<c:if test="${requestScope.myMenuList[1] ne null}">
		<div class="menu-container">
			<!-- 메뉴1개 들어가야될 공간 시작-->
			<c:forEach var="menuList" items="${requestScope.myMenuList}">
				<div class="one-menu-box"> 
					<div class="memu-img">
						<img alt="이미지" src="${pageContext.request.contextPath}/upload/${menuList.menuimage}" border="1px">
					</div>
					<div class="menu-details">
						<span id="menu-title">${menuList.menuname}</span>
						<span id="menu-exp">${menuList.menudetail}</span>
<%-- 						<span id="menu-igrnt">${menuList.}</span> --%>
						<span id="menu-price">${menuList.price}</span>
					</div>
				</div>
			</c:forEach>
			<!-- 메뉴 들어가야될 공간 끝 -->
		</div>
	</c:if>

</body>
</html>