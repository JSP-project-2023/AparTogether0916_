<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	button {
		margin: 10px;
	}
	
	body {
		background-color: #fdfcfa;
	}
	
	
</style>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="title-area">
			
		</div>
		
		<div class="cardArea">
			<div class="card-content">
				<div class="inputArea">
					<label for="url" class="input-field-label"><span class="mustInput">*</span>가게명</label>
					<input class="input_text" type="text" placeholder="입력하세요">
				</div>
				
				<div class="inputArea">
					<label for="url" class="input-field-label"><span class="mustInput">*</span>가게명</label>
					<input class="input_text" type="text" placeholder="입력하세요">
				</div>
			</div>
		</div>
		
		<div style="margin-top:20px">
			<button class="big_ctlbtn insert_bigbtn">등록</button>
			<button class="big_ctlbtn update_bigbtn">수정</button>
			<button class="big_ctlbtn delete_bigbtn">삭제</button>
			<button class="big_ctlbtn cancle_bigbtn">취소</button>
			<button class="big_ctlbtn reset_bigbtn">초기화</button>
			
			<br>
			
			<button class="big_ctlbtn normal_bigbtn">로그인</button>
			<button class="big_ctlbtn normal_bigbtn">회원가입</button>
			<button class="big_ctlbtn else_bigbtn">기타버튼</button>
			<button class="big_ctlbtn select_bigbtn">선택버튼</button>
		</div>
		
		<div style="margin-top:20px">
			<button class="sm_ctlbtn insert_smbtn">등록</button>
			<button class="sm_ctlbtn update_smbtn">수정</button>
			<button class="sm_ctlbtn delete_smbtn">삭제</button>
			<button class="sm_ctlbtn cancle_smbtn">취소</button>
			<button class="sm_ctlbtn reset_smbtn">기타</button>
<!-- 			<button class="sm_ctlbtn reset_bigbtn">선택</button> -->
		</div>
	</div>
</body>
</html>