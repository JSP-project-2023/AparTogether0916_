<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>   

<!DOCTYPE html>
<html>
<head>
<!-- 추후삭제 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>	
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreSalesCSS.css" type="text/css">
<script src="${pageContext.request.contextPath}/store/storeJS/StoreSales.js"></script>
<meta charset="UTF-8">
<title>가게 매출 내역</title>
</head>
	<body>
		<!-- 메뉴 별 판매량 시작 -->
		<div class="container-menu" id="menu-target">
			<span class="title">매출 현황</span>
			<div class="sale-header">
				<span class="title-2">${sessionScope.loginfo.name} 사장님의 ${requestScope.mystore.stname} 판매현황입니다.</span>
				<div class="button-area">
					<button class="btn-month buttons" onclick="show_month()">월별</button>
					<button class="btn-menu buttons" onclick="show_menu()">메뉴별</button>
				</div>
			</div>
<!-- 			<hr> -->
			<div class="sale-menu">
				<table>
					<thead>
						<tr class="menu-header">
							<td width="500px">메뉴명</td>
							<td width="200px" align="center">판매 누적 수량</td>
							<td width="200px" align="center">판매금액</td>
						</tr>
					</thead>
					<tbody>
					<!-- 메뉴 반복문 -->
					<c:set value="0" var="total_sales"/>
					<c:forEach var="bean" items="${requestScope.bean}">
						<tr class="one-menu">
							<td class="menu-name">${bean.menuname}</td>
							<td class="menu-qty" align="center">${bean.cumqty}개</td>
							<td class="menu-price" align="center"><fmt:formatNumber pattern="###,###,###" value="${bean.cumsale}"/>원</td>
						</tr>
						<c:set value="${total_sales = bean.cumsale + total_sales}" var="total_sales"/>
					<!-- 메뉴 반복문 -->
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="sale-footer">
				<span><strong>누적 판매 금액</strong> : <fmt:formatNumber pattern="###,###,###" value="${total_sales}"/><strong>원</strong></span>
			</div>
		</div>
		<!-- 메뉴 별 판매량 끝 -->		
		<!-- 월별 판매량 그래프 시작 -->
		<div class="container-month" id="month-target">
			<span class="title">매출 현황</span>
			<div class="sale-header">
				<span class="title-2">${sessionScope.loginfo.name} 사장님의 ${requestScope.mystore.stname} 판매현황입니다.</span>
				<div class="button-area">
					<button class="btn-month buttons" onclick="show_month()">월별</button>
					<button class="btn-menu buttons" onclick="show_menu()">메뉴별</button>
				</div>
			</div>
<!-- 			<hr> -->
			
			<!-- 1월부터 12월 데이터 -->
			<c:forEach var="sales" items="${requestScope.sales}" varStatus="status">
				<input type="hidden" name="${sales.key}" value="${sales.value}"/>
				<!-- 총 합계 -->
				<c:set var="thisyearSell" value="${thisyearSell = thisyearSell + sales.value}"/>
			</c:forEach>
			
			<!-- 그래프 영역 -->
			<div id="chart-area">
	  			<canvas id="myChart"></canvas>
			</div>
			<div class="sale-footer">
				<span><strong>올해 매출</strong> : <fmt:formatNumber pattern="###,###,###" value="${thisyearSell}"/><strong>원</strong></span>
			</div>
			<!-- 그래프 영역 끝-->
		</div>
		<!-- 월별 판매량 그래프 -->
	</body>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
	<script src="${pageContext.request.contextPath}/store/storeJS/StoreSalesChart.js"></script>
</html>