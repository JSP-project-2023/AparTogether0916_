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
 	.container{margin-top: 150px;}
 	
 	
 	.button-18 {
		align-items: center;
		background-color: #d8e4d2;
		border: 0;
		box-sizing: border-box;
		color: #6f726e;
		cursor: pointer;
		display: inline-flex;
		/* font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto,
			"Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans",
			Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji",
			"Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif; */
		
		font-weight: 600;
		justify-content: center;
		line-height: 20px;
		/* max-width: 900px; */
		 width: 100%; 
		min-height: 50px;
		/* min-width: 0px; */
		overflow: hidden;
		padding: 0px;
		/* padding-left: 230px; */
		/* padding-right: 230px; */
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
						비밀번호는 [<b> ${requestScope.decryptedPassword} </b>] 입니다.
					</c:when>
					</c:choose>
  				</div>
				
			</div >
			<div align="center" style="margin-top:5px">
				<button type="button" class="btn button-18" onclick="javascript:self.close();">닫기</button>
			</div>
		</div>
		<div class="col-lg-3"></div> 
	</div>
	
</body>
</html>