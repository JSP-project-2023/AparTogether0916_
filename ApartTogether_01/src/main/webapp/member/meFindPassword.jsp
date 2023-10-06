<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더 : PW 찾기</title>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	}
	
	</style>
</head>
<body
	background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<div class="container">
		<h2 class="mainTitle">
			패스워드 찾기
		</h2>
		<p>하기 정보를 입력해주세요.</p>

		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meFindPassword">
			
				<div class="input-group">
					<span class="input-group-text col-md-3">이름</span> <input
						class="form-control" type="text" id="name" name="name"
						placeholder="이름을 입력해 주세요." data-bs-toggle="tooltip"
						title="등록하신 이름을 입력하세요" data-bs-placement="top">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">ID</span> <input
						class="form-control" type="text" id="id" name="id"
						placeholder="아이디를 입력해주세요">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">패스워드 질문</span> <select
						class="form-select" name="passwordquest" class="passwordquest">
						<option>-- 선택해 주세요.
						<option value="초등학교 이름은">초등학교 이름은?</option>
						<option value="아버지 성함은">아버지 성함은?</option>
						<option value="내가 좋아하는 동물은">내가 좋아하는 동물은?</option>
						<option value="내 애완동물의 이름은">내 애완동물의 이름은?</option>
					</select>
				</div>
				<div class="input-group">
					<input class="form-control" type="text" id="passwordanswer"
						name="passwordanswer" placeholder="답변">
				</div>
				
				<div align="center">
				<button type="submit"
					class="big_ctlbtn else_bigbtn" style="width: 100%;" >Password 확인</button>
			</div>
		</form>
	</div>
</body>
</html>