<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파투게더:투표결과</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- CSS파일 import -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/voteResult.css" type="text/css">

</head>

<body>
	<form>
<!-- 		<div class="container row"> -->
		<div class="voteBG">
			<br />
			<h2 class="title" align="center">투표 결과</h2>
			<div class=votetitle>
				<span>${requestScope.bean.votetitle}</span><!-- votetitle -->
			</div>
			<br />
			<c:forEach var="voteResult" items="${requestScope.voteResult}">
				<span class="input-group-text votecol">
					<div id="percentage_back">
						<div id="percentage_each"
						style="width: ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%;"> <!-- list로 반복되는 값에 따라 그래프 넓이가 변해서 style로 지정  -->
<!-- <<<<<<< HEAD -->
<%-- 							${voteResult.selectvotecol}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}% (${voteResult.cnt}명)  --%>
<!-- ======= -->
							<dt>${voteResult.selectvotecol} / ${voteResult.cnt}명 ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%</dt>
<!-- >>>>>>> origin/sup_new_branch02 -->
						</div>
					</div>
				</span>
			</c:forEach>
			
			<div class="btnArea">
				<button type="button" onclick="history.go(-1)" class="big_ctlbtn else_bigbtn">뒤로가기</button>
			</div>
		</div>
	</form>
</body>
</html>