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
			<input type="hidden" name="command" value="menuManage">
			
			<select name="stno">
				<option value="-1">가게를 선택해주세요
				<c:forEach var="myStList" items="${requestScope.myStoreList}">
					<option value="${myStList.stno}">${myStList.stname}
				</c:forEach>
			</select>
			
			<button type="submit">가게 선택</button>
			<br>
		</form>
	</div>
	
	<!-- 해당 가게에 등록된 메뉴가 있다면 리스트로 출력 -->
	<c:if test="${requestScope.myMenuList ne null}">
		<div class="menu-container">
			<!-- 메뉴1개 들어가야될 공간 시작-->
			<div class="menu-control">
				<form action="" method="get">
					<input type="hidden" name="command" value="menuInsert">
					<input type="hidden" name="stno" value="${requestScope.getStno}">
					<button type="submit">메뉴 추가</button>
				</form>
			</div>
			
			<c:forEach var="menuList" items="${requestScope.myMenuList}">
				<div class="one-menu-box"> 
					<div class="memu-img">
						<img alt="이미지" src="${pageContext.request.contextPath}/upload/${menuList.menuimage}" border="1px">
					</div>
					
					<div class="menu-details">
						<span id="menu-title">${menuList.menuname}</span> <!-- 메뉴 이름 -->
						<span id="menu-exp">${menuList.menudetail}</span> <!-- 메뉴 설명 -->
						<span id="menu-price">${menuList.price}</span> <!-- 가격 -->
					</div>
					
					<div class="menu-control">
						<button>수정</button>
						<button>삭제</button>
					</div>
				</div>
			</c:forEach>
			<!-- 메뉴 들어가야될 공간 끝 -->
		</div>
	</c:if>

</body>
</html>