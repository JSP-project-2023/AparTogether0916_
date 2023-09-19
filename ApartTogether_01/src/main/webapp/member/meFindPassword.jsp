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
  		
  		.flex-container {
 			display: flex;
		}
  	
  		.flex-container > div {
 			background-color: #f1f1f1;
  			margin: 10px;
  			padding: 20px;
	 		font-size: 30px;
  		}
  		
  		          .button-18 {
            align-items: center;
            background-color: #d8e4d2;
            border: 0;
            box-sizing: border-box;
            color: #6f726e;
            cursor: pointer;
            display: inline-flex;
            font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 22px;
            font-weight: 600;
            justify-content: center;
            line-height: 20px;
            max-width: 900px;
            min-height: 50px;
            min-width: 0px;
            overflow: hidden;
            padding: 0px;
            padding-left: 190px;
            padding-right: 190px;
            text-align: center;
            touch-action: manipulation;
            transition: background-color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, box-shadow 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s;
            user-select: none;
            -webkit-user-select: none;
            vertical-align: middle;
          }

          .button-18:hover,
          .button-18:focus { 
            background-color: #8e998c;
            color: #ffffff;
          }

          .button-18:active {
            background: #09223b;
            color: rgb(255, 255, 255, .7);
          }

          .button-18:disabled { 
            cursor: not-allowed;
            background: rgba(0, 0, 0, .08);
            color: rgba(0, 0, 0, .3);
          }
  		
  	</style>

</head>
<body>
	<div class="container">
		<h2><b>패스워드 찾기</b></h2>
		<p>하기 정보를 입력해주세요.</p>
		
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meFindPassword">
			<div class="col-lg-3"></div>
			
			<div class="col-lg-6">
				<div class="input-group">
					<span class="input-group-text col-md-2">이름</span>
					<input class="form-control" type="text" id="name" name="name" placeholder="이름을 입력해 주세요."
						data-bs-toggle="tooltip" title="등록하신 이름을 입력하세요"
						data-bs-placement="top">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">ID</span>
					<input class="form-control" type="text" id="id" name="id" placeholder="아이디를 입력해주세요">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">생일(임시)</span>
					<input class="form-control" type="datetime" id="birth" name="birth" placeholder="YYYY/MM/DD">				
				</div>
				<button type="submit" class="btn button-18 col-xs-6 col-sm-4 col-lg-2"> Password 확인 </button>
			</div>
			<div class="col-lg-3"></div> 
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
					        <button type="submit" class="btn btn-danger " data-bs-dismiss="modal">확인</button>
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