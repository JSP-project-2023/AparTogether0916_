<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:마이페이지</title>
	<style type="text/css">
		.container{margin:10px;}
		#backButton{margin: auto; }
	</style>
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.name}님의 회원 정보</h2>
		<table class="table">
			<thead></thead>
			<tbody>
			
			
				<tr>
					<td align="center">회원유형</td>
					<td>${requestScope.bean.mtype}</td>
				</tr>
				<tr>
					<td align="center">아이디</td>
					<td>${requestScope.bean.id}</td>
				</tr>
				
				<tr>
					<td align="center">이름</td>
					<td>${requestScope.bean.name}</td>
				</tr>
				<tr>
					<td align="center">닉네임</td>
					<td>${requestScope.bean.nickname}</td>
				</tr>
				<tr>
					<td align="center">프로필 사진</td>
					<td>${requestScope.bean.profile}</td>
				</tr>
				
				<tr>
					<td align="center">성별</td>
					<c:if test="${requestScope.bean.gender eq 'male'}">
						<td>남자</td>
					</c:if>
					<c:if test="${requestScope.bean.gender eq 'female'}">
						<td>여자</td>
					</c:if>	
				</tr>
				<tr>
					<td align="center">전화번호</td>
					<td>${requestScope.bean.phone}</td>
				</tr>
				<tr>
					<td align="center">생일</td>
					<td>${requestScope.bean.birth}</td>
				</tr>
				<tr>
					<td align="center">주소</td>
					<td>${requestScope.bean.address}</td>
				</tr>
			</tbody>
		</table>
		
		<div  align="center">
			<c:if test="${whologin ne 1}">	
				<!-- 일반회원(3),사업자(2)이면 비밀번호재설정, 정보수정, 탈퇴하기 버튼 보이기 -->	
				<!-- 관리자(1)이면 이 부분이 보이지 않습니다. -->		
				<a type="button" href="meFindPassword.jsp" class="btn btn-info">비밀번호재설정</a>
				<a type="button" href="meFindPassword.jsp" class="btn btn-info">회원정보 수정</a>
				<a type="button" href="#" class="btn btn-info">회원탈퇴하기</a>
			</c:if>	
		</div>
		
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
			
	</div>
</body>
</html>