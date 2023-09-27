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

	$(document).ready(function(){
  	});

</script>

</head>

<body>
	<div class="container row">
		<br/>
		<h2 class="title" align="center">투표 등록</h2>
		
		<input type="text" class="input-group-text votetitle" placeholder="제목 입력"> <!-- votetitle -->
		
		<div class="ItemArea">
			<!-- 투표 항목 (최대 5개) -->
			<div class="voteinput">
				<input type="text"  class="voteItem">
				<div class="votebtn">
					<a>X</a><br>
				</div>
			</div>
		</div>
		<div class="AddItem">
			<!-- 투표 항목 (최대 5개) -->
			<input type="text"  class="voteItem"><a>+</a><br>
		</div>
		
		<div class="btnArea" align="center">
			<button type="submit" class="btn button-wrapper order_bigbtn" >등록</button>
			<button type="button" class="btn button-wrapper else_bigbtn" >취소</button>
		</div>
		<br/><br/>
	</div>
</body>
</html>