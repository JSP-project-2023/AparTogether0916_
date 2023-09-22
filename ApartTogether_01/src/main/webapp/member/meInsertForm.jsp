<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파투게더:회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
  		/* $('#birth').datepicker(); */
  	  	$('#birth').datepicker({dateFormat: "yy/mm/dd"});
  	});
  	
  	/* [st] 비밀번호 표시 창 */
  	function viewPassword() {
		var x = document.getElementById("password");
		
		if (x.type === "password") {
	 		x.type = "text";
  		} else {
    		x.type = "password";
  		}
	 }
	/* [ed] 비밀번호 표시 창 */
  		
    /* [st] submit 유효성 검사 */
    function validCheck(){ /* form validation check */
		
    	var check_num = /[0-9]/;    // 숫자 
    	var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}/; // 한글체크 + 2~10자까지
    	var check_eng_num = /^[a-zA-Z0-9]{5,20}$/; //정규식(a~z, A~Z, 0~9 만 입력가능) 8~20자까지
    	var check_pw = /^[a-zA-Z0-9~!@#$%^&*()_]{8,20}$/; //정규식(a~z, A~Z, 0~9, 특문 만 입력가능)  8~20자까지
		
    	var id = $('#id').val();
  		var result = check_eng_num.test(id);
  		if(result == false){
  			alert('영문 및 숫자 만 입력 가능합니다.(5~20자)');  				
  			return false ; /* false이면 이벤트 전파 방지 */
  		}
		
  		var name = $('#name').val();
  		var result = check_kor.test(id);
  		if(result == false){  				
  			$('#name').focus();
  			alert('이름은 2자리 이상 10자리 이하로 한글로 입력해 주세요.');
  			return false ;
  		}
  			
  		var password = $('#password').val();
  		var result = check_pw.test(id)
  		if(result == false){
  			alert('비밀 번호는 8자리 이상 20자리 이하로 입력해 주세요.');
  			$('#password').focus();
  			return false ;
  		}    
  			
		/* jqueryUI 플러그인 date picker */
  		var birth = $('#birth').val();
  		var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
  		var result = regex.test(birth);
  		
  		if(result == false){
  			alert('생일은 반드시 yyyy/mm/dd 형식으로 입력해 주세요.');  				
  			return false ;
  		}
  		
  	}
	/* [ed] 유효성 검사 */
</script>

<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

<style type="text/css">
.container {
	margin-top:;
}

.input-group {
	margin: 7px;
	max-width: 450px;
	min-width: 0px;
}

.input-group-text {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

#buttonset {
	margin-top: 15px;
}

.radio-inline {
	cursor: pointer;
	justify-content: center;
	margin-left: auto;
	margin-right: auto;
	font-size: 1.2em;
}

.form-check-input {
	cursor: pointer;
	justify-content: center;
	margin-left: auto;
	margin-right: auto;
	size: 19px;
}

.form-select {
	cursor: pointer;
	justify-content: center;
	margin-left: auto;
	margin-right: auto;
	size: 3px;
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
	font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto,
		"Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans",
		Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji",
		"Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
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
	transition: background-color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s,
		box-shadow 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, color 0.167s
		cubic-bezier(0.4, 0, 0.2, 1) 0s;
	user-select: none;
	-webkit-user-select: none;
	vertical-align: middle;
}

