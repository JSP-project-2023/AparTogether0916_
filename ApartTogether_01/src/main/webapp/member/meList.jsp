<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:회원목록</title>
	<style type="text/css">
		.container{margin-top: 10px;}
		tr{opacity: 0.7;}
	</style>	
</head>
<body>
	<div class="container">
		<h2>회원 목록</h2>
		<p>회원 목록을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th>회원유형</th>
					<th>아이디</th>
					<th>이름</th>
					<th>닉네임</th>
					<th>프로필사진</th>
					<th>비밀 번호</th>
					<th>성별</th>
					<th>전화번호</th>
					<th>생일</th>
					<th>주소</th>				
				</tr>
				
			</thead>
			<tbody>
				<tr>
					<td colspan="10" align="right">${requestScope.pageInfo.pagingStatus}</td>		
				</tr>
				
				<c:forEach var="bean" items="${datalist}">
					<c:choose>
						<c:when test="${bean.mtype eq 'user'}">
							<tr class="table-primary">
						</c:when>
						<c:when test="${bean.mtype eq 'biz'}">
							<tr class="table-success">
						</c:when>
						<c:otherwise>
							<tr class="table-danger">
						</c:otherwise>
					</c:choose> 
				
					<td align="center">${bean.mtype}</td>
				
					<td align="center">${bean.id}</td>
					
					<td align="center">
						<a href="<%=notWithFormTag%>meDetail&id=${bean.id}">${bean.name}</a>
					</td>
					
					<td align="center">${bean.nickname}</td>
					
					<td align="center">${bean.profile}</td>
					
					<td>${bean.password}</td>
				
					<c:if test="${bean.gender eq 'male'}">
						<td>남자</td>
					</c:if>
					<c:if test="${bean.gender eq 'female'}">
						<td>여자</td>
					</c:if>	
					
					<td>${bean.phone}</td>
					<td>${bean.birth}</td>
					<td>${bean.address}</td>					
				</tr>
				</c:forEach>
				
			</tbody>
		</table>	
		${requestScope.pageInfo.pagingHtml}
	</div>	 
</body>
</html>