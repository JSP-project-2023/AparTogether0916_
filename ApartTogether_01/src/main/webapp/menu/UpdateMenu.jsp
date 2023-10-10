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
			<h2 class="mainTitle">'${requestScope.stname}' 가게  메뉴 수정</h2>
			<p class="subTitle">아래 항목을 입력해 메뉴를 수정해보세요.</p>
			
			<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
				<input type="hidden" name="command" value="menuUpdate">
				<c:set var="bean" value="${requestScope.menuBean}" />
				
				<!-- 가게 번호 -->
				<input type="hidden" name="stno" value="${bean.stno}">
				<input type="hidden" name="menuno" value="${bean.menuno}">
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">메뉴 이름</span>
					<input class="input_text"type="text" name="menuname" id="menuname" value="${bean.menuname}" placeholder="메뉴명을 입력해주세요">
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">가격</span>
					<input class="input_text" type="number" name="price" id="price" value="${bean.price}" placeholder="숫자만 입력해주세요">
				</div>
				
				<div class="info_items titleDepth1">
					<span class="mustInput">*</span><span class="input-field-label">메뉴 이미지</span>
					<input class="input_text input-file" type="file" name="menuimage" id="menuimage">
				</div>
			
				<div class="info_items" id="ceofile">
					<span class="input-field-label">기존에 업로드 된 파일</span>
					<input disabled="disabled" class="inputbg input_text" type="text" name="menuimageUp" value="${bean.menuimage}">
				</div>
				
				<div class="info_items">
					<span class="input-field-label">메뉴 설명</span>
					<textarea class="input_text input-textArea" name="menudetail" rows="5" cols="50" placeholder="메뉴 설명을 입력해주세요">${bean.menudetail}</textarea>
				</div>
				
				<div class="info_items">
					<span class="input-field-label">재료 | 기본옵션</span>
					<textarea class="input_text input-textArea" name="detailPlus" rows="5" cols="50" placeholder="해당 메뉴의 재료 또는 기본으로 제공되는 옵션을 입력해주세요">${requestScope.detailPlus}</textarea>
				</div>
				
				
				<div class="info_items btnArea" id="buttons">
					<button class="big_ctlbtn update_bigbtn" type="submit" onclick="return validation()">수정</button>
					<button class="big_ctlbtn cancle_bigbtn" type="button" onclick="history.go(-1)">취소</button> <!-- 이전 화면 그대로 이동 -->
					<button class="big_ctlbtn reset_bigbtn" type="reset">초기화</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>