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
const MAX_ITEM = 5; /* 최대 입력 항목 개수 */
var nowItem = 2; /* 현재 입력 항목 개수 (카운팅) */

	$(document).ready(function(){
		/* 처음 화면 진입 시 입력 항목은 2개 노출 */
		$('.inputArea').append('<div class="voteInputArea" id="voteInputItem">' + 
			'<input type="text" class="voteInputItem" placeholder="항목 입력">' + 
			'<a class="itembtn deleteItemBtn cursor" onclick="delInput();">X</a> </div>'
		);
	});
	
	/* 항목 추가 */
	function addInput() {
		if (nowItem >= 5) {
			alert('항목 추가는 최대 5개까지 가능합니다.');
			return;
			
		} else {
			$('.inputArea').append('<div class="voteInputArea" id="voteInputItem">' + 
					'<input type="text" class="voteInputItem" placeholder="항목 입력">' + 
					'<a class="itembtn deleteItemBtn cursor" onclick="delInput();">X</a> </div>'
				);
			nowItem += 1;
		}
	}
	
	/* 항목 삭제 */
	function delInput(areaNum) {
		
		
		
	}
	
	/* 투표 항목 전송 */
	function submitAll() {
		
		
		
	}
	
</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/insertVote.css" type="text/css">
</head>

<body>
<!-- 	<div class="container row"> -->
	<div class="container">
		<div class="voteBG">
			<h2 class="boxTitle">투표 등록</h2>
			
			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="voteInsert">
				<div class="flexArea"> <!-- display : flex -->
					<input type="text" name="votetitle" class="votetitle" placeholder="제목 입력">
					
					<div class="inputArea">
						<!-- 투표 항목 입력 칸 (최대 5개) -->
						<div id="chk"></div>
						<div class="voteInputArea" id="voteInputItem">
							<input type="text" class="voteInputItem" name="votecol" placeholder="항목 입력">
							<a class="itembtn deleteItemBtn cursor" onclick="delInput(this);">X</a>
							<!-- https://coderap.tistory.com/448 -->
						</div>
					</div>
	
					<div> <!-- plusInputArea -->
						<!-- 항목 추가 칸 -->
						<div class="voteInputArea cursor" OnClick="addInput();">
							<span class="voteInputItem addItem">항목 추가</span>
							<span class="itembtn addItemBtn">+</span>
						</div>
					</div>
				</div>
				
				<div class="btnArea" align="center">
					<button type="submit" class="btn order_bigbtn" onclick="submitAll();">등록</button>
					<button type="button" class="btn else_bigbtn voteCancle">취소</button><!-- onClick="history.go(-1)" -->
				</div>
			</form>
		</div>
	</div>
</body>
</html>