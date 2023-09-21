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
<body background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<br/>
	<h2><br/>로그인 페이지</h2>
	<div class="container row" >
		<div class="col-lg-3">[포인터].col-lg-3</div>
		<div class="col-lg-6" align="center">[포인터].col-lg-6<div>
			<p>로그인을 위한 페이지입니다.</p>
			<p>혹은 로고?</p>
			</div>

			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="meLogin">

				<div class="input-group" align="center">
					<input type="text" class="form-control col-md-4" id="id" name="id" placeholder="아이디"
						data-bs-toggle="tooltip" data-bs-placement="right" title="아이디를 입력해 주세요">
				</div>

				<div class="input-group" align="center">
					<input class="form-control col-md-4" type="password" id="password" name="password" placeholder="비밀번호"
					data-bs-toggle="tooltip" data-bs-placement="right" title="비밀번호는 영문, 숫자, 특문 포함입니다." >
				</div>
				<br/>
				<!-- contextual class : btn-primary, btn-info, btn-danger -->
				<div align="center">
					<button type="submit" class="btn button-wrapper button-18" >로그인</button><br/><br/>
					<a type="button" href="<%=notWithFormTag%>meInsert" class="btn button-99">회원 가입</a>
					|
					<a type="button" href="<%=notWithFormTag%>meFindId" class="btn button-99">아이디 찾기</a>
					|
					<a type="button" href="<%=notWithFormTag%>meFindPassword" class="btn button-99">비밀번호 찾기</a>
					||
					<a type="popup" href="javascript:popup()" class="btn button-99">아이디 찾기(popup test)</a>
					|
				</div>
			</form>

			</div>
			<div class="col-lg-3">[포인터].col-lg-3</div>
	</div>
</body>
</html>