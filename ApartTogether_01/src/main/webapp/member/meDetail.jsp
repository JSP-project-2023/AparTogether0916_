<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:마이페이지</title>
	<style type="text/css">
		.container{margin:10px;}
		#backButton{margin: auto; }
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
	</style>
</head>
<body>
	<div class="container">
	
	
		<%-- accessMeDetail : 마이페이지 열람 가능 여부를 저장하는 변수입니다. 0(열람불가), 1(열람가능) --%>
		<c:set var="accessMeDetail" value="0"/>
		<c:if test="${whologin_id != requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 다른 사람의 마이페이지를 열람할 수 없습니다. --%>
			<c:set var="accessMeDetail" value="0"/>
		</c:if>
		<c:if test="${whologin_id == requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 본인의 마이페이지만 열람할 수 있습니다. --%>
			<c:set var="accessMeDetail" value="1"/>
		</c:if>
		<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
			<%-- 관리자 : 모든 회원의 마이페이지를 열람할 수 있습니다. --%>
			<c:set var="accessMeDetail" value="1"/>
		</c:if>
		
		<c:if test="${accessMeDetail == 0 }">
			<%-- 열람불가 --%>
			!!!-비정상적인 접근입니다. 다른 회원의 마이페이지는 열람하실 수 없습니다.-!!!
		</c:if>
		<c:if test="${accessMeDetail == 1 }">
			<%-- 열람가능 --%>
			<h2>${requestScope.bean.name}님의 회원 정보</h2>
			<table class="table" >
				<thead></thead>
				<tbody>
					<tr>
						<td align="center">회원유형</td>
						<td>
							<c:choose>
								<c:when test="${requestScope.bean.mtype == 'user' }">
									<span>일반회원</span>
								</c:when>
								<c:when test="${requestScope.bean.mtype == 'biz' }">
									<span>사업자</span>
								</c:when>
								<c:when test="${requestScope.bean.mtype == 'admin' }">
									<span>관리자</span>
								</c:when>
								<c:otherwise>
									<span>멤버유형 정보가 올바르지 않습니다.(user, biz, admin)</span>
								</c:otherwise>
							</c:choose>
						</td>
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
						<td>
							<img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  >
						</td>
					</tr>
					<tr>
						<td align="center">성별</td>
						<c:if test="${requestScope.bean.gender eq 'male'}">
							<td>남자</td>
						</c:if>
						<c:if test="${requestScope.bean.gender eq 'female'}">
							<td>여자</td>
						</c:if>	
						<c:if test="${requestScope.bean.gender == 'null'}">
							<td>NULL</td>
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
					<tr>
						<td align="center">비밀번호 질문</td>
						<td>${requestScope.bean.passwordquest}</td>
					</tr>
					<tr>
						<td align="center">비밀번호 답변</td>
						<td>${requestScope.bean.passwordanswer}</td>
					</tr>
				</tbody>
			</table>
			
			<div  align="center">
				<c:if test="${whologin ne 1}">	
					<!-- 일반회원(3),사업자(2)이면 비밀번호재설정, 정보수정, 탈퇴하기 버튼 보이기 -->	
					<!-- 관리자(1)이면 이 부분이 보이지 않습니다. -->		
					<%-- 이 줄은 삭제 예정입니다. <a type="button" href="<%=notWithFormTag%>meResetPassword&id=${bean.id}" class="btn btn-info">비밀번호재설정</a> --%>
					<a type="button" href="<%=notWithFormTag%>meUpdate&id=${bean.id}" class="btn btn-info">회원정보 수정</a>
					<a type="button" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}" class="btn btn-info">회원탈퇴하기</a>
				</c:if>	
			</div>
			
		</c:if>
	

		
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
		
		
	
		
			
	</div>
</body>
</html>