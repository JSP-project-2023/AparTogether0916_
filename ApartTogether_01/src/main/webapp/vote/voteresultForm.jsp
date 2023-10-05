<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파투게더:투표등록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<<<<<<< HEAD
=======

<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
>>>>>>> origin/sup_new_branch02
<!-- CSS파일 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/voteResult.css" type="text/css">

</head>

<body>
	<form>
		<div class="container row">
			<br />
			<h2 class="title" align="center">투표 결과</h2>
<<<<<<< HEAD
			<div class=votetitle>
				<span>${requestScope.bean.votetitle}</span><!-- votetitle -->
			</div>
=======
			
			<span class="input-group-text votetitle">${requestScope.bean.votetitle}</span><!-- votetitle -->
>>>>>>> origin/sup_new_branch02
			<br />
			<c:forEach var="voteResult" items="${requestScope.voteResult}">
				<span class="input-group-text votecol">
					<div id="percentage_back">
						<div id="percentage_each"
						style="width: ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%;"> <!-- list로 반복되는 값에 따라 그래프 넓이가 변해서 style로 지정  -->
<<<<<<< HEAD
							${voteResult.selectvotecol}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}% (${voteResult.cnt}명) 
=======
							<dt>${voteResult.selectvotecol} / ${voteResult.cnt}명 ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%</dt> 
>>>>>>> origin/sup_new_branch02
						</div>
					</div>
				</span>
			</c:forEach>
		</div>
	</form>
</body>
</html>