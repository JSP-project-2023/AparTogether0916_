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
  			/* $('input[value="${bean.mtype}"]').attr('checked', true); */
	  	  	
  		});
  		function validCheck(){ /* form validation check */
  			console.log('!!!!validCheck() function called');
  			// 필수 항목(id, name, ) 중 미입력 항목이 존재하는지 체크합니다.
  			var id = $('#id').val();
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
			<input type="hidden" name="mtype" value="${requestScope.bean.mtype}">
			
			<div class="input-group">
				<span class="input-group-text">회원유형</span><!-- 수정불가항목 -->
				<div class="form-control" >
					<c:choose>
						<c:when test="${requestScope.bean.mtype == 'user' }">
							<span>일반회원</span>
						</c:when>
						<c:when test="${requestScope.bean.mtype == 'biz' }">
							<span>사업자</span>
						</c:when>
						<c:when test="${requestScope.bean.mtype == 'admin' }">
							<span>관리자</span>
						</c:when>
						<c:otherwise>
							<span>멤버유형 정보가 올바르지 않습니다.(user, biz, admin)</span>
						</c:otherwise>
					</c:choose>
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