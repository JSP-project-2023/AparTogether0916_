<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파투게더:투표하기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/voteView.css" type="text/css">
<script src="${pageContext.request.contextPath}/vote/voteJS/voteJS.js"></script>
</head>

<body>
	<div class="container">
		<div class="voteBG">
		
			<h2 class="title" align="center">투표</h2>
			<div class="content-box">
				<div class="votetitle">
					<span>${requestScope.title.votetitle}</span>
				</div>
				
				<form action="<%=withFormTag%>" method="post">
					<input name="command" value="voteDo" hidden="hidden">
					<!-- 사용자 id -->
					<input type="hidden" name="id" value="${sessionScope.loginfo.id}">
					<!--게시물 번호-->
					<input type="hidden" name="voteno" value="${requestScope.title.voteno}">
					<!-- 투표 여부 변수 -->
					<c:set var="voteVal" value="${requestScope.voteVal}"/>
					<input type="hidden" name="voteVal" value="${voteVal}">
					<!-- 처음 투표를 했을 경우. -->
					<c:if test="${voteVal eq '0'}">
						<!-- 투표 리스트 반복-->
						<c:forEach var="votelist" items="${requestScope.votelist}">
							<div class="menu-box">
								<label class="input-group-text">${votelist}
									<!-- //TODO: 투표 안하고 투표 눌렀을시 삭제. -->
									<input type="radio" name="choice" id="before-vote" value="${votelist}" onclick="show_image()" hidden="hidden">
								</label>
								<span class="inputVline"></span>
								<img class="choice-img" src="${pageContext.request.contextPath}/vote/voteCSS/vote.png">
							</div>
						</c:forEach>
						<!-- 투표 리스트 반복 끝-->
					</c:if>
					
					<!-- 전에 투표를 등록했을 경우. -->
					<c:if test="${voteVal eq '1'}">
					<!-- 전에 투표한 항목 -->
					<input type="hidden" id="selectVote" value="${requestScope.selectVote}">
					<!-- 투표 리스트 반복-->
						<c:forEach var="votelist" items="${requestScope.votelist}">
								<div class="menu-box">
									<label class="input-group-text">${votelist}
										<input type="radio" id="after-vote" name="choice" value="${votelist}" onclick="show_image()" hidden="hidden">
									</label>
									<img class="choice-img" src="${pageContext.request.contextPath}/vote/voteCSS/vote.png">
								</div>
							</c:forEach>
						<!-- 투표 리스트 반복 끝-->
					</c:if>
				</form>
			</div>
		</div>
		
		<div id="buttons" align="center">
			<button type="submit" class="btn button-wrapper order_bigbtn" onclick="alertInfo()">투표하기</button>
			<button type="submit" id="endVote" class="btn button-wrapper" onclick="alertInfo()">투표마감</button>
			<a type="button" href="vote/voteList.jsp" class="btn button-wrapper else_bigbtn">취소</a>
		</div>
	</div>
</body>
</html>