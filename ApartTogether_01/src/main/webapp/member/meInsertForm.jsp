<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:회원가입</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bcryptjs/2.4.3/bcrypt.min.js"></script><%-- 비밀번호 해시암호화를 위한 파일입니다.-윤영호 --%>
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
	    	/* var isFormValid = hashPasswordAndSubmit();
		    if (!isFormValid) {
		        // 양식이 유효하지 않으면 양식 제출을 중단
		        return false;
		    } */
	      	
	    	var member01 = $('input[type="radio"]:checked').val() ;
			if(member01 == null){
				alert('일반회원 / 사업자 는 반드시 선택이 되어야 합니다.');
				return false ; 
			}  
			
	    	var id01 = $('#id').val();
	    	var check_id = /^[a-z | A-Z]{1,18}[0-9]{1,18}$/; //정규식(a~z, A~Z, 0~9 만 입력가능) 4~18자까지
	  		var idresult = check_id.test(id01);
	  		if(idresult == false){
	  			alert('[아이디]는 영문 및 숫자 만 입력 가능합니다.(4~18자)');  				
	  			return false ; /* false이면 이벤트 전파 방지 */
	  		}
			
	  		var password01 = $('#password').val();
	    	var check_pw = /^[a-zA-Z0-9~!@#$%^&*()_]{6,20}$/; //정규식(a~z, A~Z, 0~9, 특문 만 입력가능)  6~20자까지
	  		var passwordresult = check_pw.test(password01)
	  		if(passwordresult == false){
	  			alert('[비밀번호]는 6자리 이상 20자리 이하로 입력해 주세요.');
	  			$('#password').focus();
	  			return false ;
	  		}
	    	
	    	
	    	var passwordquest = $('#passwordquest').val();
	  		if(passwordquest == "-"){
	            alert("선택된 항목이 없습니다.");
	            $('#passwordquest').focus();
	            return false;
	        }
	  		
	  		var passwordanswer = $('#passwordanswer').val();
	  		if(passwordanswer == null || passwordanswer == ""){
	  			alert('[비밀번호 답변]을 입력해 주세요');
	  			$('#passwordanswer').focus();
	  			return false ;
	  		}
			
	  		var name01 = $('#name').val();
	    	var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}/; // 한글체크 + 2~10자까지
	  		var nameresult = check_kor.test(name01);
	  		if(nameresult == false){  				
	  			$('#name').focus();
	  			alert('이름은 2자리 이상 10자리 이하로 한글로 입력해 주세요.');
	  			return false ;
	  		}
	  		
	  		/* 닉네임 필수 X */
	  		
	  		var phone = $('#phone').val();
	  		var check_phone= /^\d{3}-\d{3,4}-\d{4}$/; /* '010-1234-5678 */
	  		var phoneresult = check_phone.test(phone);
	  		if(phoneresult == false){
	  			alert('휴대폰 번호는 010-1234-5678 형식으로 적어주세요 ');  				
	  			return false ;
	  		}
	  		
	  		/* jqueryUI 플러그인 date picker */
	  		var birth = $('#birth').val();
	  		var regex = /^\d{4}\/[01]\d{1}\/[0123]\d{1}$/ ;
	  		var birthresult = regex.test(birth);
	  		
	  		if(birthresult == false){
	  			alert('생일은 반드시 yyyy/mm/dd 형식으로 입력해 주세요.');  				
	  			return false ;
	  		}
	  		
	  	 	var address = $('#address').val();
	  		var address_detail = $('#address_detail').val();
	    	var check_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|0-9|a-z|A-Z]{1,100}/; // 한글체크 + 2~10자까지
	  		var resultad = check_kor.test(address);
	  		var resultadd = check_kor.test(address_detail);
	  		if(resultad == false || resultadd == false){  				
	  			$('#address').focus();
	  			alert('주소 및 상세 주소를 입력해주세요');
	  			return false ;
	  		}  
	  		
	  	}
		/* [ed] 유효성 검사 */
		
		function mtypeCheck(){
			// mtype을 사업자로 선택하면 컨펌창 후 메인화면 또는 내 가게 등록 화면으로 이동
			var mtype = $('input[name="mtype"]:checked').val();
	  		if(mtype == "biz"){
		  		var returnValue1 = confirm("회원가입이 완료되었습니다.\n내 가게를 등록하러 가시겠습니까?");
				if(returnValue1 == true){// 컨펌창 yes : 내 가게 등록화면으로 이동
					$('#gotoStoreInsert').val("yes");
				}else{// 컨펌창 no 사업자로 유지
					$('#gotoStoreInsert').val("no");
				}
	  		}
	  		
	  		 // 비밀번호를 SHA-256을 사용하여 해싱
	       /*  var passwordInput = document.getElementById("password");
	        var password = passwordInput.value;
	        var hashedPassword = sha256(password);

	        // 해싱된 비밀번호를 숨겨진 필드에 설정
	        var hashedPasswordField = document.getElementById("hashedPassword");
	        hashedPasswordField.value = hashedPassword; */
	  		
	        
	     	/* // 테스트용 비밀키
	        const secretKey = 'mySecretKey';
	     	// 암호화할 비밀번호
	        const password = $('#password').val();

	        // 비밀번호를 AES로 암호화
	        const encryptedPassword = encryptAES(password, secretKey);
	        var encryptedPasswordField = document.getElementById("encryptedPassword");
	        encryptedPasswordField.value = encryptedPassword; */
	        //console.log('암호화된 비밀번호:', encryptedPassword);

	        // 암호화된 비밀번호를 복호화
	        //const decryptedPassword = decryptAES(encryptedPassword, secretKey);
	        //console.log('복호화된 비밀번호:', decryptedPassword);
	        
		    return true;
	  	}
		/* function sha256(input) {
		    var shaObj = new jsSHA("SHA-256", "TEXT");
		    shaObj.update(input);
		    return shaObj.getHash("HEX");
		}
		// AES 암호화 함수
		function encryptAES(plainText, secretKey) {
		    const cipherText = CryptoJS.AES.encrypt(plainText, secretKey).toString();
		    return cipherText;
		}

		// AES 복호화 함수
		function decryptAES(cipherText, secretKey) {
		    const bytes = CryptoJS.AES.decrypt(cipherText, secretKey);
		    const plainText = bytes.toString(CryptoJS.enc.Utf8);
		    return plainText;
		} */
		
		
		
	</script>
	
	<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
	<!-- // SHA-256 해싱을 위해 jsSHA 라이브러리를 추가 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jsSHA/2.0.2/sha256.js"></script>
	<style type="text/css">
	.container {
		margin-top:0px;
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
		width:100%;
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
	<div class="container">
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8" align="center">
				<h2  class="mainTitle">회원 가입</h2>
				<form action="<%=withFormTag%>" id="insertForm" method="post" enctype="multipart/form-data" onsubmit="return validCheck();">
					<input type="hidden" name="command" value="meInsert">
					<input type="hidden" id="gotoStoreInsert" name="gotoStoreInsert" value="no">
	
					<div class="input-group" align="center">
						<span class="input-group-text col-md-3">회원유형 <font
							color="red">*</font></span>
	
						<div class="form-control">
							<label class="radio-inline radio_membertype"> &nbsp;<input class="mtype"
								type="radio" id="membertype" name="mtype" value="user">
								🙋‍♀️ ‍일반회원
							</label> | <label class="radio-inline radio_membertype"> &nbsp;<input class="mtype"
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
						<!-- <input type="hidden" id="hashedPassword" name="hashedPassword" value="..."> -->
					</div>
					<div class="input-group">
						<%-- 패스워드 질문 목록 수정 시 meUpdateForm.jsp도 함께 수정해주세요.--%>
						<span class="input-group-text col-md-4">패스워드 질문<font color="red">*</font></span>
						<select class="form-select"	id="passwordquest" name="passwordquest" class="passwordquest">
							<option value="-" selected>-- 선택해 주세요.
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
						<span class="input-group-text col-md-3">닉네임</span> 
						<input class="form-control" type="text"
							id="nickname" name="nickname"
							placeholder="닉네임 / 공란 시 랜덤한 아이디가 생성!">
					</div>
					<div class="input-group">
						<span class="input-group-text col-md-3">성별 <font color="red">*</font></span>
						<div class="form-control" align="center">
							<label class="radio-inline radio_gender"> &nbsp;<input
								type="radio" id="gender" name="gender" value="male" checked> 👦
								남자
							</label> | <label class="radio-inline radio_gender"> &nbsp;<input
								type="radio" id="gender" name="gender" value="female">
								👧 여자
							</label>
						</div>
					</div>
					<div class="input-group row" align="center">
						<span class="input-group-text col-md-3">전화번호 <font
							color="red">*</font></span> <input class="form-control" type="text"
							id="phone" name="phone" placeholder="ex) 010-4567-1234">
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
							onclick="return mtypeCheck();">등록</button>
					</div>
				</form>
			</div>
			<div class="col-lg-2"></div>
		</div>
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
}
</script>
<!-- [ed] 다음(카카오) 주소 검색 스크립트 -->
</html>