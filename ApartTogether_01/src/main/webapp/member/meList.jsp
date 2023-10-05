<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:회원목록</title>
	<style type="text/css">
		.container{margin-top: 10px;}
		tr{opacity: 0.7;}
		.small_image{width:50px;height:50px;margin:2px;border-radius:5px;}
	</style>
	<style type="text/css">
		.container {margin-top:;}
		
		.input-group {
			margin: 7px;
			max-width: 1280px;
			min-width: 0px;
		}
		
		.input-group-text {
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
		
		#buttonset {
			margin-top: 15px;
		}
		
		.radio-inline {
			cursor: pointer;
			justify-content: center;
			margin-left: auto;
			margin-right: auto;
			font-size: 1.2em;
		}
		
		.form-check-input {
			cursor: pointer;
			justify-content: center;
			margin-left: auto;
			margin-right: auto;
			size: 19px;
		}
		
		.form-select {
			cursor: pointer;
			justify-content: center;
			margin-left: auto;
			margin-right: auto;
			size: 3px;
		}
		
		/* [st] button-18 */
		.button-18 {
			align-items: center;
			background-color: #d8e4d2;
			border: 0;
			box-sizing: border-box;
			color: #6f726e;
			cursor: pointer;
			display: inline-flex;
			font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto,
				"Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans",
				Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji",
				"Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
			font-size: 20px;
			font-weight: 600;
			justify-content: center;
			line-height: 20px;
			max-width: 1100px;
			min-height: 50px;
			min-width: 0px;
			overflow: hidden;
			padding: 0px;
			padding-left: 200px;
			padding-right: 200px;
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
		/* [ed] button-18 */
		.button-99 { /* 무색 투명 버튼 */
			cursor: pointer;
			font-family: -apple-system, system-ui, system-ui, "Segoe UI", Roboto,
				"Helvetica Neue", "Fira Sans", Ubuntu, Oxygen, "Oxygen Sans",
				Cantarell, "Droid Sans", "Apple Color Emoji", "Segoe UI Emoji",
				"Segoe UI Symbol", "Lucida Grande", Helvetica, Arial, sans-serif;
			font-size: 12px;
			font-weight: 600;
		}
</style>	
<script type="text/javascript">
		$(document).ready(function(){
			/* 상세검색을 한 후 selected option을 보존하여 출력한다. */
			var optionList = $('#mode option');
			for(var i=0; i < optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.mode}'){
					optionList[i].selected = true ;
					
				}
			}
			changeMode();
			/* 상세검색을 한 후 selected option을 보존하여 출력한다. */
			var optionList = $('#keywordmtype option');
			for(var i=0; i < optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.keywordmtype}'){
					optionList[i].selected = true ;
				}
			}/* 상세검색을 한 후 selected option을 보존하여 출력한다. */
			var optionList = $('#keywordgender option');
			for(var i=0; i < optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.keywordgender}'){
					optionList[i].selected = true ;
				}
			}
			/* 상세검색을 한 후 검색어(keyword)를 보존하여 출력한다. */
			$('#keyword').val('${requestScope.pageInfo.keyword}');

		});
		
		function changeMode() {
	        var modeDropdown = document.getElementById("mode");
	        var keywordmtype = document.getElementById("keywordmtype");
	        var keywordgender = document.getElementById("keywordgender");
	        var keyword = document.getElementById("keyword");
	        if (modeDropdown.value === "all"){ // 검색옵션 선택안함
	        	keywordmtype.style.display = "none";
	        	keywordgender.style.display = "none";
	        	keyword.style.display = "none";
	        }else if (modeDropdown.value === "mtype") { // 검색옵션: 회원유형
	        	keywordmtype.style.display = "inline";
	        	keywordgender.style.display = "none";
	        	keyword.style.display = "none";
	        } else if (modeDropdown.value === "gender") { // 검색옵션: 성별
	        	keywordmtype.style.display = "none";
	        	keywordgender.style.display = "inline";
	        	keyword.style.display = "none";
	        } else {                                      // 검색옵션: 아이디,이름,닉네임,주소
	        	keywordmtype.style.display = "none";
	        	keywordgender.style.display = "none";
	        	keyword.style.display = "inline";
	        	$('#keyword').focus(); // 바로 입력할 수 있도록 focus()
	        }
	    }
		
		
		/* 전체검색 버튼 selectAll() */
		function searchAll(){
			location.href = '<%=notWithFormTag%>meList' ;
		}
	</script>
