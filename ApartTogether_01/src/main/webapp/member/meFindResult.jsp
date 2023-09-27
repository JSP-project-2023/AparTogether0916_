<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- [패스워드,id 찾기 결과] 창에 common형식이 그대로 나와서 일부러 뺌 -->
<%@ include file="./../common/bootstrap5.jsp" %>

<%-- jstl을 위한 태그 라이브러리 선언 | 해당 내용 common에 들어가 있음--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:결과창</title>
	<script type="text/javascript">
		
  	</script>
  	<style type="text/css">
 	.container{margin-top: 150px;}
  	</style>
</head>
<body background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<div class="container">
		<input type="hidden" name="command" value="meFindId" >
		
		<div class="col-lg-3" align="center"></div>
		<!-- 넘어온 컨트롤러에 따라 분기처리로 보여지는 값이 달라진다. -->
		<div class="col-lg-6" align="left">
			<div class="card">
	  			<div class="card-header">${requestScope.bean.name} 회원님</div>
  				<div class="card-body">
  					<c:choose>
					<c:when test="${requestScope.gotopage eq 'gotopageID'}">
						ID는 [<b> ${requestScope.bean.id} </b>]입니다.
					</c:when>
					<c:when test="${requestScope.gotopage eq 'gotopagePW'}">
						비밀번호는 [<b> ${requestScope.bean.password} </b>] 입니다.
					</c:when>
					</c:choose>
  				</div>
			</div>
		</div>
		<div class="col-lg-3"></div> 
	</div>
	
</body>
</html>