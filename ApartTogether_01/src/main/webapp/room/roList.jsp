<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.container{margin-top: 10px;}
		tr{opacity: 0.7;}
		.custom_red{background-color: red;}
		.custom_brown{background-color: brown;}
		.custom_orange{background-color: orange;}
		.custom_grey{background-color: grey;}
		.custom_green{background-color: green;}
		.custom_skyblue{background-color: skyblue;}
		.custom_blue{background-color: blue;}
		.custom_puple{background-color: #9900ff;}
		
	</style>	
	
	<script type="text/javascript">
		$(document).ready(function(){
			var optionList = $('#mode option');
			for(var i=0 ; i<optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.mode}'){
					optionList[i].selected = true ;
				}	
			}
			
			$('#keyword').val('${requestScope.pageInfo.keyword}');
			
			$("#mode").change(function(){				 
				  if($(this).val() != 'all'){
					  $('#keyword').attr('disabled', false);
				  }else{
					  $('#keyword').val('');
					  $('#keyword').attr('disabled', true);
				  }
			});			
		});
		
		function searchAll(){ /* 전체 검색 */
			location.href = '<%=notWithFormTag%>roList';
		}
		
		
	</script>
</head>
<body >
	<div class="container">
		<h2>현재 진행중인 주문 목록</h2>
		<p>현재 진행중인 주문 목록을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover" style="text-align: center;">
			<thead class="table-dark">
				<tr>
					<th>주문 방 번호</th>
					<th>카테고리</th>
					<th>가게 이름</th>
					<th>주문 장소</th>
					<th>제목</th>
					<th>주문 시간</th>		
				
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9" align="center">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">					
								<form name="myform" action="<%=withFormTag%>" method="get">
									<input type="hidden" name="command" value="roList">
									<div class="row">
										<div class="col-sm-12">
											<select class="form-control-sm" id="mode" name="mode">
												<option value="all" selected="selected">--- 선택해 주세요 ---
												<option value="category">카테고리
												<option value="stname">가게이름
												<option value="place">주문장소
												
												
											</select>
											<input class="form-control-sm" type="text" 
												name="keyword" id="keyword" placeholder="키워드 입력">
											<button type="submit" class="btn btn-warning form-control-sm" onclick="">검색</button>
											<button type="button" class="btn btn-warning form-control-sm" onclick="searchAll();">전체 검색</button>
											<span class="label label-default">${requestScope.pageInfo.pagingStatus}</span>
										</div>
									</div>
								</form>							
							</div>
							<div class="col-sm-1"></div>
						</div>
					</td>
				</tr>
				<c:forEach var="bean" items="${requestScope.roomlist}">	
				<tr>
						<td>${bean.roomno}</td>
						
						<c:if test="${bean.category eq '양식' }">
							<td><span class="badge rounded-pill custom_red"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '중식' }">
							<td><span class="badge rounded-pill custom_grey"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '일식' }">
							<td><span class="badge rounded-pill custom_orange"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '한식' }">
							<td><span class="badge rounded-pill custom_green"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '패스트푸드' }">
							<td><span class="badge rounded-pill custom_skyblue"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '치킨' }">
							<td><span class="badge rounded-pill custom_blue"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '피자' }">
							<td><span class="badge rounded-pill custom_puple"> ${bean.category} </span></td>
						</c:if>
						<c:if test="${bean.category eq '카페' }">
							<td><span class="badge rounded-pill custom_brown"> ${bean.category} </span></td>
						</c:if>
						<td>${bean.stname}</td>
						<td>${bean.place}</td>
						<td><a href="<%=notWithFormTag%>roDetail&roomno=${bean.roomno}">${bean.roomname}</a></td>
						<td>${bean.ordertime}</td>		
				</tr>
				</c:forEach>
			</tbody>
			
			
		</table>	
		${requestScope.pageInfo.pagingHtml} 	 
	</div>	 
</body>
</html>