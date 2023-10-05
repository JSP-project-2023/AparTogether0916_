<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더 : ID 찾기</title>
	<script type="text/javascript">
		$(document).ready(function() {
			/* $('#birth').datepicker(); */
			$('#birth').datepicker({
				dateFormat : "yy/mm/dd"
			});
		});
	</script>
	<style type="text/css">
	.navbar_member {
        display: none;
    }
	.navbar_menu {
        display: none;
    }
    .hamarea {
        display: none;
    }
	.container {
		max-width: 500px;
	}
	
	.input-group {
		margin-top: 0.5rem; 
		margin-bottom: 0.5rem;
	}
	
	.input-group-text {
		display: block;
		margin-left: auto;
		margin-right: auto;
	}
	
	#buttonset {
		margin-top: 15px;
	}
	
	.radio_membertype {
		font-size: 0.8rem;
	} /* 주위 글꼴의 1.1배 */
	.flex-container {
		display: flex;
	}
	
	.flex-container>div {
		background-color: #f1f1f1;
		margin: 10px;
		padding: 20px;
		font-size: 30px;
	}
	
	/* [st] button-18 */
          .button-18 {
            align-items: center;
            background-color: #FFA559; /* 버튼배경 색상 설정 */
            border: 0;
            box-sizing: border-box;
           /*  color: #6f726e; */
            color: #252525; /* 텍스트 색상 설정 */
            cursor: pointer;
            display: inline-flex;
            font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 20px;
            font-weight: 600;
            justify-content: center;
            line-height: 20px;
            width: 100%;
            min-height: 50px;
            min-width: 0px;
            overflow: hidden;
            padding: 0px;
            
            text-align: center;
            touch-action: manipulation;
            transition: background-color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, box-shadow 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s;
            user-select: none;
            -webkit-user-select: none;
            vertical-align: middle;
            border-radius: 25px; /* 모서리를 둥글게 만듦 */
          }

          .button-18:hover,
          .button-18:focus { 
            background-color: #FF6000;
            color: #252525;
            /* color: #ffffff; */
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
          .button-99 {
         	cursor: pointer;
         	font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 12px;
            font-weight: 600;
            color: #FFE6C7; /* 텍스트 색상 설정 */
            
          }
  		/* [ed] button-18 */
	
	#collapse {
		visibility: hidden;
	}
	
	</style>

</head>
<body
	background="http://localhost:5214/ApartTogether_01
	/image/background3.png">
<<<<<<< HEAD
	<div class="container">
		<h2 style="padding: 20px;">
			<b>아이디 찾기</b>
		</h2>
=======
	
	<div class="container" >
	
		<h2 class="mainTitle">아이디 찾기</h2>
>>>>>>> origin/member_merge_01_yh33
		<p>하기 정보를 입력해주세요.</p>
		

		<form action="<%=withFormTag%>" method="post" >
			<input type="hidden" name="command" value="meFindId">
			
			<div class="input-group">
				<span class="input-group-text col-md-3">이름</span> <input
					class="form-control" type="text" id="name" name="name"
					placeholder="이름을 입력해 주세요." data-bs-toggle="tooltip"
					title="등록하신 이름을 입력하세요" data-bs-placement="top">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">전화번호</span> <input
					class="form-control" type="text" id="phone" name="phone"
					placeholder="000-0000-0000">
			</div>
			<div class="input-group">
				<span class="input-group-text col-md-3">생일</span> <input
					class="form-control" type="datetime" id="birth" name="birth"
					placeholder="YYYY/MM/DD">
			</div>
			<div align="center">
				<button type="submit"
					class="btn button-18"  >ID 확인</button>
			</div>
			
			
		</form>
			
	</div>
</body>
</html>