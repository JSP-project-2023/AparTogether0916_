<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더 : ID 찾기</title>
	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#birth').datepicker(); */
  	  		$('#birth').datepicker({dateFormat: "yy/mm/dd"}); 
  		});
  			
  	</script>
  	<style type="text/css">
  		/* box model에 대한 공부가 필요합니다. */
  		.container{margin-top: ;}
  		.input-group{margin: 7px;}
  		.input-group-text{
  			display: block;
  			margin-left: auto;
  			margin-right: auto;
  		}
  		#buttonset{margin-top: 15px;}
  		.radio_membertype{font-size: 0.8rem;} /* 주위 글꼴의 1.1배 */
  	</style>

</head>
<body>
	<div class="container">
		<h2><b>아이디 찾기</b></h2>
		<p>회원 가입하는 페이지 입니다.</p>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meFindId">
			
			<div class="input-group">
				<span class="input-group-text col-md-2">이름</span>
				<input class="form-control" type="text" id="name" name="name" placeholder="이름을 입력해 주세요."
					data-bs-toggle="tooltip" title="등록하신 이름을 입력하세요"
					data-bs-placement="top">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">전화번호</span>
				<input class="form-control" type="text" id="phone" name="phone" placeholder="000-0000-0000">				
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-2">생일</span>
				<input class="form-control" type="datetime" id="birth" name="birth" placeholder="YYYY/MM/DD">				
			</div>
			<button type="submit" class="btn btn-primary col-xs-6 col-sm-4 col-lg-2"> ID 확인 </button> 
<!-- 
			인증번호 및 물음 미구현
			[st] 인증번호 전송
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
				인증 번호 전송
			</button>

			The Modal
			<div class="modal" id="myModal">
  				<div class="modal-dialog">
    				<div class="modal-content">

      					Modal Header
      					<div class="modal-header">
      					
        					<h3 class="modal-title">인증 번호 전송 완료!</h3>
        						<button type="button" class="btn-close" data-bs-dismiss="modal" onclick="return validCheck();"></button>
      						</div>
						
						
      					Modal body
      					<div class="modal-body">
							<div class="input-group">
								<span class="input-group-text col-md-4"> 인증번호 </span>
									<input class="form-control" type="text" id="name" name="name">				
							</div>

      					</div>

      					Modal footer
      					<div class="modal-footer">
					        <button type="submit" class="btn btn-danger" data-bs-dismiss="modal">확인</button>
      					</div>

				    </div>
  				</div>
			</div>
			[ed] 인증번호 전송
-->
		</form>
	</div>
</body>
</html>