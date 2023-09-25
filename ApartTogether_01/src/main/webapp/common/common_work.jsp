<%--  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<header id="margin">
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<a class="navbar-logo" href="#">로고 이미지</a> <a
					class=" navbar-brand bigsize" href="<%=notWithFormTag%>home">APTogether</a>
				
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						
						<!-- Store section -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">가게</a>
							
							<ul class="dropdown-menu">
								<li>
									<a class="dropdown-item"href="<%=notWithFormTag%>stList">가게 목록</a>
								</li>

								<c:if test="${whologin eq 2}">
									<li>
										<a class="dropdown-item" href="<%=notWithFormTag%>myStoreList&id=${sessionScope.loginfo.id}">내 가게 관리</a>
									</li>
									<li>
										<a class="dropdown-item" href="<%=notWithFormTag%>menuManage">메뉴 관리</a>
									</li>
								</c:if>
							</ul>
						</li>
						
						<!-- Order section -->
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">주문</a>
							<ul class="dropdown-menu">
								<c:if test="${whologin ne 0}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>myorList">주문내역(href : null)</a></li>
								</c:if>
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>roList">모집 중인 주문(href : null)</a></li>
							</ul></li>

						<!-- Event section -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">이벤트</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>prList">이벤트(href : null)</a></li>
								<c:if test="${whologin eq 1}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>prInsert">이벤트 등록(href : null)</a></li>
								</c:if>
							</ul></li>

						<!-- Community section -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown">커뮤니티</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="<%=notWithFormTag%>vwList">투표(href : null)</a></li>
								<c:if test="${whologin eq 1}">
									<li><a class="dropdown-item"
										href="<%=notWithFormTag%>prInsert">투표 등록(href : null)</a></li>
								</c:if>
							</ul></li>
							
						<!-- [st] 로그인 섹션 -->							
						<c:if test="${whologin eq null }">
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogin">로그인</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meInsert">회원 가입</a></li>

						</c:if>
						
						<c:if test="${whologin eq 1 }">
							<li class="tips"><a class="small-tip">주인님 !</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meList">회원 목록</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
						
						<c:if test="${whologin eq 2 }">
							<li class="tips"><a class="small-tip">${sessionScope.loginfo.name}
									사장님 </a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meDetail">마이페이지</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
						<c:if test="${whologin eq 3 }">
							<li class="tips"><a class="small-tip">${sessionScope.loginfo.name}님
							</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meDetail">마이페이지</a></li>
							<li class="tips"><a class="small-tip"
								href="<%=notWithFormTag%>meLogout">로그아웃</a></li>
						</c:if>
						<!-- [ed] 로그인 섹션 -->
					</ul>
				</div>
			</div>
		</nav>
		<hr id="small" />
	</header>






</body>
</html>--%>