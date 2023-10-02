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
	var pop = window.open("/popup/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
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
 		#container{margin-left: 25%; margin-right: 25% }
</style>

</head>
<body>
	<div id = "container">
		<div>
			<h2>주문 방 정보 입력</h2>
			<p> 방 제목, 배달 위치를 입력하는 페이지 입니다. </p>
			<form action="<%=withFormTag%>" method="post" name="form" id="form" method="post">
				<input type="hidden" name="command" value="roDetail"> 
				<input type="hidden" name="stno" value = "${requestScope.stno}">
				<input type="text" id = "roomname" name="roomname" placeholder="방 제목을 입력해주세요">
				<br/>
				<input type="button" onClick="goPopup();" value="주소 검색하기"/>
				<div id="list"></div>
				<div id="callBackDiv">
					<table>
						<tr><td>도로명주소 전체(포멧)</td><td><input type="text"  style="width:500px;" id="roadFullAddr"  name="orderplace" /></td></tr>
					</table>
				</div>
				
				<button type="submit" >입력 완료</button>
			</form>
		</div>
		</div>
</body>
</html>