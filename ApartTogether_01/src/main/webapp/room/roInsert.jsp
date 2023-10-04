<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("popup/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.form.roadFullAddr.value = roadFullAddr;	
}

</script>

<title>Insert title here</title>
 <style>
 		
 		.container{margin-top: 50px;}
		h2{font-weight: bold;margin-bottom:2.5rem;}
		#subject{font-size: 20px; text-align: center;}
		.button-18 {
            align-items: center;
            background-color: #d8e4d2;
            border: 0;
            box-sizing: border-box;
            color: #6f726e;
            cursor: pointer;
            display: inline-flex;
            font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto, "Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans", Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 20px;
            font-weight: 600;
            justify-content: center;
            line-height: 20px;
            max-width: 900px;
            min-height: 50px;
            min-width: 0px;
            overflow: hidden;
            padding: 0px;
            padding-left: 190px;
            padding-right: 190px;
            text-align: center;
            touch-action: manipulation;
            transition: background-color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, box-shadow 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s, color 0.167s cubic-bezier(0.4, 0, 0.2, 1) 0s;
            user-select: none;
            -webkit-user-select: none;
            vertical-align: middle;
          }

          .button-18:hover,
          .button-18:focus { 
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
<body>
	<div class = "container">
		<div>
			<h2>주문 방 정보 입력</h2>
			<p> 방 제목, 배달 위치를 입력하는 페이지 입니다. </p>
			<form action="<%=withFormTag%>" method="post" name="form" id="form" method="post" >
				<input type="hidden" name="command" value="roDetail"> 
				<input type="hidden" name="stno" value = "${requestScope.stno}">
				
				<div id="list"></div>
				<div id="callBackDiv">
					<table>
						<tr>
							<td id = "subject" style="padding-top: 20px;"> 주소 &nbsp;&nbsp;&nbsp;</td>
							<td ><input type="text"  onClick="goPopup();" style="width:400px;" id="roadFullAddr"  name="orderplace" placeholder="주소를 검색해주세요"/></td>
						</tr>
						<tr>
							<td id = "subject" style="padding-top: 20px;"> 방제목 &nbsp;&nbsp;&nbsp; </td>
							<td style="padding-top: 20px;"><input type="text" style="width:400px; id = "roomname" name="roomname"  placeholder="방 제목을 입력해주세요"></td>
						</tr>
						
					</table>
				</div>
				<div style= "margin-top: 20px">
					<button type="submit" class="button-18" >입력 완료</button>
				</div>
			</form>
		</div>
		</div>
</body>
</html>