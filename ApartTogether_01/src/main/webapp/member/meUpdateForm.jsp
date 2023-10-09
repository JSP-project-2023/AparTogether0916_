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
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/member/memberCSS/meUpdate.css" type="text/css">
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
			<h2 class="mainTitle">회원 정보 수정</h2>
			<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data" onsubmit="mtypeChangeCheck()">
			
				<input type="hidden" name="command" value="meUpdate">
				<input type="hidden" id="oldmtype" name="oldmtype" value="${requestScope.bean.mtype}">
				<input type="hidden" id="changeBizToUser" name="changeBizToUser" value="yes">
				<input type="hidden" id="gotoStoreInsert" name="gotoStoreInsert" value="no">
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">회원유형</span>
					<div class="input_text">
						<label class="radio-inline radio_mtype">
							&nbsp;<input type="radio" id="mtype1" name="mtype" value="user">일반회원
						</label>
						<label class="radio-inline radio_mtype">
							&nbsp;<input type="radio" id="mtype2" name="mtype" value="biz">사업자
						</label>
					</div>
				</div>	

				<div class="info_items">
					<span class="input-field-label">아이디</span><!-- 수정불가항목 -->
					<input disabled="disabled" class="input_text inputbg" type="text" id="fakeid" name="fakeid" value="${requestScope.bean.id}">				
					<input type="text" id="id" name="id" value="${requestScope.bean.id}" hidden>
				</div>
				<div class="info_items"> 
					<span class="mustInput">*</span><span class="input-field-label">이름</span>
					<input class="input_text" type="text" id="name" name="name" value="${requestScope.bean.name}">				
				</div>
				
				<div class="info_items">
					<span class="input-field-label">닉네임</span><!--  -->
					<input class="input_text" type="text" id="nickname" name="nickname" 
					       value="${requestScope.bean.nickname }" placeholder="닉네임 / 공란 시 랜덤한 아이디가 생성!">				
				</div>
				<div class="info_items">
					<span class="input-field-label profile-img">프로필사진</span>
					
					<%-- profile가 null인 상태라면 기본이미지(default.jpg)를 보여줍니다. --%>
					<c:if test="${requestScope.bean.profile == null}">
						<img class="card-img-top  small_image rounded-circle" alt="기본이미지" 
								src="image/defaultProfile.jpeg"  >
					</c:if>
					<c:if test="${requestScope.bean.profile != null}">
						<img class="card-img-top  small_image rounded-circle" alt="${requestScope.bean.profile}" 
				         src="uploadProfileImage/${requestScope.bean.profile}"  >
					</c:if>
					<%-- <img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  > --%>
				
					<input class="input_text input-text-img" type="file" id="profile" name="profile">
					<input type="text" name="deleteProfile" value="${requestScope.bean.profile}" hidden>
				</div>
				
				<div class="info_items" >
					<span class="mustInput">*</span><span class="input-field-label">비밀 번호</span>
					<input class="input_text" type="password" id="password" name="password"  value="${requestScope.decryptedPassword}">
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">성별</span>
					<div class="input_text">
						<label class="radio-inline radio_gender radio_mtype"> 
							&nbsp;<input type="radio" id="gender1" name="gender" value="male">남자
						</label>
						<label class="radio-inline radio_gender radio_mtype"> 
							&nbsp;<input type="radio" id="gender2" name="gender" value="female">여자
						</label>
					</div>
				</div>
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">전화번호</span>
					<input class="input_text" type="text" id="phone" name="phone" value="${requestScope.bean.phone }">			
				</div>
				
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">생일</span>
					<input class="input_text" type="datetime" id="birth" name="birth" value="${requestScope.birthSet[0]}/${requestScope.birthSet[1]}/${requestScope.birthSet[2]}">			
				</div> 
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">주소</span>
					<input class="input_text" type="text" id="address" name="address" value="${addressSet[0] }">			
				</div>
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">상세주소</span>
					<input class="input_text" type="text" id="address_detail" name="address_detail" value="${addressSet[1] }">			
				</div>
				
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">비밀번호 질문</span>
					<%-- <input disabled="disabled" class="input_text" type="text" id="fakepasswordquest" name="fakepasswordquest" value="${requestScope.bean.passwordquest}">			
					<input class="input_text" type="text" id="passwordquest" name="passwordquest" value="${requestScope.bean.passwordquest}" hidden>	 --%>		
					<select class="input_text"	id="passwordquest" name="passwordquest" class="passwordquest">
						<option value="-" selected>-- 선택해 주세요.
						<option value="초등학교 이름은">초등학교 이름은?
						<option value="아버지 성함은">아버지 성함은?
						<option value="내가 좋아하는 동물은">내가 좋아하는 동물은?
						<option value="내 애완동물의 이름은">내 애완동물의 이름은?
					</select>
				</div>
				
				<div class="info_items">
					<span class="mustInput">*</span><span class="input-field-label">비밀번호 답변</span>
					<input class="input_text" type="text" id="passwordanswer" name="passwordanswer" value="${requestScope.bean.passwordanswer }">			
				</div>
			
				<div class="btnArea">
					<button  type="submit" class="big_ctlbtn update_bigbtn" onclick="return validCheck();">수정</button>
			        <a href="javascript:history.go(0)" class="big_ctlbtn reset_bigbtn">초기화</a>
			        <button type="button" class="big_ctlbtn cancle_bigbtn" onclick="history.back();">돌아 가기</button>
				</div>
			</form>
		</c:if>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>

<!-- [st] 다음(카카오) 주소 검색 스크립트 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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

	$(document).ready(function(){
		/* value 속성의 값이 일치하는 항목에 대하여 체크 on 시킵니다. */
	  	$('input[value="${bean.gender}"]').attr('checked', true);
		$('input[value="${bean.mtype}"]').attr('checked', true);
		$('select[name="passwordquest"]').val("${bean.passwordquest}"); 
	    $('button[type="reset"]').click(function() {
	    	
	    });
	});
	/* [st] submit 유효성 검사 */
	   function validCheck(){ /* form validation check */
	   	var member01 = $('input[type="radio"]:checked').val() ;
		if(member01 == null){
			alert('일반회원 / 사업자 는 반드시 선택이 되어야 합니다.');
			return false ; 
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
	
	 // 사용자가 입력한 mtype에 따라 컨펌창을 띄우고 결과값을 저장합니다.
	function mtypeChangeCheck(){  	  		
		var oldmtype = $('#oldmtype').val();
		var mtype = $('input[name="mtype"]:checked').val();
	 		if(oldmtype == "biz") {
			if(mtype == "biz") { // 사업자->사업자
			}else if(mtype == "user") { // 사업자->일반회원 : 컨펌창("내가게 다 사라집니다")
				var returnValue1 = confirm("내가 등록한 가게 정보가 모두 사라집니다. \n정말 일반회원으로 변경하시겠습니까?");
				if(returnValue1 == true){
					$('#changeBizToUser').val("yes");
				}else{
					$('#changeBizToUser').val("no");
				}
			}
		}else if(oldmtype == "user") {
			if(mtype == "user") { // 일반회원->일반회원
			}else if(mtype == "biz") { // 일반회원->사업자 : 컨펌창(내가게 등록하러 가시겠습니까?)
				var returnValue2 = confirm("회원유형이 사업자로 변경되었습니다. \n내 가게를 등록하러 가시겠습니까?");
				if(returnValue2 == true){
					$('#gotoStoreInsert').val("yes");
				}else{
					$('#gotoStoreInsert').val("no");
				}
			}
		}
	}
</script>
<!-- [ed] 다음(카카오) 주소 검색 스크립트 -->
</html>