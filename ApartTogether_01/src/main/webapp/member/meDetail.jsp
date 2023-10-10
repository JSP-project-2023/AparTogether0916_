<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:마이페이지</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/member/memberCSS/meDetail.css" type="text/css">
	
	<script type="text/javascript">
		function meDeleteCheck(){
			var mtype = "${requestScope.bean.mtype}";
			var returnValue1 = confirm("정말로 탈퇴하시겠습니까?");
			if(returnValue1 == true){
				if(mtype == "biz") { 
					var returnValue2 = confirm("등록하신 가게의 정보가 모두 사라집니다. \n탈퇴하시겠습니까?");
					if(returnValue2 == true){
						$('#meDeleteCheck').val("yes");
						alert("다음에 또 만나요~");
					}else{
						$('#meDeleteCheck').val("no");
						return false;
					}
				}
				if(mtype == "user") { 
					alert("다음에 또 만나요~");
				} 
			}else{
				return false;
			}
		}
	</script>
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
			
			<input type="hidden" id="meDeleteCheck" name="meDeleteCheck" value="no">
			<div class="main-contents">
				<table class="table"  >
					<thead></thead>
					<tbody>
						<tr >
							<th class="tableHead">회원유형</th>
							<td class="tableBody">
								<c:choose >
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
									<img class="card-img-top  small_image rounded-circle" alt="기본이미지" 
									src="image/defaultProfile.jpeg"  >
								</c:if>
								<c:if test="${requestScope.bean.profile != null}">
									<img class="card-img-top  small_image rounded-circle" alt="${requestScope.bean.profile}" 
							         src="uploadProfileImage/${requestScope.bean.profile}">
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
			</div>
			
			<div class="btnArea">
				<c:if test="${whologin ne 1}">
					<!-- 일반회원(3),사업자(2)에게만 정보수정, 탈퇴하기 버튼 보이기 -->
					<a type="button" href="<%=notWithFormTag%>meUpdate&id=${bean.id}" class="big_ctlbtn update_bigbtn">회원정보 수정</a>
					<a type="button" href="<%=notWithFormTag%>meDelete&id=${sessionScope.loginfo.id}" 
						class="big_ctlbtn delete_bigbtn" onclick="return meDeleteCheck();">회원탈퇴하기</a>
					<button type="button" class="big_ctlbtn cancle_bigbtn" onclick="history.back();">
						돌아 가기
					</button>
				</c:if>	
			</div>
		</c:if>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>