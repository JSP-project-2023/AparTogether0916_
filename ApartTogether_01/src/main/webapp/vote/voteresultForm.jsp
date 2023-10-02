<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파투게더:투표등록</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>

<style type="text/css">
.container {
	padding-left: 200px;
	padding-right: 200px;
	padding-top: 20px;
	max-width: 1060px;
	max-height: 720px;
	margin: 0 auto;
	margin-top: 118px;
	background: #f1f1f1f1;
	border-radius: 10px;
	box-shadow: 2px 3px grey;
}

.title {
	padding: 20px;
	font-size: 3rem;
	font-weight: 600;
}

.input-group-text {
	padding: 16px;
	margin: 5px;
}

.votetitle {
	background-color: #dcdcdc;
	font-weight: 600;
	align-items: center;
	border-radius: 25px;
}

.btn {
	padding: 16px;
	min-width: 220px;
	margin: 30px;
	align-content: center;
}
</style>
</head>

<body>
	<form>
		<div class="container row">
			<br />
			<h2 class="title" align="center">투표 결과</h2>

			<span class="input-group-text votetitle">${requestScope.bean.votetitle}</span>
			<!-- votetitle -->
			<br />
			<div class="progress" style="height:20px">
  				<div class="progress-bar" style="width:40%;"></div>
			</div>

			<!-- 항목1 -->
			<div class="progress" style="height: 20px">
				<div class="progress-bar" style="width: 40%;">${requestScope.bean.votecol1}</div>
			</div>
			<!-- or -->
			<span class="input-group-text votecol1">${requestScope.bean.votecol1}</span>
			<!-- 항목2 -->
			<c:if test="${requestScope.bean.votecol2 ne null}">
				<span class="input-group-text votecol2">${requestScope.bean.votecol2}</span>
			</c:if>
			<!-- 항목3 -->
			<c:if test="${requestScope.bean.votecol3 ne null}">
				<span class="input-group-text votecol3">${requestScope.bean.votecol3}</span>
			</c:if>
			<!-- 항목4 -->
			<c:if test="${requestScope.bean.votecol4 ne null}">
				<span class="input-group-text votecol4">${requestScope.bean.votecol4}</span>
			</c:if>
			<!-- 항목5 -->
			<c:if test="${requestScope.bean.votecol5 ne null}">
				<span class="input-group-text votecol5">${requestScope.bean.votecol5}</span>
			</c:if>

			<div align="center">
				<button type="" class="btn button-wrapper order_bigbtn" hidden>버튼1</button>
				<button type="" class="btn button-wrapper else_bigbtn" hidden>버튼2</button>
			</div>
			<br /> <br />
		</div>
	</form>
</body>
<script type="text/javascript">
	
</script>
</html>