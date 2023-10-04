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
		/* 항목 삭제 */
		$(document).on("click", "input[name='remove']", function() {
			if (nowItem <= 1) {
				alert('최소 1개 이상의 항목을 입력해주세요');
				return;
				
			} else {
				nowItem = nowItem-1;
				$(this).parent().remove(); /* 상위 div까지 다 삭제 */
			}
		});
		
		/* 항목 추가 */
		$(document).on("click", "input[name='addItem']", function() {
			if (nowItem >= 5) {
				alert('항목 추가는 최대 5개까지 가능합니다.');
				return;
				
			} else {
				$('.inputArea').append('<div class="voteInputArea">' + 
						'<input type="text" class="voteInputItem" placeholder="항목 입력" name="votecol">' + 
						'<input type="button" name="remove" class="itembtn deleteItemBtn cursor" value="X"> </div>'
					);
				nowItem += 1;
			}
		});
	});
</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/insertVote.css" type="text/css">
</head>

<body>
	<div class="container">
		<div class="voteBG">
			<h2 class="boxTitle">투표 등록</h2>
			
			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="voteInsert">
				<input type="hidden" name="id" value="${sessionScope.loginfo.id}">
				
				<div class="flexArea"> <!-- display : flex -->
					<input type="text" name="votetitle" class="votetitle" placeholder="제목 입력">
					
					<div class="inputArea">
						<!-- 투표 항목 입력 칸 (최대 5개) -->
						<!-- 화면 진입 시 기본 2개 항목 노출 -->
						<div class="voteInputArea">
							<!-- request에 votecol 배열로 값 전달 -->
							<input type="text" class="voteInputItem" placeholder="항목 입력" name="votecol">
							<input type="button" name="remove" class="itembtn deleteItemBtn" value="X">
						</div>
						
						<div class="voteInputArea">
							<input type="text" class="voteInputItem" placeholder="항목 입력" name="votecol">
							<input type="button" name="remove" class="itembtn deleteItemBtn" value="X">
						</div>
					</div>
	
					<div>
						<!-- 항목 추가 칸 -->
						<div class="voteInputArea">
							<input type="button" name="addItem" class="voteInputItem addItem" value="항목 추가">
							<input type="button" name="addItem" class="itembtn addItemBtn" value="+">
						</div>
					</div>
				</div>
				
				<div class="btnArea" align="center">
					<button type="submit" class="btn order_bigbtn">등록</button>
					<button type="button" class="btn else_bigbtn voteCancle" onClick="history.go(-1)">취소</button>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>