<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	
	<div class="container">
		<h2> �� ��ٱ��� ��� ������</h2>
			<p> ������ ��ٱ��� ����� ���Է� �����ϱ��� ������</p>
			<table class="table table-hover">
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
						<c:forEach var="bean" items="${requestScope.wishList}">
							<tr>
								<td>
									<c:if test="${bean.name eq sessionScope.loginfo.name}">
										�� �ֹ�
									</c:if>
									<c:if test="${bean.name ne sessionScope.loginfo.name}">
										${bean.name}
									</c:if>
								</td>	
								
								<td>${bean.menuname}</td>
								<td>${bean.qty}</td>
								<td>${bean.price * bean.qty}</td>
								
							</tr>					
						</c:forEach>
					
				</tbody>
				
				
			</table>	
			<br/>
			
			<h2>��޺� : ${requestScope.wishList.fee}</h2>	 
		</div>	 
</body>
</html>