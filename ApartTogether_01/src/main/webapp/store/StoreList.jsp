<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 목록</title>
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

<style type="text/css">
	.container {
		margin-top: 50px;
		
	}
	
	.mainTitle {
		font-weight: bolder;
	}
	
	.myAddress {
		margin-bottom: 2.5rem;
	}

	.searchbar {
		margin: 20 auto;
		
	}
	
	.notShow {
		display: none;
	}
	
	a {
		text-decoration: none;
	}
	
	.card-img-top {
		width: 300px;
		height: 300px;
	}
	
	.text_dark {
		color: #484848;
	}
	
	.card-title {
		font-weight: 600;
		margin: 10px auto;
	}
	
	.buttonList {
		margin: 15px auto;
	}
</style>


</head>
<body>
	<div class="container">
		<h2 class="mainTitle">우리 동네 맛집 리스트</h2>
		
		<c:if test="${sessionScope.loginfo ne null}">
			<p class="myAddress">내 현재 주소 | ${sessionScope.loginfo.address}</p>
		</c:if>

		<table class="table table-borderless">
			<tbody> 
				<c:set var="colsu" value="3"/>
				<tr>
					<td colspan="${colsu}" align="center">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<form name="myform" action="<%=withFormTag%>" method="get">
									<input type="hidden" name="command" value="stList">
									<div class="row">
										<div class="col-sm-12">
											
											<select class="form-control-sm" id="mode" name="mode">
												<option value="all" selected="selected">--- 선택해 주세요 ---
												<option value="stname">가게명
												<option value="category">카테고리
											</select> 
											
											<select class="form-control-sm" id="categoryList" name="categoryList">
												<option value="all" selected="selected">--- 카테고리 리스트
													---
												<option value="양식">양식
												<option value="중식">중식
												<option value="일식">일식
												<option value="한식">한식
												<option value="패스트푸드">패스트푸드
												<option value="치킨">치킨
												<option value="피자">피자
												<option value="카페">카페
											</select>
											
											<input class="form-control-sm" type="text" name="keyword" id="keyword" placeholder="키워드 입력">
											<button type="submit" class="btn btn-warning form-control-sm">검색</button>
											
											<button type="button" class="btn btn-warning form-control-sm" onclick="searchAll();">전체 검색</button>
											
											<c:if test="${sessionScope.loginfo.mtype eq 'biz'}">
												<button type="button" class="btn btn-info form-control-sm" onclick="addStore();">내 가게 등록</button>
											</c:if>
											
											<span class="label label-default">${requestScope.pageInfo.pagingStatus}</span>
										</div>
									</div>
								</form>
							</div>
							<div class="col-sm-1"></div>
						</div>
					</td>
				</tr>
				
				<c:forEach var="storeList" items="${requestScope.storeList}" varStatus="status">
					<c:if test="${status.index mod colsu == 0}">
						<tr>
					</c:if>
					<td>
						<div class="card" style="width: 19rem;">
							<a class="removeUnderLine" href="<%=notWithFormTag%>stMuDetail&stno=${storeList.stno}">
								<img class="card-img-top" alt="${storeList.stname}" src="upload/${storeList.stlogo}">
								
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
									
									<h5 class="card-title text_dark">${storeList.stname}</h5>
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
</html>