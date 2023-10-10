<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		.container{margin-top: 50px;}
		h2{font-weight: bold;margin-bottom:2.5rem;}
		span {font-size:1.2rem;}
		.dddmain_header{
		 background-color:LightGray;
		  border-top-left-radius: 2em 0.5em;
		  border-top-right-radius: 1em 3em;
		  border-bottom-right-radius: 4em 0.5em;
		  border-bottom-left-radius: 1em 3em;}
		  #red{color: red; font-weight: bold;}
		  #jul{text-decoration: line-through; color: red; font-weight: bold; }
		  #bold{font-weight: bold;}
	</style>
	
	<script>
        function goBack() {
            // 브라우저의 이전 페이지로 이동합니다.
            window.history.back();
        }
    </script>
</head>
<body>
	<div class="container">
		<div class="row">
    		<div class="col-sm-2"></div>
    		<div class="col-sm-8">
				<h2>
					${sessionScope.loginfo.name}(${sessionScope.loginfo.id})
					님의 주문 상세 내역
				</h2>
				<p>
					${sessionScope.loginfo.name} 고객님이
					구매하신 상품에 대한 세부 결제 내역입니다.
				</p>
				
				<br/>
				
				<table class="table">
					<thead>
						<tr>
							<th>메뉴 이름</th>
							<th>단가</th>
							<th>수량</th>
							<th>금액</th>
						</tr>
					</thead>
					<tbody>
						<%-- 변수 : totalAmount(총금액) --%>
						<c:set var="totalAmount" value="0"/>
						
						<c:forEach var="bean" items="${requestScope.lists}">
						<tr>
							<td valign="middle">${bean.menuname}</td>
							<td valign="middle">
								<fmt:formatNumber value="${bean.price}" pattern="###,###"/> 원						
							</td>
							<td valign="middle">
								<fmt:formatNumber value="${bean.qty}" pattern="###,###"/> 개						
							</td>
							<td valign="middle">
								<c:set var="amount" value="${bean.price*bean.qty}"/>
								<c:set var="totalAmount" value="${totalAmount + amount}"/>
								<fmt:formatNumber value="${amount}" pattern="###,###"/> 원						
							</td>					
						</tr>					
						</c:forEach>
						<tr>
							<td>배달료</td>
							<td id = "jul">${requestScope.fee}원</td>
							<td>/${requestScope.su}명</td>
							<td id = "red">${requestScope.suFee}원</td>
							<c:set var="totalAmount" value="${requestScope.suFee+totalAmount}"/>
						</tr>
						
						<tr>
							<td id = "bold">
								<span>배달료를 <span id="red">${requestScope.fee - requestScope.suFee}원</span> 아끼셨어요!!</span>
							</td>	
							<td colspan="4" align="right">
								합계 : <fmt:formatNumber value="${totalAmount}" pattern="###,###"/> 원&nbsp;&nbsp;
							</td>
						</tr>
							
					</tbody>
				</table>
				<div style="text-align: right">
					<button onclick="goBack()" class="big_ctlbtn cancle_bigbtn" style="width: 100px; height: 30px;">이전 화면</button>
				</div>
 		
    		</div>
    		<div class="col-sm-2"></div>
  		</div>		
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>

