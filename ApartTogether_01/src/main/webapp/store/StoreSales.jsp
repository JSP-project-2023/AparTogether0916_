<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreSalesCSS.css" type="text/css">

<meta charset="UTF-8">
<title>가게 매출 내역</title>
</head>
	<body>
		<!-- 메뉴 별 판매량 -->
		<div class="container-menu">
			<span class="title">판매 현황</span>
			<div class="sale-header">
				<span class="title-2">김이박최 사장님의 파스타집 판매현황입니다.</span>
				<div class="button-area">
					<button id="btn1" class="buttons">월별</button>
					<button id="btn2 " class="buttons">메뉴별</button>
				</div>
			</div>
			<hr>
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
						<tr class="one-menu">
							<td class="menu-name">로제 파스타</td>
							<td class="menu-qty" align="center">2개</td>
							<td class="menu-price" align="center">15,000원</td>
						</tr>
					<!-- 메뉴 반복문 -->
					
					</tbody>
				</table>
			</div>
			
			<div class="sale-footer">
				<span><strong>총 판매금액</strong> : 5,5000<strong>원</strong></span>
			</div>
		</div>
				
		<!-- 월별 판매량 그래프 -->
		<div class="container-month">
			<span class="title">판매 현황</span>
			<div class="sale-header">
				<span class="title-2">김이박최 사장님의 파스타집 판매현황입니다.</span>
				<div class="button-area">
					<button id="btn1" class="buttons">월별</button>
					<button id="btn2" class="buttons">메뉴별</button>
				</div>
			</div>
			<hr>
			<!-- 그래프 영역 -->
			<div>
	  			<canvas id="myChart"></canvas>
			</div>
		</div>
	</body>
	<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
	<script src="${pageContext.request.contextPath}/store/storeJS/StoreSalesChart.js"></script>
</html>