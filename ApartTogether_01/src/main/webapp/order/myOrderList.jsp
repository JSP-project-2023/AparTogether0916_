<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		#liststart {margin-bottom: 2.5rem}
		h2{font-weight: bold;margin-bottom:2.5rem;}
		.container{margin-top: 50px;}
	</style>
</head>
<body>
	<div class="container">
		<h2>주문 내역</h2>
		<p id="liststart">개인이 주문했던 내역을 보여 주는 페이지입니다.</p>		
		<table class="table table-hover">
			<thead class="table-danger">
				<tr align="center">
					<th>주문 일자</th>
					<th>가게 이름</th>
					<th>상세 보기</th>		
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${requestScope.orderList}">
				<tr style="text-align: center;">
					<form name="myform" action="<%=withFormTag%>" method="get">
						<input type="hidden" name="command" value="myorList">
					</form>		
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