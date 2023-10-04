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
		var i = 0;
		function move() {
			if (i == 0) {
				i = 1;
				var elem = document.getElementById("percentage_each");
				var width = 10;
				var id = setInterval(frame, 10);
				function frame() {
					if (width >= 100) {
						clearInterval(id);
						i = 0;
					} else {
						width++;
						elem.style.width = width + "%";
						elem.innerHTML = width + "%";
					}
				}
			}
		}
		
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

.votecol {
	padding: 16px;
	margin: 5px;
	;
}

.votetitle {
	background-color: #dcdcdc;
	font-weight: 600;
	align-items: center;
	border-radius: 25px;
	padding: 16px;
	margin: 5px;
}

.btn {
	padding: 16px;
	min-width: 220px;
	margin: 30px;
	align-content: center;
}

/* 퍼센테이지 뒷 배경*/
#percentage_back {
	width: 100%;
	background-color: #f8f9fa;
	max-height: 20px;
	min-height: 30px;
}

/* 각 항목들 퍼센테이지 */
#percentage_each {
	align-self : flex-end;
	height: 30px;
		background-color: #ff9e9e;
	text-align: justify;
	line-height: 30px;
	color: black;
	}

	
</style>
</head>

<body>
	<form>
		<div class="container row">
			<br />
			<h2 class="title" align="center">투표 결과</h2>
			
			<span class="input-group-text votetitle">${requestScope.bean.votetitle}</span><!-- votetitle -->
			<br />
			<c:forEach var="voteResult" items="${requestScope.voteResult}">
				<span class="input-group-text votecol">
					<div id="percentage_back">
						<div id="percentage_each"
						style="width: ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%;"> <!-- list로 반복되는 값에 따라 그래프 넓이가 변해서 style로 지정  -->
							<dt>${voteResult.selectvotecol} / ${voteResult.cnt}명 ${Math.round((voteResult.cnt/requestScope.voteTotal.total)*100)}%</dt> 
						</div>
					</div>
				</span>
			</c:forEach>

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