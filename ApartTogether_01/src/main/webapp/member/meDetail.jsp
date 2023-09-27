<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:마이페이지</title>
	<style type="text/css">
		.container{margin:10px;}
		#backButton{margin: auto; }
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
	</style>
</head>
<body>
	<div class="container">
		<h2>${requestScope.bean.name}님의 회원 정보</h2>
		<table class="table">
			<thead></thead>
			<tbody>
			
			
				<tr>
					<td align="center">회원유형</td>
					<td>
						<c:choose>
							<c:when test="${requestScope.bean.mtype == 'user' }">
								<span>일반회원</span>
							</c:when>
							<c:when test="${requestScope.bean.mtype == 'biz' }">
								<span>사업자</span>
							</c:when>
							<c:when test="${requestScope.bean.mtype == 'admin' }">
								<span>관리자</span>
							</c:when>
							<c:otherwise>
								<span>멤버유형 정보가 올바르지 않습니다.(user, biz, admin)</span>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="center">아이디</td>
					<td>${requestScope.bean.id}</td>
				</tr>
				
				<tr>
					<td align="center">이름</td>
					<td>${requestScope.bean.name}</td>
				</tr>
				<tr>
					<td align="center">닉네임</td>
					<td>${requestScope.bean.nickname}</td>
				</tr>
				<tr>
					<td align="center">프로필 사진</td>
					<td>
						<img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
					         src="upload/${requestScope.bean.profile}"  >
					         
					</td>
				</tr>
				
				<tr>
					<td align="center">성별</td>
					<c:if test="${requestScope.bean.gender eq 'male'}">
						<td>남자</td>
					</c:if>
					<c:if test="${requestScope.bean.gender eq 'female'}">
						<td>여자</td>
					</c:if>	
				</tr>
				<tr>
					<td align="center">전화번호</td>
					<td>${requestScope.bean.phone}</td>
				</tr>
				<tr>
					<td align="center">생일</td>
					<td>${requestScope.bean.birth}</td>
				</tr>
				<tr>
					<td align="center">주소</td>
					<td>${requestScope.bean.address}</td>
				</tr>
			</tbody>
		</table>
		
		<div  align="center">
			<c:if test="${whologin ne 3}">	
				<!-- 일반회원(1),사업자(2)이면 비밀번호재설정, 정보수정, 탈퇴하기 버튼 보이기 -->	
				<!-- 관리자(3)이면 이 부분이 보이지 않습니다. -->		
				<%-- 이 줄은 삭제 예정입니다. <a type="button" href="<%=notWithFormTag%>meResetPassword&id=${bean.id}" class="btn btn-info">비밀번호재설정</a> --%>
				<a type="button" href="<%=notWithFormTag%>meUpdate&id=${bean.id}" class="btn btn-info">회원정보 수정</a>
				<a type="button" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}" class="btn btn-info">회원탈퇴하기</a>
			</c:if>	
		</div>
		
		<div id="backButton">
			<button type="button" class="btn btn-primary" onclick="history.back();">
				돌아 가기
			</button>
		</div>
			
	</div>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:마이페이지</title>
	<style type="text/css">
		.table{ }
		.tableHead{text-align: center; }
		#backButton{margin: auto; }
		.small_image{width:100px;height:100px;margin:2px;border-radius:5px;}
	</style>
  	<style type="text/css">
		.container {margin-top:50px;}
		
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
</head>
<body>
	<div class="container">
	
		<%-- accessMeDetail : 마이페이지 열람 가능 여부를 저장하는 변수입니다. 0(열람불가), 1(열람가능) --%>
		<c:set var="accessMeDetail" value="0"/>
		<c:if test="${sessionScope.loginfo.id != requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 다른 사람의 마이페이지를 열람할 수 없습니다. --%>
			<c:set var="accessMeDetail" value="0"/>
		</c:if>
		<c:if test="${sessionScope.loginfo.id == requestScope.bean.id}">
			<%-- 일반회원, 사업자 : 본인의 마이페이지만 열람할 수 있습니다. --%>
			<c:set var="accessMeDetail" value="1"/>
		</c:if>
		<c:if test="${sessionScope.loginfo.mtype == 'admin'}">
			<%-- 관리자 : 모든 회원의 마이페이지를 열람할 수 있습니다. --%>
			<c:set var="accessMeDetail" value="1"/>
		</c:if>
		
		<c:if test="${accessMeDetail == 0 }">
			<%-- 열람불가 --%>
			!!!-비정상적인 접근입니다. 다른 회원의 마이페이지는 열람하실 수 없습니다.-!!!
		</c:if>
		<c:if test="${accessMeDetail == 1 }">
			<%-- 열람가능 --%>
			<h2  class="mainTitle">${requestScope.bean.name}님의 회원 정보</h2>
			<table class="table" >
				<thead></thead>
				<tbody>
					<tr>
						<th align="center" class="tableHead">회원유형</th>
						<td>
							<c:choose>
								<c:when test="${requestScope.bean.mtype == 'user' }">
									<span>일반회원</span>
								</c:when>
								<c:when test="${requestScope.bean.mtype == 'biz' }">
									<span>사업자</span>
								</c:when>
								<c:when test="${requestScope.bean.mtype == 'admin' }">
									<span>관리자</span>
								</c:when>
								<c:otherwise>
									<span>멤버유형 정보가 올바르지 않습니다.(user, biz, admin)</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">아이디</th>
						<td>${requestScope.bean.id}</td>
					</tr>
					
					<tr>
						<th align="center"  class="tableHead">이름</th>
						<td>${requestScope.bean.name}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">닉네임</th>
						<td>${requestScope.bean.nickname}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">프로필 사진</th>
						<td>
							<%-- profile가 null인 상태라면 기본이미지(defaultProfile.jpeg)를 보여줍니다. --%>
							<c:if test="${requestScope.bean.profile == null}">
								<img class="card-img-top  small_image rounded" alt="기본이미지" 
								src="image/defaultProfile.jpeg"  >
							</c:if>
							<c:if test="${requestScope.bean.profile != null}">
								<img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  >
							</c:if>
							<%-- <img class="card-img-top  small_image rounded" alt="${requestScope.bean.profile}" 
						         src="uploadProfileImage/${requestScope.bean.profile}"  > --%>
						</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">성별</th>
						<c:if test="${requestScope.bean.gender eq 'male'}">
							<td>남자</td>
						</c:if>
						<c:if test="${requestScope.bean.gender eq 'female'}">
							<td>여자</td>
						</c:if>	
						<c:if test="${requestScope.bean.gender == 'null'}">
							<td>NULL</td>
						</c:if>	
					</tr>
					<tr>
						<th align="center"  class="tableHead">전화번호</th>
						<td>${requestScope.bean.phone}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead" >생일</th>
						<td >${requestScope.birthSet[0]}/${requestScope.birthSet[1]}/${requestScope.birthSet[2]}</td>
						<%-- <td >${requestScope.bean.birth}</td> --%>
					</tr>
					<tr>
						<th align="center"  class="tableHead">주소</th>
						<td>${requestScope.addressSet[0]}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">상세주소</th>
						<td>${requestScope.addressSet[1]}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">비밀번호 질문</th>
						<td>${requestScope.bean.passwordquest}</td>
					</tr>
					<tr>
						<th align="center"  class="tableHead">비밀번호 답변</th>
						<td>${requestScope.bean.passwordanswer}</td>
					</tr>
				</tbody>
			</table>
			
			
			<div  align="center">
				<c:if test="${whologin ne 1}">	
					<!-- 일반회원(3),사업자(2)에게만 정보수정, 탈퇴하기 버튼 보이기 -->
					<a type="button" href="<%=notWithFormTag%>meUpdate&id=${bean.id}" class="btn button-18 "  style=" padding-left:50px; padding-right:50px">회원정보 수정</a>
					<a type="button" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}" class="btn button-18 "  style=" padding-left:50px; padding-right:50px">회원탈퇴하기</a>
				</c:if>	
			</div>
		
			
		</c:if>
	

		
		<div id="backButton">
			<button type="button" class="btn button-18 "  style=" padding-left:20px; padding-right:20px" onclick="history.back();">
				돌아 가기
			</button>
		</div>
		
		
	
		
			
	</div>
</body>
>>>>>>> origin/member_merge03
</html>