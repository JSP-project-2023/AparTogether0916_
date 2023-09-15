<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store List</title>
<script type="text/javascript">

	$(document).ready(function(){
		
		console.log('eedsdfsdf');
		
		$('#mode').change(function() {
			console.log('ee');
			
			
			
		});
		
	});
}


</script>


<style type="text/css">
	.container {
		margin-top: 50px;
		
	}
	
	.mainTitle {
		font-weight: bolder;
		margin-bottom: 2.5rem;
	}
	
	.setSpace {
		boarder-collapse: seperate;
		boarder-spacing: 0 30px;
	}
	
	.searchbar {
		margin: 20 auto;
		
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
	
	.card-text {
		
	}
	
	.buttonList {
		margin: 15px auto;
	}
	
</style>


</head>
<body>
	<div class="container">
		<h2 class="mainTitle">우리 동네 맛집 리스트</h2>
<%-- 		<p>현재 주소 | </p> --%>
		<table class="table table-borderless setSpace">
			<thead>
			</thead>
			<tbody> 
				<c:set var="colsu" value="3" />
				<tr>
					<td colspan="${colsu}" align="center">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<form name="myform" action="<%=withFormTag%>" method="get">
									<input type="hidden" name="command" value="storeList">
									<div class="row">
										<div class="col-sm-12">
											
											<select class="form-control-sm" id="mode" name="mode">
												<option value="all" selected="selected">--- 선택해 주세요 ---
												<option value="storename">가게명
												<option value="category">카테고리
											</select> 
											
											<select class="form-control-sm" id="categoryList" name="categoryList">
												<option value="none" selected="selected">--- 카테고리 리스트
													---
												<option value="western">양식
												<option value="chinese">중식
												<option value="japanese">일식
												<option value="korean">한식
												<option value="fastfood">패스트푸드
												<option value="chicken">치킨
												<option value="pizza">피자
												<option value="cafe">카페
											</select>
											
											<input class="form-control-sm" type="text" name="keyword" id="keyword" placeholder="키워드 입력">
											
											<button type="submit" class="btn btn-warning form-control-sm" onclick="">검색</button>
											
											<button type="button" class="btn btn-warning form-control-sm" onclick="searchAll();">전체 검색</button>
											
											<button type="button" class="btn btn-info form-control-sm" onclick="writeForm();">글 쓰기</button>
											
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
							<a class="removeUnderLine" href=""> <%-- <%=notWithFormTag%>storeDetail&stno=${storeList.stno}${requestScope.pageInfo.flowParameter} --%>
								<img class="card-img-top" alt="${storeList.stname}" src="upload/${storeList.stlogo}">
								
								<div class="card-body">
									
									<span class="badge rounded-pill bg-info">
										${storeList.category}
									</span>
									
									<h5 class="card-title text_dark">${storeList.stname}</h5>
									<p class="card-text text_dark">
										배달 시간 : ${storeList.btime}분 <br>
										배달팁 : ${storeList.fee}
									</p>
						<%--			<p class="card-text">
 										<span data-bs-toggle="popover" title="${storeList.stname}"
											data-bs-trigger="hover" data-bs-content="${storeList.content}">
											<c:if test="${fn:length(storeList.content) >= 10}">
											 	${fn:substring(storeList.content, 0, 10)}...
											</c:if>
										</span>

										<c:if test="${fn:length(storeList.content) < 10}">
										 	${storeList.content}
										</c:if> 
									</p>--%>
									
									
<%-- 									<c:if test="${whologin == 2}"> --%>
										<div id="buttonList" class="buttonList">
											<a id="updateAnchor" class="btn btn-outline-primary" href="<%=notWithFormTag%>storeUpdate&stno=${storeList.stno}${requestScope.pageInfo.flowParameter}">
												수정 
											</a>
											
											<a id="deleteAnchor" class="btn btn-outline-danger" href="#">
												삭제
											</a>
										</div>
<%-- 									</c:if> --%>
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