
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:로그인</title>
	<script type="text/javascript">
			$(document).ready(function() {	
				var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
				var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
					return new bootstrap.Tooltip(tooltipTriggerEl)
				});		
				$('#id').focus(); // 로그인페이지로 이동하자마자 id입력할 수 있게 추가했습니다. 9/27
			});
			function validCheck(){/* form validation check */
	  			var id = $('#id').val();
	  			if(id.length < 2 || id.length > 11){
	  				swal('아이디는 3글자 이상 10글자 이하이어야 합니다.');
	  				$('#id').focus();
	  				return false ; /* false이면 이벤트 전파 방지 */
	  			}
			}
			/* [st] popup 창으로 열기 */
			function popupfindID(){
	            var url = "<%=notWithFormTag%>meFindId";
	            var name = "popup test";
	            var option = "width = 700, height = 500, top = 100, left = 200, location = no"
	            window.open(url, name, option);
	        }
			function popupfindPW(){
	            var url = "<%=notWithFormTag%>meFindPassword";
	            var name = "popup test";
	            var option = "width = 700, height = 590, top = 100, left = 200, location = no"
	            window.open(url, name, option);
	        }
			/* [ed] popup 창으로 열기 */
			
	</script>
	<style type="text/css">
		.btnAlign{
  			align-items: center;
            display: inline-flex;
            justify-content: center;
  		}
	</style>

</head>
<body>
	<div class="container">
		<div class="title-area"></div>
		<form action="<%=withFormTag%>" method="post">
			<input type="hidden" name="command" value="meLogin">
			<div align="left">
				<h3 style="padding: 20px">
				</h3>
			</div>

			<div class="cardArea">
	
				<div class="card-content">
				<table>
				<tr>
					<th>
						<div class="header1" style="font-size: 25px; font-weight: 600">로그인해서 배달비를 아껴보세요</div>
					</th>
					<th>
						<img alt="자물쇠"	src="${pageContext.request.contextPath}/image/자물쇠_열쇠.png" height="25%" width="25%">
					</th>
				</tr>
				</table>
				<br/><br/><br/>
					<!-- [st] 아이디 입력 공간 -->
					<div class="inputArea">
						<input class="input_text" type="text" id="id" name="id"
							placeholder="아이디를 입력해 주세요" data-bs-placement="right" data-bs-toggle="tooltip"
							title="아이디를 입력해 주세요" maxlength="18">
					</div>
					<!-- [ed] 아이디 입력 공간 -->

					<!-- [st] 패스워드 입력 공간 -->
					<div class="inputArea">
						 <input class="input_text" type="password" id="password"
							name="password" placeholder="비밀번호" data-bs-toggle="tooltip"
							data-bs-placement="right" title="비밀번호는 영문, 숫자, 특문 포함입니다." maxlength="20">
					</div>
					<!-- [ed] 패스워드 입력 공간 -->

					<!-- [st] 버튼 공간 -->
					<div align="center">
						<button type="submit" class="big_ctlbtn normal_bigbtn">로그인</button>
						<br /> <br /> 
						<a type="button" href="<%=notWithFormTag%>meInsert" class="btn ">회원가입</a> 
						| 
						<a type="popup" href="javascript:popupfindID()"class="btn ">아이디 찾기</a> 
						| 
						<a type="popup" href="javascript:popupfindPW()" class="btn ">비밀번호 찾기</a>
					</div>
					<!-- [ed] 버튼 공간 -->
					
				</div>
			</div>
		</form>
	</div>
</body>
</html>