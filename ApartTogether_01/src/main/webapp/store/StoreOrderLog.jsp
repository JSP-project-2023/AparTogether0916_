<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>   

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/store/storeCSS/StoreOrderLog.css" type="text/css">
<meta charset="UTF-8">
<title>가게 주문 내역</title>
</head>
	<body>
		<div class="container">
			<!-- 주문 내역 시작 -->
			<div class="container-menu" id="menu-target">
				<h2 class="mainTitle">${requestScope.mystore.stname} 주문내역</h2>
				<p class="subTitle">${sessionScope.loginfo.name} 사장님의 가게 주문내역입니다.</p>
	<!-- 			<hr> -->
				<div class="sale-menu">
					<table>
						<thead>
							<tr class="menu-header">
								<td width="200px" align="center">주문순서</td>
								<td width="200px" align="center">메뉴명</td>
								<td width="200px" align="center">배달지</td>
								<td width="200px" align="center">주문일자</td>
								<td width="200px" align="center">수량</td>
								<td width="200px" align="center">판매금액</td>
							</tr>
						</thead>
						<tbody>
						<!-- 주문목록 반복문 -->
						<c:forEach var="bean" items="${requestScope.bean}">
							<tr class="one-menu">
								<td align="center">${bean.orderno}</td>
								<td align="center">${bean.menuname}</td>
								<!-- 문자 길이 8이상이면 툴팁 -->
								<td data-bs-toggle="tooltip" title="${bean.addr}" data-bs-trigger="hover" align="center">
									<c:if test="${fn:length(bean.addr) >= 8}">
										${fn:substring(bean.addr, 0, 7)}...
									</c:if>
									<c:if test="${fn:length(bean.addr) < 8}"> <!-- 8자 이하일 경우 -->
										${bean.addr}
									</c:if>
								</td>
								<td align="center">${bean.ordertime}</td>
								<td align="center">${bean.qty}</td>
								<td align="center">${bean.sellprice}원</td>
							</tr>
						<!-- 주문 목록 반복문 -->
						</c:forEach>
						</tbody>
					</table>
					<div id="paging">
						${requestScope.pageInfo.pagingHtml}
					</div>
				</div>
			</div>
		</div>
		<!-- 주문내역 끝 -->
		<%@ include file="/common/footer.jsp"%>
		<!-- 툴팁 스크립트 -->
		<script src="${pageContext.request.contextPath}/store/storeJS/StoreOrderLogJS.js"></script>
	</body>
</html>