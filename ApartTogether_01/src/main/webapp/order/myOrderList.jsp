<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<h2>주문 내역</h2>
		<p>개인이 주문했던 내역을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover">
			<thead class="table-dark">
				<tr>
					<th>주문 일자</th>
					<th>가게 이름</th>
					<th>상세 보기</th>		
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="9" align="center">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">					
								<form name="myform" action="<%=withFormTag%>" method="get">
									<input type="hidden" name="command" value="myorList">
								</form>							
							</div>
							<div class="col-sm-1"></div>
						</div>
					</td>
				</tr>
				<c:forEach var="bean" items="${requestScope.orderList}">
				<tr>
					<td>${bean.ordertime}</td>
					<td>${bean.stlogo}<br/>${bean.stname}</td>
					<td><a href="<%=notWithFormTag%>myOrDetail&roomno=${bean.roomno}">상세 보기</a></td>
					
				</tr>					
				</c:forEach>
		
			</tbody>
			
			
		</table>	
		${requestScope.pageInfo.pagingHtml} 	 
	</div>	 
</body>
</html>