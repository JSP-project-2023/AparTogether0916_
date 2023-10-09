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

<title>방 기본 정보 입력</title>
 <style>
 		
 		.container{margin-top: 50px;}
		h2{font-weight: bold;margin-bottom:2.5rem;}
		#subject{font-size: 25px; text-align: center; font-weight: bolder;}
		
           .bordered-div {
            border: 2px solid #ffa559; /* 테두리 두께와 색상을 지정합니다. */
            border-radius: 10px;
            padding: 30px; 
            margin-bottom: 8px;
            text-align: center; /* 내용을 가로로 가운데 정렬합니다. */
		    display: flex;
		    flex-direction: column; /* 내용을 수직으로 정렬합니다. */
		    align-items: center; /* 수직 정렬을 가운데로 설정합니다. */
		    justify-content: center; /* 수평 정렬을 가운데로 설정합니다. */
            }
</style>

</head>
<body>
	<div class = "container">
			<h2>주문 방 정보 입력</h2>
			<p> 방 제목, 배달 위치를 입력하는 페이지 입니다. </p>
			
			<div class="bordered-div">
				<form action="<%=withFormTag%>" method="post" name="form" id="form" method="post" >
					<input type="hidden" name="command" value="roDetail"> 
					<input type="hidden" name="stno" value = "${requestScope.stno}">
					
					<div id="list"></div>
					<div id="callBackDiv">
						<table>
							<tr>
								<td id = "subject" style="padding-top: 20px;"> 주소 &nbsp;&nbsp;&nbsp;</td>
								<td style="padding-top: 20px;"><input class="input_text" type="text"  onClick="goPopup();" style="width:400px;" id="roadFullAddr"  name="orderplace" placeholder="주소를 검색해주세요"/></td>
							</tr>
							<tr>
								<td id = "subject" style="padding-top: 20px;"> 방제목 &nbsp;&nbsp;&nbsp; </td>
								<td style="padding-top: 20px;"><input class="input_text" type="text" style="width:400px; id = "roomname" name="roomname"  placeholder="방 제목을 입력해주세요"></td>
							</tr>
							<tr>
							    <td id="subject" style="padding-top: 20px;">방 유지 시간</td>
							    <td style="padding-top: 20px;">
							        <input class="input_text" type="number" style="width: 400px;" id="pointtime" name="pointtime" placeholder="최대 60분까지 가능" min="0" max="60">
							    </td>
							</tr>
							
						</table>
					</div>
					<div style= "margin-top: 20px">
						<button type="submit" class="big_ctlbtn insert_bigbtn" >입력 완료</button>
					</div>
				</form>
			</div>
		</div>
		
</body>
<%@ include file="/common/footer.jsp"%>

</html>