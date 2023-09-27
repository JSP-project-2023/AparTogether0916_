<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
	<!-- 주소 입력api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<!-- css파일 불러오기 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/InsertStore.css" type="text/css">
	
	<title>가게 정보 수정</title>
	</head>
	<body>
	
	<!-- hidden으로 사용자 아이디 받아옴. 가게 아이디 히든으로 받아옴. -->
	<strong class="title">내가게 상세보기</strong>
	<c:set var="bean" value="${requestScope.bean}"/>
	<hr>
	<div class="container1"><!-- 컨테이너 -->
		<form action="<%=withFormTag%>"> <!-- post? get? -->
		<input type="hidden" name="command" value="storeUpdate">
		<!-- 회원 아이디 -->
		<input type="hidden" value="${requestScope.id}" name="id" placeholder="회원아이디">
		<!-- 가게 고유번호-->
		<input type="text" value="${bean.stno}" name="stno" placeholder="회원아이디"><br>
		
		<div id="ctname">
			가게이름 <input name="stname" type="text" value="${bean.stname}" readonly><br>
		</div>
		<div id="category">
		<input name=selectedCategory id="selectedCategory" type="hidden" value="${bean.category}">
		카테고리 <input value="${bean.category}" readonly> <br>
		</div>
			
		<div id="stplace">
			<%--주소 api사용하여 주소지 받아옴.--%>
			가게주소 <input id="stplace1" name="stplace1" type="text" value="${staddr[0]}" readonly><br>
		가게상세 주소 <input id="stplace2" name="stplace2" type="text" value="${staddr[1]}" readonly><br> <!-- 상세 주소 컬럼 생성 후 삽입 -->
		</div>
		
		<div id="storeNumber">
			가게 전화번호
			<input type="number" value="${sttel[0]}" readonly style="width: 60px;">
			- <input class="snumber" value="${sttel[1]}" name="areacode2" type="number" value="" maxlength="4" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" readonly>
			- <input class="snumber" value="${sttel[2]}" name="areacode3" type="number" value="" maxlength="4" oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" readonly><br>
		</div>
			
		<div id="content">
			가게 소개 <input name="content" height="2" width="10" value="${bean.content}" readonly><br>
		</div>
		
		<div id="ceofile">
			사업자 등록증 <!-- <input name="ceofile" type="file" readonly><br> -->
			<input name="ceofileUpdate" type="text" value="${bean.ceofile}" readonly>
		</div>
		
		<div id="shoptime">
			가게운영시간
				<input value="${bean.sttime}" readonly>
			<br>
		</div>
			
		<div id="stlogo">
			가게로고 <!-- <input name="stlogo" type="file"/> -->
					<input name="stlogoUpdate" type="text" value="${bean.stlogo}" readonly>
			<br>
		</div>
		
		<div id="fee">
			배달비 <input name="fee" type="number" value="${bean.fee}" readonly><br>
		</div>
		
		<div id="redday">
			휴무일 <input name="redday" type="text" value="${bean.redday}" readonly><br>
		</div>
		
		<div id="ceono">
			사업자 등록번호 <input name="ceono" type="text" value="${bean.ceono}" readonly><br>
		</div>
			
		<div id="buttons">
			<a type="button" href="${pageContext.request.contextPath}/common/home.jsp">취소</a>
			<button type="button">메뉴 추가하기</button>
			<!-- 초기화 하기전에 컨펌창 출력 -->
		</div>
			
		</form>	
	</div>
	</body>
</html>