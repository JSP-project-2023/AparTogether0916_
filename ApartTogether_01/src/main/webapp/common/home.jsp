<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>아파투게더</title>
<style>
@import	url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css"); /* 스크롤 관련 */

.scroll::-webkit-scrollbar {
	display: none;
	scroll-behavior: smooth
}

.scroll-container, .scroll-area {
	max-width: 2560px;
	height: 700px;
	/* font-size: 60px; */
}

.scroll-container {
	overflow: auto;
	scroll-snap-type: y mandatory;
}

.scroll-area {
	scroll-snap-align: start;
}

.scroll-container, .scroll-area {
	margin: auto;
}

.scroll-area { /* 스크롤 내용 부분 css */
	display: flex;
	margin: 110px;
	flex-direction: column;
	flex-wrap: wrap;
	/* align-content: flex-start; */
	padding: 3rem;
	/* justify-content: flex-start; */
	color: black;
}
.item {max-height: 1080px;}
.scroll-container .item:nth-child(1) { flex-grow: 1; font-size: 2rem;}
.scroll-container .item:nth-child(2) { flex-grow: 3; font-size: 1rem; margin-left: 2rem;}
.scroll-container .item:nth-child(3) { flex-grow: 1; font-size: 2rem;}

.scroll-area:nth-of-type(1) {
	/* background: #49b293; */
	background-image: url(${pageContext.request.contextPath}/image/background3.png);
	background-size: cover;
}

.scroll-area:nth-of-type(2) {
	/* background: #c94e4b; */
	background-image: url(${pageContext.request.contextPath}/image/background3.png);
	background-size: cover;
}

.scroll-area:nth-of-type(3) {
	/* background: #4cc1be; */
	background-image: url(${pageContext.request.contextPath}/image/background3.png);
	background-size: cover;
}

.scroll-area:nth-of-type(4) {
	/* background: #8360A6; */
	background-image: url(${pageContext.request.contextPath}/image/background3.png);
	background-size: cover;
}

</style>
</head>
<body>
<<<<<<< HEAD
	<div class="support-scrollsnap"></div>

	<div class="scroll scroll-container">
		<div class="scroll-area">
			<div class="item">높아만 지는 <u>배달비</u> </br>어떻게 해야 할까요?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			<div class="item">배달하는 곳을 한곳으로!</br> apartogether와 함께 하세요!</div>
			<div class="item" align="center">예쁜그림 ㅎㅎ&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
		<div class="scroll-area">
			<div class="item"><del>혼자내는 배달비!</del><Strong><br/>배달비 / 사람수만큼 절약</Strong></div>
			<div class="item">함께하는 사람이 많을수록 더욱 싸게! 배달비 절감!</div>
			<div class="item">오토바이 굴러가는 이미지</div>
		</div>
		<div class="scroll-area">
			<div class="item">test1</div>
			<div class="item">test2</div>
			<div class="item">test3</div>
		</div>
		<div class="scroll-area">
			<div class="item">test1</div>
			<div class="item">test2</div>
			<div class="item">test3</div>
		</div>
	</div>
</body>
</html>