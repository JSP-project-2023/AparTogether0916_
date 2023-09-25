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
	  	  	
  		});
  		function validCheck(){ /* form validation check */
  			console.log('!!!!validCheck() function called');
  			// 필수 항목(id, name, ) 중 미입력 항목이 존재하는지 체크합니다.
  			var id = $('#id').val();
  		}
  		
  		function mtypeChangeCheck(){
  			// mtype에 따라 알럿창, 컨펌창 후 마이페이지 또는 내 가게 등록 화면으로 이동
  	  		var oldmtype = $('#oldmtype').val();
  			var mtype = $('input[name="mtype"]:checked').val();
  	  		
  	  		if(oldmtype == "biz") { // 수정 전에 사업자 였음
  				if(mtype == "biz") { // 사업자를 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
  					alert('수정완료');
  				}else if(mtype == "user") { // 사업자가 일반회원으로 변경한 거면 컨펌창(내가게 다 사라집니다)
  					var returnValue1 = confirm("내가 등록한 가게 정보가 모두 사라집니다. \n정말 일반회원으로 변경하시겠습니까?");
  					if(returnValue1 == true){// 컨펌창 yes 알럿창(수정완료)띄우고 마이페이지로 이동
  						alert('회원유형이 사업자로 변경되었습니다.');
  					}else{// 컨펌창 no 사업자로 유지
  						alert('회원유형을 사업자로 유지합니다.');
  						$('#changeBizToUser').val("no"); // Set the value of #yesorno to "no"
  					}
  				}
  			}else if(oldmtype == "user") { // 수정 전에 일반회원 이었음
  				if(mtype == "user") { // 일반회원을 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
  					alert('수정완료');
  				}else if(mtype == "biz") { // 일반회원이 사업자로 변경한 거면 컨펌창(내가게 등록하러 가시겠습니까?)
  					var returnValue2 = confirm("회원유형이 사업자로 변경되었습니다. \n내 가게를 등록하러 가시겠습니까?");
  					if(returnValue2 == true){ // 컨펌창 yes '내 가게등록 페이지'로 이동
  						$('#gotoStoreInsert').val("yes");
  					}else{ // 컨펌창 no 마이페이지로 이동
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
  		.small_image{width:50px;height:50px;margin:2px;border-radius:5px;}
  	</style>
</head>
<body>
	<div class="container">
		
		
		<%-- accessMeUpdate : 회원정보수정 페이지 열람 가능 여부를 저장하는 변수입니다. 0(열람불가), 1(열람가능) --%>
		<c:set var="accessMeUpdate" value="0"/>
		<c:if test="${whologin_id != requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 다른 사람의 회원정보수정 페이지를 열람할 수 없습니다. --%>
			<c:set var="accessMeUpdate" value="0"/>
		</c:if>
		<c:if test="${whologin_id == requestScope.bean.id}">
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
					<span class="input-group-text">회원유형</span><!-- 수정불가항목 -->
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
					<span class="input-group-text">아이디</span><!-- 수정불가항목 -->
					<input disabled="disabled" class="form-control" type="text" id="fakeid" name="fakeid" value="${requestScope.bean.id}">				
					<input type="text" id="id" name="id" value="${requestScope.bean.id}" hidden>
				</div>
				<div class="input-group"> 
					<span class="input-group-text">이름</span>
					<input class="form-control" type="text" id="name" name="name" value="${requestScope.bean.name}">				
				</div>
				
				<div class="input-group">
					<span class="input-group-text col-md-2">닉네임</span>
					<input class="form-control" type="text" id="nickname" name="nickname" value="${requestScope.bean.nickname }">				
				</div>
				<div class="input-group">
					<span class="input-group-text col-md-2">프로필사진</span>
					<img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  >
					<input class="form-control" type="file" id="profile" name="profile"  ">
					<input type="text" name="deleteProfile" value="${requestScope.bean.profile}" hidden>
				</div>
				
				<div class="input-group" >
					<span class="input-group-text">비밀 번호</span>
					<input class="form-control" type="password" id="password" name="password"  value="${requestScope.bean.password}">		
				</div>
				
				<div class="input-group">
					<span class="input-group-text">성별</span>
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
					<span class="input-group-text">전화번호</span>
					<input class="form-control" type="text" id="phone" name="phone" value="${requestScope.bean.phone }">			
				</div>
				
				
				<div class="input-group">
					<span class="input-group-text">생일</span>
					<input class="form-control" type="datetime" id="birth" name="birth" value="${requestScope.bean.birth }">			
				</div> 
				
				<div class="input-group">
					<span class="input-group-text">주소</span>
					<input class="form-control" type="text" id="address" name="address" value="${requestScope.bean.address }">			
				</div>
				
				<div class="input-group">
					<span class="input-group-text">비밀번호 질문</span>
					<input disabled="disabled" class="form-control" type="text" id="fakepasswordquest" name="fakepasswordquest" value="${requestScope.bean.passwordquest}">			
					<input class="form-control" type="text" id="passwordquest" name="passwordquest" value="${requestScope.bean.passwordquest}" hidden>			
				
				</div>
				
				<div class="input-group">
					<span class="input-group-text">비밀번호 답변</span>
					<input class="form-control" type="text" id="passwordanswer" name="passwordanswer" value="${requestScope.bean.passwordanswer }">			
				</div>
				
				<button type="submit" class="btn btn-primary" onclick="return validCheck();">수정</button>
				<button type="reset" class="btn btn-primary">초기화</button>	
				
			
			</form>
			
		</c:if>
		
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
	
		
	
		
	</div>
</body>
</html>