<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:회원가입</title>
	<script type="text/javascript">
  		$(document).ready(function(){
  	  		/* $('#birth').datepicker(); */
  	  		$('#birth').datepicker({dateFormat: "yy/mm/dd"});   	  	 	
  		});
  		
  		function validCheck(){/* form validation check */
  			var id = $('#id').val();
  			if(id.length < 4 || id.length > 10){
  				swal('아이디는 4자리 이상 10자리 이하로 입력해 주세요.');
  				$('#id').focus();
  				return false ; /* false이면 이벤트 전파 방지 */
  			}
  			
  			var name = $('#name').val();  			
  			if(name.length < 3 || name.length > 15){  				
  				$('#name').focus();
  				swal('이름은 3자리 이상 15자리 이하로 입력해 주세요.');
  				return false ;
  			}
  			
  			var password = $('#password').val();  			
  			if(password.length < 5 || password.length > 12){
  				swal('비밀 번호는 5자리 이상 12자리 이하로 입력해 주세요.');
  				$('#password').focus();
  				return false ;
  			}    
  			
  			var regex = /^[a-z]\S{4,11}$/; /* 정규 표현식 */
  			var result = regex.test(password) ;

  			if(result == false){
  				swal('비밀 번호의 첫글자는 반드시 소문자이어야 합니다.');  				
  				return false ;
  			}
  			
  			if(password.indexOf('@') <= 0 && password.indexOf('#') <= 0 && password.indexOf('$') <= 0){
  				swal('특수 문자 @#% 중에 최소 1개가 포함이 되어야 합니다.');  				
  				return false ;
  			}
  			
			var radioList = $('input[type="radio"]:checked') ;
  			if(radioList.length == 0){
  				swal('성별은 반드시 선택이 되어야 합니다.');
  				return false ; 
  			}

  			var phone = $('#phone').val();  			
  			if(phone.length < 8 || name.length > 12){  				
  				$('#name').focus();
  				swal('이름은 9자리 이상 11자리 이하로 입력해 주세요.');
  				return false ;
  			}
  			
  			/* jqueryUI 플러그인 date picker */
  			var birth = $('#birth').val();
  			var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
  			var result = regex.test(birth);
  			
  			if(result == false){
  				swal('생일은 반드시 yyyy/mm/dd 형식으로 입력해 주세요.');  				
  				return false ;
  			}   			
  		}
  	</script>
  	<style type="text/css">
  		/* box model에 대한 공부가 필요합니다. */
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
  		.radio-inline{
  			cursor: pointer;
  			justify-content: center;
  			margin-left: auto;
  			margin-right: auto;
  		}
  		
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
            max-width: 1100px;
            min-height: 50px;
            min-width: 0px;
            overflow: hidden;
            padding: 0px;
            padding-left: 200px;
            padding-right: 200px;
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
		/* [ed] button-18 */
        
          .button-99 {/* 무색 투명 버튼 */
         	cursor: pointer;
         	font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 12px;
            font-weight: 600;
          }
  	
  	
  	
  	</style>
</head>
<body>
	<div class="container row">
		<div class="col-lg-2">col-lg-2</div>
		<div class="col-lg-8">
			<h2>회원 가입</h2>
			<p>회원 가입하는 페이지 입니다.</p>
			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="meInsert">
				
				<div class="input-group">
					<span class="input-group-text col-md-3">회원유형</span>
					
					<div class="form-control">
						<label class="radio-inline radio_membertype"> 
							&nbsp;<input type="radio" id="membertype" name="mtype" value="user"> 🙋‍♀️ ‍일반회원
						</label>
						 |
						<label class="radio-inline radio_membertype"> 
							&nbsp;<input type="radio" id="membertype" name="mtype" value="biz"> 👨‍💼 사업자
						</label>
					</div>
					
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">아이디</span>
					<input class="form-control" type="text" id="id" name="id" placeholder="아이디">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">비밀번호</span>
					<input class="form-control" type="password" id="password" name="password" placeholder="비밀번호">				
				</div>
				
				<div class="input-group">
					<span class="input-group-text col-md-3">이름</span>
					<input class="form-control" type="text" id="name" name="name" placeholder="이름">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">닉네임</span>
					<input class="form-control" type="text" id="nickname" name="nickname" placeholder="닉네임">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">성별</span>
					<div class="form-control">
						<label class="radio-inline radio_gender"> 
							&nbsp;<input type="radio" id="gender1" name="gender" value="male"> 👦 남자
						</label>
						 |
						<label class="radio-inline radio_gender"> 
							&nbsp;<input type="radio" id="gender2" name="gender" value="female"> 👧 여자
						</label>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">전화번호</span>
					<input class="form-control" type="text" id="phone" name="phone" placeholder="000-000-0000">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">생일</span>
					<input class="form-control" type="datetime" id="birth" name="birth" placeholder="YYYY/MM/DD">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">주소</span>
					<input class="form-control" type="text" id="address" name="address" placeholder="주소">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">프로필사진</span>
					<input class="form-control" type="file" id="profile" name="profile" placeholder=".jpg, .png 파일을 올려주세요">
				</div>	
				<div id="buttonset" class="input-group">
					<button type="submit" class="btn button-18" onclick="return validCheck();"> 
						등록
					</button>
				</div>
			</form>
		</div><!-- col-lg-8 -->
		<div class="col-lg-2">col-lg-2</div>
	</div>
</body>
 <!-- [st] 다음(카카오) 주소 검색 스크립트 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){
    document.getElementById("address").addEventListener("click", function(){ /* id 가 "address_kakao" 주소입력칸을 클릭하면 */
        /* 카카오 지도 발생 */
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; /* 불러온 주소 넣기 */
                document.querySelector("input[name=address]").focus(); /* 주소(name=address)로 포커싱 | 추후 다른걸로도 가능 */
            }
        }).open();
    });
}
</script>
<!-- [ed] 다음(카카오) 주소 검색 스크립트 -->
</html>