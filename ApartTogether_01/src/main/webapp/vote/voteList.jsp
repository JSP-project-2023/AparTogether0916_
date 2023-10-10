<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아파투게더:투표리스트</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/vote/voteCSS/voteList.css" type="text/css">
</head>
<body>
	<div class="container">
		<h2 class="mainTitle">투표</h2>
		
		<div class="row">
       		<div class="col-sm-12">               
               <form name="myform" action="<%=withFormTag%>" method="get">
                  <input type="hidden" name="command" value="voteList">
                  <div class="row">
                     <div class="col-sm-12 mode" align="right">
                        <select class="form-control-sm mySelect" id="mode" name="mode" onchange="changeMode()">
                           <option value="all" selected="selected">--- 검색옵션 ---
                           <option value="votetitle">제목
                           <option value="voteid">작성자 아이디
                           <option value="nickname">작성자 닉네임
                           <option value="endvote">마감여부
                        </select>
                        
                        <select class="form-control-sm mySelect" id="keywordEndVote" name="keywordEndVote">
                           <option value="all" selected="selected">--- 전체 ---
                           <option value="0">진행중
                           <option value="1">마감
                        </select>
                       
                        <input class="form-control-sm mySelect" type="text" name="keyword" id="keyword"
                        		placeholder="검색어 입력">
                        <button type="submit" class="btn button-18 " style="padding: 7px 20px; min-height: 0px" onclick="">검색</button>
                        <button type="button" class="btn button-18 " style="padding: 7px 25px; min-height: 0px" onclick="searchAll();">전체 검색</button>
                      </div>  
                  </div>
               </form>                     
       		</div>
		</div>
		
		<table class="table table-hover" style="margin-top: 5px;">
			<thead class="">
				<tr>
					<th class=" col-md-1 text-center">글번호</th>
					<th class=" col-md-1 text-center">제목</th>
					<th class=" col-md-1 text-center">작성자</th>
					<th class=" col-md-1 text-center">마감여부</th>		
				</tr>
				
			</thead>
			<tbody>
				<tr>
					<td colspan="10" align="right">${requestScope.pageInfo.pagingStatus}</td>
				</tr>
				 
				<c:forEach var="bean" items="${datalist}">
					<tr>
						<td  class=" col-md-1 text-center">${bean.voteno}</td>
						
						<td class=" col-md-1 text-center">
							<a href="javascript:void(0)" onClick="gotoDetail('${bean.voteno}', '${bean.voteid}', '${sessionScope.loginfo.id}')">${bean.votetitle}</a>
							<%--<a href="<%=notWithFormTag%>voteDetail&id=${bean.voteno}">${bean.votetitle}</a> --%>
						</td>
						
						<td class=" col-md-1 text-center">
							<c:if test="${bean.voteid == null}">-</c:if><%-- 작성자가 탈퇴해서 null이면 X로 표시 --%>
							<%-- <c:if test="${bean.voteid != null}">${bean.votenickname}(${bean.voteid})</c:if> --%>
							<c:if test="${bean.voteid != null}">${requestScope.idnickmap.get(bean.voteid)}(${bean.voteid})</c:if>
						</td>
						
						<td class=" col-md-1 text-center">
							<c:if test="${bean.endvote == '1'}"><span class="badge rounded-pill bg-magam">마감</span></c:if>
							<c:if test="${bean.endvote == '0'}"><span class="badge rounded-pill bg-jinheng">진행중</span></c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>	
		${requestScope.pageInfo.pagingHtml}
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>

<script type="text/javascript">
		$(document).ready(function(){
			/* 상세검색을 한 후 selected option을 보존하여 출력한다. */
			var optionList = $('#mode option');
			for(var i=0; i < optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.mode}'){
					optionList[i].selected = true ;
					
				}
			}
			changeMode();
			/* 상세검색을 한 후 selected option을 보존하여 출력한다. */
			var optionList = $('#keywordEndVote option');
			for(var i=0; i < optionList.length ; i++){
				if(optionList[i].value == '${requestScope.pageInfo.keywordEndVote}'){
					optionList[i].selected = true ;
				}
			}
			/* 상세검색을 한 후 검색어(keyword)를 보존하여 출력한다. */
			$('#keyword').val('${requestScope.pageInfo.keyword}');

		});
		
		function changeMode() {
	        var modeDropdown = document.getElementById("mode");
	        var keywordEndVote = document.getElementById("keywordEndVote");
	        var keyword = document.getElementById("keyword");
	        if (modeDropdown.value === "all"){ // 검색옵션 선택안함
	        	keywordEndVote.style.display = "none";
	        	keyword.style.display = "none";
	        }else if (modeDropdown.value === "votetitle") { // 검색옵션: 제목
	        	keywordEndVote.style.display = "none";
	        	keyword.style.display = "inline";
	        	$('#keyword').focus(); // 바로 입력할 수 있도록 focus()
	        } else if (modeDropdown.value === "endvote") { // 검색옵션: 마감여부
	        	keywordEndVote.style.display = "inline";
	        	keyword.style.display = "none";
	        	$('#keywordEndVote').focus();
	        } else if (modeDropdown.value === "voteid"){ // 검색옵션: 작성자id
	        	keywordEndVote.style.display = "none";
	        	keyword.style.display = "inline";
	        	$('#keyword').focus(); // 바로 입력할 수 있도록 focus()
	        }else if (modeDropdown.value === "nickname"){ // 검색옵션: 작성자nickname
	        	keywordEndVote.style.display = "none";
	        	keyword.style.display = "inline";
	        	$('#keyword').focus(); // 바로 입력할 수 있도록 focus()
	        }
	    }
		
		/* 전체검색 버튼 selectAll() */
		function searchAll(){
			location.href = '<%=notWithFormTag%>voteList' ;
		}
		
		function gotoDetail(vno, userid, loginid) {
			//alert(vno + ' / ' + userid);
			let f = document.createElement('form');
			
			let command = document.createElement('input');
			command.setAttribute('type', 'hidden');
			command.setAttribute('name', 'command');
			command.setAttribute('value', 'voteView');
			
			let no = document.createElement('input');
			no.setAttribute('type', 'hidden');
			no.setAttribute('name', 'voteno');
			no.setAttribute('value', vno);
			
			//유저 아이디 넘겨줌.
			let uid = document.createElement('input');
			uid.setAttribute('type', 'hidden');
			uid.setAttribute('name', 'id');
			uid.setAttribute('value', userid);
			
			let loginID = document.createElement('input');
			loginID.setAttribute('type', 'hidden');
			loginID.setAttribute('name', 'loginID');
			loginID.setAttribute('value', loginid);
			
			f.appendChild(command);
			f.appendChild(no);
			f.appendChild(uid);
			f.appendChild(loginID);
			f.setAttribute('method', 'post');
			f.setAttribute('action', '<%=withFormTag%>');
			
			document.body.appendChild(f);
		    f.submit();
		}
</script>
</html>