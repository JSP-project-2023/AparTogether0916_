<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./../common/bootstrap5.jsp" %>
<%@ include file="./../common/common.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:로그인</title>
	<script type="text/javascript">
		$(document).ready(function() {	
			var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
			var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
				return new bootstrap.Tooltip(tooltipTriggerEl)
			});		
		});
		function validCheck(){/* form validation check */
  			var id = $('#id').val();
  			if(id.length < 2 || id.length > 11){
  				swal('아이디는 3글자 이상 10글자 이하이어야 합니다.');
  				$('#id').focus();
  				return false ; /* false이면 이벤트 전파 방지 */
  			}
		}
	</script>
</head>
<body>
	<br/>
	<div class="container">
		<h2>로그인 페이지</h2>
		<p>로그인을 위한 페이지입니다.</p>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meLogin"> 
			<div> 
				<label for="id" class="form-label">아이디 :</label> 
				<input type="text" class="form-control" id="id" name="id" 
					placeholder="아이디를 입력해 주세요."
					data-bs-toggle="tooltip" title="아이디는 3글자 이상 10글자 이하이어야 합니다."
					data-bs-placement="top">
			</div>
			<div>
				<label for="password" class="form-label">비밀 번호 :</label> 
				<input class="form-control" type="password" id="password" name="password">
			</div>
			
			<!-- contextual class : btn-primary, btn-info, btn-danger -->
			<button type="submit" class="btn btn-primary">로그인</button> 
			<a type="button" href="meInsertForm.jsp" class="btn btn-info">회원 가입</a>	
			<a type="button" href="meFindId.jsp" class="btn btn-info">아이디 찾기</a>	
			<a type="button" href="meFindPassword.jsp" class="btn btn-info">비밀번호 찾기</a>						
		</form>
	</div>
</body>
</html>