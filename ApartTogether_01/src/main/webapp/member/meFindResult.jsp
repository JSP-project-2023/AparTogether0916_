<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- common내용은 가져오되 화면에 보여주진 않는다.(style display:none설정)  -->
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:결과창</title>
	<script type="text/javascript">
		
  	</script>
  	<style type="text/css">
  	.header_underline {
  		/* common의 내용은 화면에 보여주지 않는다. */
        display: none;
    }
 	.container{
 		margin-top: 100px;
		max-width: 500px;
 	}
 	
 	
 	
  	</style>
</head>
<body background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<div class="container">
		<input type="hidden" name="command" value="meFindId" >
		
		<!-- 넘어온 컨트롤러에 따라 분기처리로 보여지는 값이 달라진다. -->
		<h2 style="padding: 20px">
			<c:choose>
				<c:when test="${requestScope.gotopage eq 'gotopageID'}">
					<h2 style="padding: 20px"><b>ID 찾기</b></h2>
				</c:when>
				<c:when test="${requestScope.gotopage eq 'gotopagePW'}">
					<h2  style="padding: 20px"><b>패스워드 찾기</b></h2>
				</c:when>
			</c:choose>
		</h2>
		
		<div class="card">
  			<div class="card-header">${requestScope.bean.name} 회원님</div>
 				<div class="card-body">
 					<c:choose>
						<c:when test="${requestScope.gotopage eq 'gotopageID'}">
							ID는 [<b> ${requestScope.bean.id} </b>]입니다.
						</c:when>
						<c:when test="${requestScope.gotopage eq 'gotopagePW'}">
							비밀번호는 [<b> ${requestScope.decryptedPassword} </b>] 입니다.
						</c:when>
					</c:choose>
 				</div>
			</div>
			<div align="center" style="margin-top:5px">
				<button type="button" class="big_ctlbtn else_bigbtn" style="width:100%;" onclick="javascript:self.close();">닫기</button>
			</div>
		</div>
	</div>
	
</body>
</html>