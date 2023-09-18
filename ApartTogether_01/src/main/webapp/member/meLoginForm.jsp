<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	<style type="text/css">
	.container{margin-top: ;}
	.input-group{margin: 7px;}
  	.input-group-text{
  		display: block;
  		margin-left: auto;
  		margin-right: auto;
  	}
  	#buttonset{margin-top: 15px;}
  	/* [st] flexbox */
  	.flex-container {
 		display: flex;
	}
  	
  	.flex-container > div {
 		background-color: #f1f1f1;
  		margin: 10px;
  		padding: 20px;
	 	font-size: 30px;
	}
  	/* [ed] flexbox */
  	
  	
  	/* [st] */
         
  	/* [ed] */
  	
  	
	</style>
</head>
<body>
	<br/>
	<h2><br/>로그인 페이지</h2>
	<div class="flex-container">
		<div>
		<p>%nbsp;로그인을 위한 페이지입니다.</p>
		<p>혹은 로고?</p>
		</div>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meLogin"> 
			
			<div class="input-group">
				<span class="input-group-text col-md-4">아이디 🔒</span>
				<input type="text" class="form-control" id="id" name="id" 
					placeholder="아이디를 입력해 주세요."
					data-bs-toggle="tooltip" title="아이디는 3글자 이상 10글자 이하이어야 합니다."
					data-bs-placement="top">
			</div>
			
			<div class="input-group">
				<span class="input-group-text col-md-4">비밀번호 🔑</span>
				<input class="form-control" type="password" id="password" name="password">
			</div>
			
			<!-- contextual class : btn-primary, btn-info, btn-danger -->
			<button type="submit" class="btn button-wrapper button-18">로그인</button> 
			<a type="button" href="<%=notWithFormTag%>meInsert" class="btn button-18">회원 가입</a>	
			<a type="button" href="<%=notWithFormTag%>meFindId" class="btn button-18">아이디 찾기</a>	
			<a type="button" href="<%=notWithFormTag%>meFindPassword" class="btn button-18">비밀번호 찾기</a>						
		</form>
	</div>
</body>
</html>