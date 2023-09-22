<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더 : ID 찾기</title>
	<script type="text/javascript">
		
  	</script>
  	<style type="text/css">
 
  	</style>

</head>
<body background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
	<div class="container">
		<h2><b>쉬이이이발 ${requestScope.bean.id}이다</b></h2>
		<h2><b>이발 ${requestScope.result}이다</b></h2>
		<p>회원 가입하는 페이지 입니다.</p>
		<%-- <c:choose>
			test="${requestScope.result = } }
		</c:choose> --%>
			<input type="hidden" name="command" value="meFindId">
			
			<div class="col-lg-3"></div>

			<div class="col-lg-6" align="center">
				
			</div>
			<div class="col-lg-3"></div> 
	</div>
	
</body>
</html>