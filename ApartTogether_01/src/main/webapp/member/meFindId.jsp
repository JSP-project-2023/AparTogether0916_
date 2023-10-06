<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더 : ID 찾기</title>
	<script type="text/javascript">
		$(document).ready(function() {
			/* $('#birth').datepicker(); */
			$('#birth').datepicker({
				dateFormat : "yy/mm/dd"
			});
		});
	</script>
	<style type="text/css">
	.navbar_member {
        display: none;
    }
	.navbar_menu {
        display: none;
    }
    .hamarea {
        display: none;
    }
	.container {
		max-width: 500px;
	}
	
	.input-group {
		margin-top: 0.5rem; 
		margin-bottom: 0.5rem;
	}
	
	.input-group-text {
		display: block;
		margin-left: auto;
		margin-right: auto;
	}
	
	#buttonset {
		margin-top: 15px;
	}
	
	.radio_membertype {
		font-size: 0.8rem;
	} /* 주위 글꼴의 1.1배 */
	.flex-container {
		display: flex;
	}
	
	.flex-container>div {
		background-color: #f1f1f1;
		margin: 10px;
		padding: 20px;
		font-size: 30px;
	}
	
	#collapse {
		visibility: hidden;
	}
	
	</style>

</head>
<body
	background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	
	<div class="container" >
	
		<h2 class="mainTitle">아이디 찾기</h2>
		
		<form action="<%=withFormTag%>" method="post" >
			<input type="hidden" name="command" value="meFindId">
			
			<div class="input-group">
				<span class="input-group-text col-md-3">이름</span> <input
					class="form-control" type="text" id="name" name="name"
					placeholder="이름을 입력해 주세요." data-bs-toggle="tooltip"
					title="등록하신 이름을 입력하세요" data-bs-placement="top">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">전화번호</span> <input
					class="form-control" type="text" id="phone" name="phone"
					placeholder="000-0000-0000">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">생일</span> <input
					class="form-control" type="datetime" id="birth" name="birth"
					placeholder="YYYY/MM/DD">
			</div>
			<div align="center">
				<button type="submit"
					class="big_ctlbtn else_bigbtn" style="width:100%;" >ID 확인</button>
			</div>
		</form>
	</div>
</body>
</html>