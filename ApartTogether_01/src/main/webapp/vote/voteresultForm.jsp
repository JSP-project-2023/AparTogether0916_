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

<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

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
	<div class="container row">
		<br/>
		<h2 class="title" align="center">투표 결과</h2>
		
		<span class="input-group-text votetitle">gi</span> <!-- votetitle -->
		<br/>
		<span class="input-group-text">gi</span> <!-- votetitle -->
		<span class="input-group-text">gi</span> <!-- votetitle -->
		<span class="input-group-text">gi</span> <!-- votetitle -->
		<span class="input-group-text">gi</span> <!-- votetitle -->
		<span class="input-group-text">gi</span> <!-- votetitle -->
		
		<div align="center">
			<button type="" class="btn button-wrapper order_bigbtn" >버튼1</button>
			<button type="" class="btn button-wrapper else_bigbtn" >버튼2</button>
		</div>
		<br/><br/>
	</div>
</body>
</html>