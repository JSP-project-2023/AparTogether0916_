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
			$('#id').focus(); // 로그인페이지로 이동하자마자 id입력할 수 있게 추가했습니다. 9/27
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
		function popupfindID(){
            var url = "<%=notWithFormTag%>meFindId";
            var name = "popup test";
            var option = "width = 700, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }
		function popupfindPW(){
            var url = "<%=notWithFormTag%>meFindPassword";
            var name = "popup test";
            var option = "width = 700, height = 590, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }
		/* [ed] popup 창으로 열기 */
		
	</script>
	<style type="text/css">
	
	.container{margin-top:0px;}
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
  	.h2{
  		margin: auto;
  		padding: 3rem;
  	}
  	#buttonset{margin-top: 15px;}
  	/* [st] flexbox */
  	
  	/* [ed] flexbox */
  	
  	
  	/* [st] background */
  	/* [ed] background */
  	
  	/* [st] button-18 */
          .button-18 {
            align-items: center;
            background-color: #FFA559; /* 버튼배경 색상 설정 */
            border: 0;
            box-sizing: border-box;
           /*  color: #6f726e; */
            color: #252525; /* 텍스트 색상 설정 */
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
            border-radius: 25px; /* 모서리를 둥글게 만듦 */
          }

          .button-18:hover,
          .button-18:focus { 
            background-color: #FF6000;
            color: #252525;
            /* color: #ffffff; */
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
  	
  		.myInputText{
  		min-height: 40px;
  			border-radius: 20px; /* 모서리를 둥글게 만듦 */
  		}
		
  	
	</style>
</head>
<body background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	
	<div class="container" >
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6" align="center">
				
				<h2 class="mainTitle">로그인 페이지</h2>
				
	
				<form action="<%=withFormTag%>" method="post">
					<input type="hidden" name="command" value="meLogin" onsubmit="return validCheck();"> 
					
					<div class="input-group" align="center">
						<input type="text" class="form-control col-md-4 myInputText" id="id" name="id" placeholder="아이디" 
							data-bs-toggle="tooltip" data-bs-placement="right" title="아이디를 입력해 주세요">
					</div>
					
					<div class="input-group" align="center">
						<input class="form-control col-md-4 myInputText" type="password" id="password" name="password" placeholder="비밀번호"
						data-bs-toggle="tooltip" data-bs-placement="right" title="비밀번호는 영문, 숫자, 특문 포함입니다." >
					</div>
					
					<!-- <input type="hidden" id="hashedPassword" name="hashedPassword" value="..."> -->
					<br/>
					
					<div align="center">
						<button type="submit" class="btn button-wrapper button-18" onclick="return cryptPassword();">로그인</button><br/><br/>
						<!--
						<button type="" class="btn button-wrapper button-18" onclick="kakaoLogin();" >카카오 로그인</button><br/>
						<ul>
							<li onclick="kakaoLogin();">
	      						<a href="javascript:void(0)">
	          						<span>카카오 로그인</span>
	      						</a>
							</li>
							<li onclick="kakaoLogout();">
	      						<a href="javascript:void(0)">
	          						<span>카카오 로그아웃</span>
	      						</a>
							</li>
						</ul>
						-->
						<a type="button" href="<%=notWithFormTag%>meInsert" class="btn button-99">회원 가입</a>
						|
						<a type="popup" href="javascript:popupfindID()" class="btn button-99">아이디 찾기</a>
						|	
						<a type="popup" href="javascript:popupfindPW()" class="btn button-99">비밀번호 찾기</a>
					</div>
				</form>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
</body>

<!--  
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('ebeba8b3574c87b76521c3760bbf5ee6'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }
/* 만약 계정 연동해제를 하고 싶으면 아래 주소에 가서 연동해제하면 된다. */
/* accounts.kakao.com/weblogin/account/info */
</script>
</html>