<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreList.css" type="text/css">

</head>
<body>
	<div class="container">
		<h2 class="mainTitle">우리 동네 맛집 리스트</h2>
		
		<c:if test="${sessionScope.loginfo ne null}">
			<p class="subTitle">내 현재 주소 | ${requestScope.myaddress}</p>
		</c:if>

		<table class="table table-borderless">
			<tbody> 
				<c:set var="colsu" value="3"/>
				<tr>
					<td colspan="${colsu}" align="center">
						<form name="myform" action="<%=withFormTag%>" method="get">
							<div class="whole-searchArea">
								<div class="searchArea-mystbtn">
									<div class="searchArea">
										<input type="hidden" name="command" value="stList">
										
										<select class="select-mode round-orange-hover" id="mode" name="mode">
											<option value="all" selected="selected">선택해 주세요
											<option value="stname">가게명
											<option value="category">카테고리
										</select>
										
										<select class="select-cate round-orange-hover" id="categoryList" name="categoryList">
											<option value="all" selected="selected">카테고리 리스트
											<option value="양식">양식
											<option value="중식">중식
											<option value="일식">일식
											<option value="한식">한식
											<option value="패스트푸드">패스트푸드
											<option value="치킨">치킨
											<option value="피자">피자
											<option value="카페">카페
										</select>
										
										<input class="select-keyword round-orange-hover" type="text" name="keyword" id="keyword" placeholder="키워드 입력">
										<button type="submit" class="normal_bigbtn">검색</button>
										
										<button type="button" class="else_bigbtn" onclick="searchAll();">전체 검색</button>
									</div>
									
									<c:if test="${sessionScope.loginfo.mtype eq 'biz'}">
										<button type="button" class="insert_bigbtn mystbtn" onclick="addStore();">내 가게 등록</button>
									</c:if>
								</div>
								<span class="label label-default">${requestScope.pageInfo.pagingStatus}</span>
							</div>
						</form>
					</td>
				</tr>
				
				<c:forEach var="storeList" items="${requestScope.storeList}" varStatus="status">
					<c:if test="${status.index mod colsu == 0}">
						<tr>
					</c:if>
					<td>
					<c:choose>
							<c:when test="${storeList.ststatus eq 'open'}">
								<div class="card hover-back"><!--  style="width: 19rem;" -->
							</c:when>		
							<c:when test="${storeList.ststatus eq 'close'}">
								<div class="card hover-back" style="opacity: 0.35;"><!--  width: 19rem; -->
							</c:when>
					</c:choose>
					
						
							<a class="removeUnderLine" href="<%=notWithFormTag%>stMuDetail&stno=${storeList.stno}">
								<img class="card-img-top" alt="${storeList.stname}" src="uploadStoreImage/${storeList.stlogo}">
								
								<div class="card-body">
									<!-- 카테고리 별 색상 부여 -->
									<c:choose>
										<c:when test="${storeList.category eq '양식'}">
											<span class="badge rounded-pill" style='background-color:#51CEA1;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '한식'}">
											<span class="badge rounded-pill" style='background-color:#72CCFF;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '중식'}">
											<span class="badge rounded-pill" style='background-color:#FF4E4E;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '일식'}">
											<span class="badge rounded-pill" style='background-color:#359EFF;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '피자'}">
											<span class="badge rounded-pill" style='background-color:#FFAB2E;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '치킨'}">
											<span class="badge rounded-pill" style='background-color:#FF9CBA;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '패스트푸드'}">
											<span class="badge rounded-pill" style='background-color:#FFCC15;'>
												${storeList.category}
											</span>
										</c:when>
										
										<c:when test="${storeList.category eq '카페'}">
											<span class="badge rounded-pill" style='background-color:#C18F61;'>
												${storeList.category}
											</span>
										</c:when>
									</c:choose>
									
									<h5 class="card-title text_dark">${storeList.stname}
											<c:if test="${storeList.ststatus eq 'close'}">
												<span class="badge rounded-pill" style='background-color:#ff0000;'>
													${storeList.ststatus}
												</span>
											</c:if>
									</h5>
									<input type="hidden" name="stno" value="${storeList.stno}">
									
									<p class="card-text text_dark">
										배달 시간 : ${storeList.btime}분 <br>
										배달팁 : <fmt:formatNumber value="${storeList.fee}" pattern="#,###"></fmt:formatNumber>원
									</p>
								</div> <!-- card-body -->
								
							</a>
						</div>
					</td>
					<c:if test="${status.index mod colsu == (colsu-1)}">
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
		${requestScope.pageInfo.pagingHtml}	
	</div>

</body>
<%@ include file="/common/footer.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	/*  페이징 - 가게명, 카테고리 mode 선택  */
	var modeOption = $('#mode option');
	
	for (var i=0; i<modeOption.length; i++) {
		if (modeOption[i].value == '${requestScope.pageInfo.mode}') {
			modeOption[i].selected = true;
			
			$('#categoryList').addClass('notShow');
			$('#keyword').removeClass('notShow');
			
			/*  검색했던 가게명 input에 값 유지  */
			$('#keyword').val('${requestScope.pageInfo.keyword}');
			$('#keyword').attr('disabled', false);
		}
	}
	
	/*  페이징 - 카테고리 리스트에 값 유지  */
	var cateOption = $('#categoryList option');
	
	for (var i=0; i<cateOption.length; i++) {
		if (cateOption[i].value == '${requestScope.pageInfo.category}') {
			cateOption[i].selected = true;
			
			$('#keyword').addClass('notShow');
			$('#categoryList').removeClass('notShow');
			$('#categoryList').attr('disabled', false);
		}
	}
	
	
	/*  검색 방법 바꿀 때  */
	$('#mode').change(function() {
		if ($(this).val() == 'all') {
			$('#keyword').val('');
			$('#keyword').attr('disabled', true);
			$('#categoryList').attr('disabled', true);
			
		/*  가게명으로 검색 시 카테고리 검색 드롭박스 안보이게  */
		} else if ($(this).val() == 'stname') {
			$('#keyword').val('');
			$('#categoryList').addClass('notShow');
			$('#keyword').removeClass('notShow');
			$('#keyword').attr('disabled', false);
		
		/*  카테고리로 검색 시 가게명 검색 input 박스 안보이게  */
		} else if ($(this).val() == 'category') {
			console.log('category change');
			
			$('#keyword').val('');
			$('#keyword').addClass('notShow');
			$('#categoryList').removeClass('notShow');
			$('#categoryList').attr('disabled', false);
		}
	});

	/*  화면 진입 시 셋팅  */
	if ($('#mode').val() == 'all' || $('#mode').val() == '') {
		$('#keyword').attr('disabled', true);
		$('#categoryList').attr('disabled', true);
		
 	/*	  가게명으로 검색 시 카테고리 검색 드롭박스 안보이게  */
	} else if ($('#mode').val() == 'stname') {
		$('#categoryList').addClass('notShow');
		$('#keyword').removeClass('notShow');
		$('#keyword').attr('disabled', false);
	
	/*  카테고리로 검색 시 가게명 검색 input 박스 안보이게  */
	} else if ($('#mode').val() == 'category') {
		console.log('category change');
		
		$('#keyword').addClass('notShow');
		$('#categoryList').removeClass('notShow');
		$('#categoryList').attr('disabled', false);
	}
});

/*  전체 선택 버튼 클릭  */
function searchAll() {
	location.href='<%=notWithFormTag%>stList';
}

/*  가게 등록 버튼 클릭  */
function addStore() {
	location.href='<%=notWithFormTag%>stInsert&id=${sessionScope.loginfo.id}';
}
</script>
</html>