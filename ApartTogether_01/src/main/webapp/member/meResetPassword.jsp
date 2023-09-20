<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/common.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:비밀번호 재설정</title>
	<script type="text/javascript">
		$(document).ready(function() {	
			var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				return new bootstrap.Tooltip(tooltipTriggerEl)
			});		
		});
		function validCheck(){ /* form validation check */
			/* newPassword와 newPasswordCheck가 서로 일치하는지 체크합니다. */
			var newPassword = $('#newPassword').val();
			var newPasswordCheck = $('#newPasswordCheck').val();
			if(newPassword != newPasswordCheck){
				swal('새 비밀번호를 똑같이 두번 입력해주세요.');
  				$('#newPassword').focus();
  				return false ;
			}
			
  			
		}
	</script>
</head>
<body>
	<br/>
	<div class="container">
		<h2>비밀번호 재설정 페이지</h2>
		<p>비밀번호 재설정을 위한 페이지입니다.</p>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meDetail"> 
			
			<div>
				<label for="password" class="form-label">기존 비밀번호 :</label> 
				<input class="form-control" type="password" id="oldPassword" name="oldPassword">
			</div>
			<div>
				<label for="password" class="form-label"> 새 비밀번호 :</label> 
				<input class="form-control" type="password" id="newPassword" name="newPassword">
			</div>
			<div>
				<label for="password" class="form-label">새 비밀번호 재입력:</label> 
				<input class="form-control" type="password" id="newPasswordCheck" name="newPasswordCheck">
			</div>
			
			
			<div id="backButton">
				<button type="button" class="btn btn-primary" onclick="history.back();">뒤로가기</button>
				<button type="submit" class="btn btn-danger" onclick="return validCheck()">비밀번호 변경하기</button> 
			</div>
			
			<!-- contextual class : btn-primary, btn-info, btn-danger -->
							
		</form>
	</div>
</body>
</html>