.button-18:hover, .button-18:focus {
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
.button-99 { /* 무색 투명 버튼 */
	cursor: pointer;
	font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto,
		"Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans",
		Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji",
		"Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
	font-size: 12px;
	font-weight: 600;
}
</style>
</head>

<body
	background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<div class="container row">
		<div class="col-lg-2">col-lg-2</div>
		<div class="col-lg-8">
			<h2>회원 가입</h2>
			<p>회원 가입하는 페이지 입니다.</p>
			<form action="<%=withFormTag%>" method="post">
				<input type="hidden" name="command" value="meInsert">

				<div class="input-group" align="center">
					<span class="input-group-text col-md-3">회원유형 <font
						color="red">*</font></span>

					<div class="form-control">
						<label class="radio-inline radio_membertype"> &nbsp;<input
							type="radio" id="membertype" name="mtype" value="user">
							🙋‍♀️ ‍일반회원
						</label> | <label class="radio-inline radio_membertype"> &nbsp;<input
							type="radio" id="membertype" name="mtype" value="biz">
							👨‍💼 사업자
						</label>
					</div>

				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">아이디 <font
						color="red">*</font></span> <input class="form-control" type="text"
						id="id" name="id" placeholder="아이디">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">비밀번호 <font
						color="red">*</font></span> <input class="form-control" type="password"
						id="password" name="password" placeholder="비밀번호"> <span
						class="input-group-text col-md-3"> <label
						class="radio-inline"> <input class="form-check-input"
							type="checkbox" onclick="viewPassword()">🔒👀
					</label>
					</span>
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-4">패스워드 질문 <font
						color="red">*</font></span> <select class="form-select"
						name="passwordquest" class="passwordquest">
						<option>-- 선택해 주세요.
						<option value="초등학교 이름은">초등학교 이름은?
						<option value="아버지 성함은">아버지 성함은?
						<option value="내가 좋아하는 동물은">내가 좋아하는 동물은?
						<option value="내 애완동물의 이름은">내 애완동물의 이름은?
					</select>
				</div>
				<div class="input-group">
					<input class="form-control" type="text" id="passwordanswer"
						name="passwordanswer" placeholder="답변">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">이름 <font color="red">*</font></span>
					<input class="form-control" type="text" id="name" name="name"
						placeholder="이름">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">닉네임 <font
						color="red">*</font></span> <input class="form-control" type="text"
						id="nickname" name="nickname"
						placeholder="닉네임 / 공란 시 랜덤한 아이디가 생성!">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">성별 <font color="red">*</font></span>
					<div class="form-control" align="center">
						<label class="radio-inline radio_gender"> &nbsp;<input
							type="radio" id="gender1" name="gender" value="male"> 👦
							남자
						</label> | <label class="radio-inline radio_gender"> &nbsp;<input
							type="radio" id="gender2" name="gender" value="female">
							👧 여자
						</label>
					</div>
				</div>
				<div class="input-group row" align="center">
					<span class="input-group-text col-md-3">전화번호 <font
						color="red">*</font></span> <input class="form-control" type="text"
						id="phone" name="phone" placeholder="ex) 01045671234">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">생일 <font color="red">*</font></span>
					<input class="form-control" type="datetime" id="birth" name="birth"
						placeholder="YYYY/MM/DD">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">주소 <font color="red">*</font></span>
					<input class="form-control" type="text" id="address" name="address"
						placeholder="ex) 경기 성남시 분당구 판교로198번길">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">상세 주소 <font
						color="red">*</font></span> <input class="form-control" type="text"
						id="address_detail" name="address_detail"
						placeholder="상세 주소를 입력하세요">
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-3">프로필사진</span> <input
						class="form-control" type="file" id="profile" name="profile"
						placeholder=".jpg, .png 파일을 올려주세요">
				</div>
				<div id="buttonset" class="input-group">
					<button type="submit" class="btn button-18"
						onclick="return validCheck();">등록</button>
				</div>
			</form>
		</div>
		<!-- col-lg-8 -->
		<div class="col-lg-2">col-lg-2</div>
	</div>
</body>
<!-- [st] 다음(카카오) 주소 검색 스크립트 -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
window.onload = function(){ /* 창이 켜졌을때 로드되는 function들 */
	<!-- [st] 다음(카카오) 주소 검색 스크립트 -->
    document.getElementById("address").addEventListener("click", function(){ /* id 가 "address_kakao" 주소입력칸을 클릭하면 */
        /* 카카오 지도 발생 */
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address").value = data.address; /* 불러온 주소 넣기 */
                document.querySelector("input[name=address_detail]").focus(); /* 주소(name=address_detail)로 포커싱 */
            }
        }).open();
    });
    <!-- [ed] 다음(카카오) 주소 검색 스크립트 -->
    
    <!-- [st] 유효성검사 스크립트 해당 입력창 outfocusing 되었을 때 -->
    
    
    <!-- [ed] 유효성검사 스크립트 해당 입력창 outfocusing 되었을 때 -->
    
}

/* [st] focusout 유효성 검사 */
document.getElementById("id").addEventListener("focusout", function(){
	var id = $('#id').val();
	if(id.length < 2 || id.length > 18){
		alert('아이디는 2자리 이상 18자리 이하로 입력해 주세요.');
		event.target.removeEventListener(event.type, arguments.callee);
		$('#password').focus();
	}
});


document.getElementById("password").addEventListener("focusout", function(){
	var password = $('#password').val();  			
	if(password.length < 5 || password.length > 20){
		alert('비밀 번호는 5자리 이상 20자리 이하로 입력해 주세요.');
		event.target.removeEventListener(event.type, arguments.callee);
		$('#password').focus();
	}
	
	
});

document.getElementById("name").addEventListener("focusout", function(){
	var name = $('#name').val();  			
	if(name.length < 2 || name.length > 15){  				
		alert('이름은 2자리 이상 15자리 이하로 입력해 주세요.');
	}
	event.target.removeEventListener(event.type, arguments.callee);
});

document.getElementById("phone").addEventListener("focusout", function(){
	var phone = $('#phone').val();  			
	if(phone.length < 7 || phone.length > 12){  				
		alert('번호는 8자리 이상 11자리 이하로 입력해 주세요.');
	}
	event.target.removeEventListener(event.type, arguments.callee);
});

/* [ed] focusout  유효성 검사 */

</script>

</html>