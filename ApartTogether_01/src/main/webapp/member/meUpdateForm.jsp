<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ include file="./../common/common.jsp" %>

<%@ page import="com.apartogether.model.dao.MemberDao" %>
<%@ page import="com.apartogether.model.bean.Member"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:회원정보수정</title>
	<script type="text/javascript">
  		$(document).ready(function(){
  			/* value 속성의 값이 일치하는 항목에 대하여 체크 on 시킵니다. */
	  	  	$('input[value="${bean.gender}"]').attr('checked', true);
  			$('input[value="${bean.mtype}"]').attr('checked', true);
  			var pqnum = 0;
  			switch("${bean.passwordquest}"){
  				case "초등학교 이름은": pqnum = 1 ; break;
  				case "아버지 성함은": pqnum = 2 ; break;
  				case "내가 좋아하는 동물은": pqnum = 3 ; break;
  				case "내 애완동물의 이름은": pqnum = 4 ; break;
  				default: pqnum = 0;
  			} 
  			$('select[name="passwordquest"] option').eq(pqnum).prop('selected', true); 
  			//$('option').eq("${bean.passwordquest}").attr('selected', true);
  			//console.log(">>${bean.passwordquest}<<");
	  	  	
  		});
  		/* [st] submit 유효성 검사 */
  	    function validCheck(){ /* form validation check */
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
  	    	
  	    	<%-- 회원정보 수정에서 passwordquest는 수정할 수 없습니다. --%>
  	    	var passwordquest = $('#passwordquest').val();
  	  		if(passwordquest == "-"){
  	            alert("선택된 항목이 없습니다.");
  	            $('#passwordquest').focus();
  	            return false;
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
  		
  		function mtypeChangeCheck(){
  			// mtype에 따라 알럿창, 컨펌창 후 마이페이지 또는 내 가게 등록 화면으로 이동
  	  		var oldmtype = $('#oldmtype').val();
  			var mtype = $('input[name="mtype"]:checked').val();
  	  		
  	  		if(oldmtype == "biz") { // 수정 전에 사업자 였음
  				if(mtype == "biz") { // 사업자를 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
  					//alert('수정완료');
  				}else if(mtype == "user") { // 사업자가 일반회원으로 변경한 거면 컨펌창(내가게 다 사라집니다)
  					var returnValue1 = confirm("내가 등록한 가게 정보가 모두 사라집니다. \n정말 일반회원으로 변경하시겠습니까?");
  					if(returnValue1 == true){// 컨펌창 yes 알럿창(수정완료)띄우고 마이페이지로 이동
  						$('#changeBizToUser').val("yes");
  						//alert('회원유형이 사업자로 변경되었습니다.');
  					}else{// 컨펌창 no 사업자로 유지
  						//alert('회원유형을 사업자로 유지합니다.');
  						$('#changeBizToUser').val("no"); // Set the value of #yesorno to "no"
  					}
  				}
  			}else if(oldmtype == "user") { // 수정 전에 일반회원 이었음
  				if(mtype == "user") { // 일반회원을 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
  					//alert('수정완료');
  				}else if(mtype == "biz") { // 일반회원이 사업자로 변경한 거면 컨펌창(내가게 등록하러 가시겠습니까?)
  					var returnValue2 = confirm("회원유형이 사업자로 변경되었습니다. \n내 가게를 등록하러 가시겠습니까?");
  					if(returnValue2 == true){ // 컨펌창 yes '내 가게등록 페이지'로 이동
  						$('#gotoStoreInsert').val("yes");
  					}else{ // 컨펌창 no 마이페이지로 이동
  						$('#gotoStoreInsert').val("no");
  					}
  				}
  			}
  		}
  		
  		
  	</script>
  	<style type="text/css">
  		/* box model에 대한 공부가 필요합니다. */
  		.container{margin-top: 10px;}
  		.input-group{margin: 7px;}
  		.input-group-text{
  			display: block;
  			margin-left: auto;
  			margin-right: auto;
  		}
  		#buttonset{margin-top: 15px;}
  		.radio_gender{font-size: 1.1rem;}
  		.radio_mtype{font-size: 1.1rem;} /* 주위 글꼴의 1.1배 */
  		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
  	</style>
  	<style type="text/css">
		.container {margin-top:;}
		
		.input-group {
			margin: 7px;
			max-width: 1280px;
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
<body>
	<div class="container">
		
		
		<%-- accessMeUpdate : 회원정보수정 페이지 열람 가능 여부를 저장하는 변수입니다. 0(열람불가), 1(열람가능) --%>
		<c:set var="accessMeUpdate" value="0"/>
		<c:if test="${sessionScope.loginfo.id != requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 다른 사람의 회원정보수정 페이지를 열람할 수 없습니다. --%>
			<c:set var="accessMeUpdate" value="0"/>
		</c:if>
		<c:if test="${sessionScope.loginfo.id == requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 본인의 회원정보수정 페이지만 열람할 수 있습니다. --%>
			<c:set var="accessMeUpdate" value="1"/>
		</c:if>
		<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
			<%-- 관리자 : 모든 회원의 회원정보수정 페이지를 열람할 수 있습니다. --%>
			<c:set var="accessMeUpdate" value="1"/>
		</c:if>
		
		
		<c:if test="${accessMeUpdate == 0 }">
			<%-- 열람불가 --%>
			!!!-비정상적인 접근입니다. 다른 회원의 회원정보 수정 페이지에 접근하실 수 없습니다..-!!!
		</c:if>
		<c:if test="${accessMeUpdate == 1 }">
			<%-- 열람가능 --%>
			<h2>회원 정보 수정</h2>
			<p>특정 회원에 대하여 정보를 수정하는 페이지 입니다.</p>
			<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data" onsubmit="mtypeChangeCheck()">
			
				<input type="hidden" name="command" value="meUpdate">
				<%-- <input type="hidden" name="mtype" value="${requestScope.bean.mtype}"> --%>
				<input type="hidden" id="oldmtype" name="oldmtype" value="${requestScope.bean.mtype}">
				<input type="hidden" id="changeBizToUser" name="changeBizToUser" value="yes">
				<input type="hidden" id="gotoStoreInsert" name="gotoStoreInsert" value="no">
				
				<div class="input-group">
					<span class="input-group-text col-md-2">회원유형<font color="red">*</font></span>
					<div class="form-control" >
						<label class="radio-inline radio_mtype"> 
							&nbsp;<input type="radio" id="mtype1" name="mtype" value="user">일반회원
						</label>
						<label class="radio-inline radio_mtype"> 
							&nbsp;<input type="radio" id="mtype2" name="mtype" value="biz">사업자
						</label>
					</div>
				</div>	

				<div class="input-group">
					<span class="input-group-text col-md-2">아이디</span><!-- 수정불가항목 -->
					<input disabled="disabled" class="form-control" type="text" id="fakeid" name="fakeid" value="${requestScope.bean.id}">				
					<input type="text" id="id" name="id" value="${requestScope.bean.id}" hidden>
				</div>
				<div class="input-group"> 
					<span class="input-group-text col-md-2">이름<font color="red">*</font></span>
					<input class="form-control" type="text" id="name" name="name" value="${requestScope.bean.name}">				
				</div>
				
				<div class="input-group">
					<span class="input-group-text col-md-2">닉네임<font color="red">*</font></span>
					<input class="form-control" type="text" id="nickname" name="nickname" value="${requestScope.bean.nickname }">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">프로필사진</span>
					
					<%-- profile가 null인 상태라면 기본이미지(default.jpg)를 보여줍니다. --%>
					<c:if test="${requestScope.bean.profile == null}">
						<img class="card-img-top  small_image rounded" alt="기본이미지" 
						src="./../upload/defaultprofile.jpg"  >
					</c:if>
					<c:if test="${requestScope.bean.profile != null}">
						<img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
				         src="uploadProfileImage/${requestScope.bean.profile}"  >
					</c:if>
					<br>
					<%-- <img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  > --%>
				
					<input class="form-control" type="file" id="profile" name="profile"  ">
					<input type="text" name="deleteProfile" value="${requestScope.bean.profile}" hidden>
				</div>
				
				<div class="input-group" >
					<span class="input-group-text col-md-2">비밀 번호<font color="red">*</font></span>
					<input class="form-control" type="password" id="password" name="password"  value="${requestScope.bean.password}">		
				</div>
				
				<div class="input-group">
					<span class="input-group-text col-md-2">성별<font color="red">*</font></span>
					<div class="form-control">
						<label class="radio-inline radio_gender"> 
							&nbsp;<input type="radio" id="gender1" name="gender" value="male">남자
						</label>
						<label class="radio-inline radio_gender"> 
							&nbsp;<input type="radio" id="gender2" name="gender" value="female">여자
						</label>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">전화번호<font color="red">*</font></span>
					<input class="form-control" type="text" id="phone" name="phone" value="${requestScope.bean.phone }">			
				</div>
				
				
				<div class="input-group">
					<span class="input-group-text col-md-2">생일<font color="red">*</font></span>
					<input class="form-control" type="datetime" id="birth" name="birth" value="${requestScope.birthSet[0]}/${requestScope.birthSet[1]}/${requestScope.birthSet[2]}">			
				</div> 
				
				<div class="input-group">
					<span class="input-group-text col-md-2">주소<font color="red">*</font></span>
					<input class="form-control" type="text" id="address" name="address" value="${addressSet[0] }">			
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">상세주소<font color="red">*</font></span>
					<input class="form-control" type="text" id="address_detail" name="address_detail" value="${addressSet[1] }">			
				</div>
				
				
				<div class="input-group">
					<span class="input-group-text col-md-2">비밀번호 질문<font color="red">*</font></span>
					<%-- <input disabled="disabled" class="form-control" type="text" id="fakepasswordquest" name="fakepasswordquest" value="${requestScope.bean.passwordquest}">			
					<input class="form-control" type="text" id="passwordquest" name="passwordquest" value="${requestScope.bean.passwordquest}" hidden>	 --%>		
					<select class="form-select"	id="passwordquest" name="passwordquest" class="passwordquest">
						<option value="-" selected>-- 선택해 주세요.
						<option value="초등학교 이름은">초등학교 이름은?
						<option value="아버지 성함은">아버지 성함은?
						<option value="내가 좋아하는 동물은">내가 좋아하는 동물은?
						<option value="내 애완동물의 이름은">내 애완동물의 이름은?
					</select>
				</div>
				
				<div class="input-group">
					<span class="input-group-text col-md-2">비밀번호 답변</span>
					<input class="form-control" type="text" id="passwordanswer" name="passwordanswer" value="${requestScope.bean.passwordanswer }">			
				</div>
			
			
				<div style="text-align: center;">
				<button  type="submit" class="btn button-18 "  style=" padding-left:50px; padding-right:50px" 
						 onclick="return validCheck();">수정</button>
				<button type="reset" class="btn button-18 " style="padding-left:50px; padding-right:50px"  >
						초기화</button>
				</div>
			
			</form>
		</c:if>
		
	 	<div id="backButton">
			<button type="button" class="btn button-18 " style=" padding-left:20px; padding-right:20px" onclick="history.back();">돌아 가기</button>
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