</head>
<body>
	<div class="container">
		
		<%-- accessThisPage : 페이지 열람 가능 여부를 저장하는 변수입니다. 0(열람불가), 1(열람가능) --%>
		<c:set var="accessThisPage" value="0"/>
		<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
			<%-- 관리자 : 페이지를 열람할 수 있습니다. --%>
			<c:set var="accessThisPage" value="1"/>
		</c:if>
		
		
		<c:if test="${accessThisPage == 0 }">
			<%-- 열람불가 --%>
			!!!-비정상적인 접근입니다. 회원목록 페이지에 접근하실 수 없습니다..-!!!
		</c:if>
		<c:if test="${accessThisPage == 1 }">
			<%-- 열람가능 --%>
			<h2 class="mainTitle">회원 목록</h2>	
			
			
			<div class="row">
               		<div class="col-sm-12">               
		                <form name="myform" action="<%=withFormTag%>" method="get">
		                   <input type="hidden" name="command" value="meList">
		                   <div class="row">
		                      <div class="col-sm-12 mode" align="right">
		                      	
		                         <select class="form-control-sm" id="mode" name="mode" onchange="changeMode()">
		                            <option value="all" selected="selected">--- 검색옵션 ---
		                            <option value="mtype">회원유형
		                            <option value="id">아이디
		                            <option value="name">이름
		                            <option value="nickname">닉네임
		                            <option value="gender">성별
		                            <option value="address">주소
		                         </select>
		                         
		                         <select class="form-control-sm" id="keywordmtype" name="keywordmtype">
		                            <option value="all" selected="selected">--- 전체 ---
		                            <option value="user">일반회원
		                            <option value="biz">사업자
		                            <option value="admin">관리자
		                         </select>
		                         
		                         <select class="form-control-sm" id="keywordgender" name="keywordgender">
		                            <option value="all" selected="selected">--- 전체 ---
		                            <option value="male">남자
		                            <option value="female">여자
		                         </select>
		                        
		                         <input class="form-control-sm notShow" type="text" name="keyword" id="keyword"
		                         		placeholder="검색어 입력">
		                         <button type="submit" class="btn button-18 " style="padding: 7px; min-height: 0px" onclick="">검색</button>
		                         <button type="button" class="btn button-18 " style="padding: 7px; min-height: 0px" onclick="searchAll();">전체 검색</button>
		                         </div>  
		                   </div>
		                </form>                     
               		</div>
				</div>
			
			
			<table class="table table-hover" style="margin-top: 5px;">
				<thead class="table-dark">
					<tr>
						<th class=" col-md-1 text-center">회원유형</th>
						<th class=" col-md-1 text-center">아이디</th>
						<th class=" col-md-1 text-center">이름</th>
						<th class=" col-md-1 text-center">닉네임</th>
						<th class=" col-md-1 text-center">프로필사진</th>
						<th class=" col-md-1 text-center">비밀 번호</th>
						<th class=" col-md-1 text-center">성별</th>
						<th class=" col-md-1 text-center">전화번호</th>
						<th class=" col-md-1 text-center">생일</th>
						<th class=" col-md-1 text-center">주소</th>				
					</tr>
					
				</thead>
				<tbody>
					<tr>
						<td colspan="10" align="right">${requestScope.pageInfo.pagingStatus}</td>
					</tr>
					 
					<c:set var="cnt" value="0"/><%-- cnt는  --%>
					<c:forEach var="bean" items="${datalist}">
						<c:choose>
							<c:when test="${bean.mtype eq 'user'}">
								<tr class="table-primary">
							</c:when>
							<c:when test="${bean.mtype eq 'biz'}">
								<tr class="table-success">
							</c:when>
							<c:otherwise>
								<tr class="table-danger">
							</c:otherwise>
						</c:choose> 
					
						<td  class=" col-md-1 text-center">${bean.mtype}</td>
					
						<td class=" col-md-1 text-center">${bean.id}</td>
						
						<td class=" col-md-1 text-center">
							<a href="<%=notWithFormTag%>meDetail&id=${bean.id}">${bean.name}</a>
						</td>
						
						<td class=" col-md-1 text-center">${bean.nickname}</td>
						
						<td class=" col-md-1 text-center">
							<%-- profile가 null인 상태라면 기본이미지(default.jpg)를 보여줍니다. --%>
							<c:if test="${bean.profile == null}">
								<img class="card-img-top  small_image rounded" alt="기본이미지" 
								src="image/defaultProfile.jpeg"  >
							</c:if>
							<c:if test="${bean.profile != null}">
								<img class="card-img-top  small_image rounded" alt="${bean.profile}" 
						         src="uploadProfileImage/${bean.profile}"  >
							</c:if>
						</td>
						
						<td class=" col-md-1 text-center">${bean.password}</td>
						
						<td class=" col-md-1 text-center">
							<c:if test="${bean.gender eq 'male'}">남자</c:if>
							<c:if test="${bean.gender eq 'female'}">여자</c:if>	
							<c:if test="${bean.gender == null}">x</c:if>	
						</td>
						
						<td  class=" col-md-1 text-center">
							<c:if test="${bean.phone == null }">x</c:if>
							<c:if test="${bean.phone != null }">${bean.phone}</c:if>
						</td>
						<td  class=" col-md-1 text-center">
							<c:if test="${bean.birth == null }">x</c:if>
							<c:if test="${bean.birth == 'null' }">x</c:if>
							<c:if test="${bean.birth != 'null' }">${bean.birth}</c:if>
						</td>
						<td  class=" col-md-2 text-center">
							<c:if test="${bean.address == ' ' }">x</c:if>
							<%-- <c:if test="${bean.address != ' ' }">${bean.address}</c:if> --%>
							<c:if test="${bean.address != ' ' }">${requestScope.addressSetList[cnt]['firstPart']} ${requestScope.addressSetList[cnt]['secondPart']}</c:if>
						</td>					
					</tr>
					<c:set var="cnt" value="${cnt +1}"/><%-- cnt는 주소의 출력을 위한 변수입니다. --%>
					</c:forEach>
					
				</tbody>
			</table>	
			${requestScope.pageInfo.pagingHtml}
		</c:if>
		
		<div id="backButton">
			<button type="button"  class="btn button-18 "  style=" padding-left:20px; padding-right:20px" onclick="history.back();">
				돌아 가기
			</button>
		</div>
	
	
		
	</div>	 
</body>
</html>