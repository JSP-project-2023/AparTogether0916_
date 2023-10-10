<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 등록</title>

<!-- 자바 스크립트 파일 불러오기 --> <!-- 유효성 검사 Validation -->
<script src="${pageContext.request.contextPath}/menu/menuJS/InsertMenu.js"></script>
<!-- css파일 불러오기 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/menu/menuCSS/InsertMenu.css" type="text/css">

</head>
<body>
	<div class="container">
		<div class="container_content"><!-- 컨테이너 -->
			<!-- 내 가게 이름 출력 -->
			<h2 class="mainTitle">'${requestScope.stname}' 가게  메뉴 등록</h2>
			<p class="subTitle">아래 항목을 입력해 메뉴를 등록해보세요.</p>
			
			<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
				<input type="hidden" name="command" value="menuInsert">
				
				<!-- 가게 번호 -->
				<input type="hidden" name="stno" value="${requestScope.stno}">
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">메뉴 이름</span>
					<input type="text" name="menuname" id="menuname" class="input_text" placeholder="메뉴명을 입력해주세요">
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">가격</span>
					<input type="number" name="price" id="price" class="input_text" placeholder="숫자만 입력해주세요">
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">메뉴 이미지</span>
					<input class="input_text input-file" type="file" name="menuimage" id="menuimage">
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">메뉴 설명</span>
					<textarea class="input_text input-textArea" name="menudetail" rows="5" cols="50" placeholder="메뉴 설명을 입력해주세요"></textarea>
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">재료 | 기본옵션</span>
					<textarea class="input_text input-textArea" name="detailPlus" rows="5" cols="50" placeholder="해당 메뉴의 재료 또는 기본으로 제공되는 옵션을 입력해주세요"></textarea>
				</div>

				<div class="info_items btnArea" id="buttons">
					<button class="big_ctlbtn insert_bigbtn" type="submit" onclick="return validation()">등록</button>
					<button class="big_ctlbtn cancle_bigbtn" type="button" onclick="history.go(-1)">취소</button> <!-- 이전 화면 그대로 이동 -->
					<!-- 초기화 하기전에 컨펌창 출력 -->
					<button class="big_ctlbtn reset_bigbtn" type="reset">초기화</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>