<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>현재 진행중인 주문 목록</title>
	<style type="text/css">
		.container{margin-top: 50px;}
		
		.custom_red{background-color: red;}
		.custom_brown{background-color: brown;}
		.custom_orange{background-color: orange;}
		.custom_grey{background-color: grey;}
		.custom_green{background-color: green;}
		.custom_skyblue{background-color: skyblue;}
		.custom_blue{background-color: blue;}
		.custom_puple{background-color: #9900ff;}
		#hidden{display: none;}
		#liststart {margin-bottom: 2.5rem}
		h2{font-weight: bold;margin-bottom:2.5rem;}
		
		.orange {
        background-color: #FFA559 !important; /* 원하는 배경색으로 설정 */
    	}
		
	</style>	
	
	<script type="text/javascript">
		$(document).ready(function(){
			/* 모드 검색기록 남겨두기 */
			var optionList = $('#mode option');
			for(var i=0 ; i<optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.mode}'){
					optionList[i].selected = true ;
				}	
			}
			
			/* 키워드 검색 기록 남겨두기 */
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
<body>
	<div class="container">
		<h2>현재 진행중인 주문 목록</h2>
		<p id = "liststart">현재 진행중인 주문 목록을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover" style="text-align: center;">
			<thead>
				<tr>
					<th>주문 방 번호</th>
					<th>카테고리</th>
					<th>가게 이름</th>
					<th>주문 장소</th>
					<th>제목</th>
					<th>주문 시간</th>
					<th>참가자 수</th>		
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
												<option value="all" selected="selected">선택해 주세요
												<option value="category">카테고리
												<option value="stname">가게명
												<option value="orderplace">주문장소
												
												
											</select>
											<input class="form-control-sm" type="text" 
												name="keyword" id="keyword" placeholder="키워드 입력">
											<button type="submit" class="big_ctlbtn insert_bigbtn" style="width: 50px; height: 30px;" onclick="">검색</button>
											<button type="button" class="big_ctlbtn insert_bigbtn" style="width: 85px; height: 30px;" onclick="searchAll();">전체 검색</button>
											<span class="label label-default">${requestScope.pageInfo.pagingStatus}</span>
										</div>
									</div>
								</form>							
							</div>
							<div class="col-sm-1"></div>
						</div>
					</td>
				</tr>
				<c:set var="success" value="success"/>
				<c:forEach var="bean" items="${requestScope.roomlist}">	
					
				
								
								<tr>
									<td>${bean.roomno}</td>
									<td>
									<!-- 카테고리 별 뱃지 색깔을 다르게 하기 위한 if 문 -->
									<c:choose>
										<c:when test="${bean.category eq '양식'}">
											<span class="badge rounded-pill" style='background-color:#51CEA1;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '한식'}">
											<span class="badge rounded-pill" style='background-color:#72CCFF;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '중식'}">
											<span class="badge rounded-pill" style='background-color:#FF4E4E;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '일식'}">
											<span class="badge rounded-pill" style='background-color:#359EFF;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '피자'}">
											<span class="badge rounded-pill" style='background-color:#FFAB2E;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '치킨'}">
											<span class="badge rounded-pill" style='background-color:#FF9CBA;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '패스트푸드'}">
											<span class="badge rounded-pill" style='background-color:#FFCC15;'>
												${bean.category}
											</span>
										</c:when>
										
										<c:when test="${bean.category eq '카페'}">
											<span class="badge rounded-pill" style='background-color:#C18F61;'>
												${bean.category}
											</span>
										</c:when>
									</c:choose>
									</td>
									<td>${bean.stname}</td>
									<td><a href="<%=notWithFormTag%>roDetail&roomno=${bean.roomno}">${bean.place}</a></td>
									<td><a href="<%=notWithFormTag%>roDetail&roomno=${bean.roomno}">${bean.roomname}</a></td>
									<td>${bean.ordertime}</td>	
									<td>${bean.row_count }</td>	
								</tr>		
						
				</c:forEach>
			</tbody>
			
			
		</table>	
		${requestScope.pageInfo.pagingHtml} 	 
	</div>	 
	<%@ include file="/common/footer.jsp"%>
</body>
</html>