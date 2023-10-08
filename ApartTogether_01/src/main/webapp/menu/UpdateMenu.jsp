<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 수정</title>

<!-- 자바 스크립트 파일 불러오기 --> <!-- 유효성 검사 Validation -->
<script src="${pageContext.request.contextPath}/menu/menuJS/UpdateMenu.js"></script>
<!-- 주소 입력api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/menu/menuCSS/InsertMenu.css" type="text/css">

</head>
<body>
	<div class="container">
		<div class="container_content"><!-- 컨테이너 -->
			<h2 class="mainTitle">[${requestScope.stname}] 가게  메뉴 수정</h2>
			<p class="subTitle">아래 항목을 입력해 메뉴를 수정해보세요.</p>
			
			<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
				<input type="hidden" name="command" value="menuUpdate">
				<c:set var="bean" value="${requestScope.menuBean}" />
				
				<!-- 가게 번호 -->
				<input type="hidden" name="stno" value="${bean.stno}">
				<input type="hidden" name="menuno" value="${bean.menuno}">
				
				<div class="info_items">
					<span class="littleTitle">메뉴 이름</span>
					<input type="text" name="menuname" id="menuname" value="${bean.menuname}">
				</div>
				
				<div class="info_items">
					<span class="littleTitle">가격</span>
					<input type="number" name="price" id="price" value="${bean.price}">
				</div>
				
				<div class="info_items titleDepth1">
					<span class="littleTitle">메뉴 이미지</span>
					<input class="imagefile" type="file" name="menuimage" id="menuimage">
				</div>
			
				<div class="info_items" id="ceofile">
					<span class="littleTitle">기존에 업로드 된 파일</span>
					<input class="inputbg" type="text" name="menuimageUp" value="${bean.menuimage}">
				</div>
				
				<div class="info_items">
					<span class="littleTitle">메뉴 설명</span>
					<textarea name="menudetail" rows="5" cols="50">${bean.menudetail}</textarea>
				</div>
				
				<div class="info_items">
					<span class="littleTitle">재료 | 기본옵션</span>
					<textarea name="detailPlus" rows="5" cols="50">${requestScope.detailPlus}</textarea>
				</div>
				
				
				<div class="info_items btnArea" id="buttons">
					<button class="big_ctlbtn update_bigbtn" type="submit" onclick="return validation()">수정</button>
					<button class="big_ctlbtn cancle_bigbtn" type="button" onclick="history.go(-1)">취소</button> <!-- 이전 화면 그대로 이동 -->
					<button class="big_ctlbtn reset_bigbtn" type="reset">초기화</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>