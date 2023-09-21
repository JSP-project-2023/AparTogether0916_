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
  		
  		// mtype에 따라 알럿창, 컨펌창 후 마이페이지 또는 내 가게 등록 화면으로 이동
  		var oldmtype = $('#oldmtype').val();
  		var mtype = $('#mtype').val();
  		if(oldmtype == "biz") { // 수정 전에 사업자 였음
			if(mtype == "biz") {
				alert("수정 완료되었습니다.");
				// 사업자를 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
				//bean.setMtype(mr.getParameter("mtype"));
			}else if(mtype == "user") {
				// 사업자가 일반회원으로 변경한 거면 컨펌창(내가게 다 사라집니다)
				var returnValue1 = confirm("내가 등록한 가게 정보가 모두 사라집니다. 수정하시겠습니까?");
				if(returnValue1 == true){
					// 컨펌창 yes 알럿창(수정완료)띄우고 마이페이지로 이동
				}else{
					// 컨펌창 no 사업자로 유지
				}
				//bean.setMtype(oldmtype);
			}
		}else if(oldmtype == "user") { // 수정 전에 일반회원 이었음
			if(mtype == "user") {
				alert("수정 완료되었습니다.");
				// 일반회원을 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
				//bean.setMtype(mr.getParameter("mtype"));
			}else if(mtype == "biz") {
				// 일반회원이 사업자로 변경한 거면 컨펌창(내가게 등록하러 가시겠습니까?)
				var returnValue2 = confirm("수정완료되었습니다. 내 가게를 등록하러 가시겠습니까?");
				if(returnValue1 == true){
					// 컨펌창 yes '내 가게등록 페이지'로 이동
				}else{
					// 컨펌창 no 마이페이지로 이동
				}
				//bean.setMtype(mr.getParameter("mtype"));
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
		<h2>회원 정보 수정</h2>
		<p>특정 회원에 대하여 정보를 수정하는 페이지 입니다.</p>
		<form action="<%=withFormTag%>" method="post" enctype="multipart/form-data">
		
			<input type="hidden" name="command" value="meUpdate">
			<%-- <input type="hidden" name="mtype" value="${requestScope.bean.mtype}"> --%>
			<input type="hidden" name="oldmtype" value="${requestScope.bean.mtype}">
			
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
					         src="upload/${requestScope.bean.profile}"  >
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
			
			<div id="buttonset" class="input-group">
				<button type="submit" class="btn btn-primary" onclick="return validCheck();">수정</button>
				&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-primary">초기화</button>				
			</div>
		</form>
	</div>
</body>
</html>