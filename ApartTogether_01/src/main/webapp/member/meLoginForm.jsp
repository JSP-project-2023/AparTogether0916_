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
		/* [st] popup 창으로 열기 */
		function popup(){
            var url = "<%=notWithFormTag%>meFindId";
            var name = "popup test";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }
		/* [ed] popup 창으로 열기 */
		
	</script>
	<style type="text/css">
	.container{margin-top: ;}
	.input-group{
		margin: 7px;
		max-width: 450px;
        min-width: 0px;
    }
  	.input-group-text{
  		display: block;
  		margin-left: auto;
  		margin-right: auto;
  		
  	}
  	#buttonset{margin-top: 15px;}
  	/* [st] flexbox */
  	
  	/* [ed] flexbox */
  	
  	
  	/* [st] background */
  	/* [ed] background */
  	
  	/* [st] button-18 */
          .button-18 {
            align-items: center;
            background-color: #d8e4d2;
            border: 0;
            box-sizing: border-box;
            color: #6f726e;
            cursor: pointer;
            display: inline-flex;
            font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 20px;
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
          .button-99 {
         	cursor: pointer;
         	font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 12px;
            font-weight: 600;
          }

          
  	/* [ed] button-18 */
		
  	
	</style>
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