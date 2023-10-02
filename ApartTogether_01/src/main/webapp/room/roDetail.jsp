<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./../common/common.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 상세 정보</title>
    <style type="text/css">
        .container { margin-top: 50px; }
        tr { opacity: 0.7; }
        .qty{width: 25px;}
        .custom_red{background-color: red;}
        
        ul { list-style: none; }
		#container { padding: 30px 20px; }
		#insertComment {
			padding: 20px 15px;
			border-bottom: 1px solid #7BAEB5;
		}

		#insertComment label {
			display: inline-block;
			width: 80px;
			font-size: 14px;
			font-weight: bold;
			margin-bottom: 10px;
		}

		#insertComment input[type='text'], #insertComment textarea {
			border: 1px solid #ccc;
			vertical-align: middle;
			padding: 3px 10px;
			font-size: 12px;
			line-height: 150%;
		}

		#insertComment textarea {
			width: 1000px;
			height: 30px ;
		}

		.commentItem {
			font-size: 13px;
			color: #333;
			padding: 15px;
			border-bottom: 1px dotted #ccc;
			line-height: 150%;
		}

		.commentItem .id {
			color: #555;
			line-height: 200%;
		}

		.commentItem .id input {
			vertical-align: middle;
		}

		.commentItem .id .name {
			color: #222;
			font-weight: bold;
			font-size: 14px;
		}
		
		.form-group {
			margin-bottom: 3px;
		}
		
		.form-control {
			height: 25px;
		}
		.btn-primary{opacity: 0.8;}
		
		textarea{width:150px; height : 20px;}
		
		h4{font-weight: bold; margin-top: 30px;}
		
    </style>

   <script>

		   var qty = 0;
		   
		   function updateQuantity(menuId, change) {
			    var qtyInput = document.getElementById(menuId + 'qty');
			    var qty = parseInt(qtyInput.value, 10);
		
			    if (isNaN(qty)) {
			        qty = 0;
			    }
		
			    qty += change;
		
			    if (qty < 0) {
			        qty = 0;
			    }
		
			    qtyInput.value = qty;
		
			    var priceElement = document.getElementById(menuId + 'price');
			    var price = parseFloat(priceElement.textContent.replace(/[^0-9.-]+/g, ''));
			    var amount = qty * price;
		
			    var totalpriceElement = document.getElementById(menuId + 'totalprice');
			    totalpriceElement.textContent = amount.toLocaleString();
			    console.log(qtyInput);
			    
			    
			}	
   $(document).on('click', '.delete_btn', function(){
		if(confirm('선택하신 항목을 삭제하시겠습니까?')){
			$.ajax({
				url:'<%=notWithFormTag%>cmDelete', 
				data:'cnum=' + $(this).attr('cnum') ,
				type:'get', 
				dataType:'text',
				success:function(result, status){
					console.log(result);	
					console.log(status);
					getListComment() ; 
				}
			});
		}
	});
	
	function getListComment(){
		$('#comment_list').empty();			
		/* $.ajax() 함수를 이용하여 데이터 보여 주기 */
		$.ajax({
			url:'<%=notWithFormTag%>cmList', 
			data:'roomno=' + '${requestScope.roomno}',
			type:'get', 
			dataType:'json',
			success:function(result, status){
				/* console.log('result는 넘어온 데이터 결과 값') ; */
				/* console.log(result) ; */
				
				$.each(result, function(idx){ /* idx는 색인 번호 */
					var cnum = result[idx].cnum ;
					var id = result[idx].id ;
					var content = result[idx].content ;
					addNewItem(cnum, id, content);
				})
			},
			error:function(result, status){
				console.log(result) ;
				console.log(status) ;
			}
		});
	}
	
	function addNewItem(cnum, id, content){
		/* 댓글 1개를 추가해주는 함수입니다. */
		var litag = $('<li>') ; /* 댓글의 외곽 li 태그  */
		litag.addClass('commentItem') ;
		
		var ptag = $('<p>') ; /* 작성자 정보가 들어갈 태그  */
		ptag.addClass('id') ;

		var spantag = $('<span>') ; /* 작성자 이름이 들어갈 태그  */
		spantag.addClass('name') ;
		spantag.html(id + "님") ;
		
		
		/* 로그인한 사람이 작성한 댓글이면 삭제 가능 */
		if(id == '${sessionScope.loginfo.id}'){ 
			var inputtag = $('<input>') ; /* 삭제 버튼 */
			var attrlist = {'id':id, 'type':'button', 'value':'삭제', 'class':'btn btn-xs btn-outline-primary', 'cnum':cnum};
			inputtag.attr(attrlist);
			inputtag.addClass('delete_btn');
		}else{
			var inputtag = '' ;
		}
		
		var content_p = $('<p>') ; /* 작성한 댓글 내용 */
		content_p.html(content) ; 
		
		/* 조립하기(compose up) */
		ptag.append(spantag).append(inputtag);
		litag.append(ptag).append(content_p) ; 
		
		$('#comment_list').append(litag) ;
	}

	 
	
	$(document).ready(function(){
		getListComment();
		
		/* 사용자가 댓글을 입력하고, 전송 버튼을 눌렀습니다. */
		$('#comment_form').submit(function(){				
			/* 댓글 입력 없이 전송 버튼을 누른 경우 */
			if(!$('#content').val()){
				alert('댓글을 입력해 주셔야 합니다.');
				$('#content').focus() ;
				return false ;
			}
			
			/* post 방식으로 데이터를 전송합니다. */
			var URL = '<%=notWithFormTag%>cmInsert' ;
			var parameters = $('#comment_form').serialize() ;
			/* alert(parameters); */
			$.post(URL, parameters, function(data){
				getListComment(); /* 목록 갱신하기 */
				$('#content').val('');		
				return true ;
				
			}).fail(function(){
				alert('댓글 작성에 실패하였습니다.');
				return false ;
			});
			return false ;
		});
	});


	</script>

</head>
<body>


    <div class="container">
		<h2 style="font-weight: bold">방이름:${requestScope.bean2.roomname}</h2> 
        <table class="table">
            <tbody>
                <tr>
             <td>주문장소 : ${requestScope.bean2.orderplace}</td> 
                    <td>주문 마감까지 남은 시간: (값 넣을 예정)</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="container">
       <h4>가게명:${requestScope.bean2.stname}</h4> 
        <table class="table table-hover">
            <tr>
                <th>Menu Name</th>
                <th>Menu Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.lists}" var="bean">
                <tr>
                    <td>${bean.menuname}</td>
                    <td>${bean.price}</td>
                    <td>${bean.qty}</td>
                    <td>${bean.totalmenu}</td>
                    <td><a href="<%=notWithFormTag%>orDelete&roomno=${bean.roomno}&menuname=${bean.menuname}">x</a></td>      
                </tr>
             </c:forEach>   
             <tr>
             	<td></td>
             	 <td>배달비:${requestScope.bean2.fee}</td>  
             	<td>내 주문 금액:${requestScope.bean.personalprice}</td> 
             	<td>총 금액:${requestScope.bean3.allprice}</td>
             	
				<td>
	             	<form action="<%=withFormTag%>" method="post">
			          	<button type="submit" class="btn btn-danger">전체 삭제</button>
			          	 <input type="hidden" name="command" value="orDelete">
			          	<input type="hidden" name = "roomno" value="${requestScope.roomno}">
	          		</form>
          		</td>
           </tr>
        </table>
          <table class="table " style="margin-top: 30px;">
	          <tr>
	          <td style="font-weight: bold;">방 들어온 사람 아이디</td>
	          <c:forEach items="${requestScope.lists2}" var="bean">
	        
	          				<td>
	          				<c:if test="${requestScope.bangjang eq bean.id }">
	          				  <span class="badge rounded-pill custom_red">방장 </span>
	          				</c:if>
	          		
	                    ${bean.id}</td>   
	          </c:forEach>
	          </tr>
	          <tr>
	          <td style="font-weight: bold;">레디 안한사람 아이디</td>
	           <c:forEach items="${requestScope.lists3}" var="bean">
	           			<td>
	           			<c:if test="${requestScope.bangjang eq bean.id }">
	          				  <span class="badge rounded-pill custom_red">방장 </span>
	          			</c:if>
	                    ${bean.id}</td>      
	          </c:forEach>
	          
	            
	           </tr>
	           
	           
          </table>
          <div style="text-align: right">
	          	<a class="ready" href="<%=notWithFormTag%>roReady&ready=ready&roomno=${requestScope.roomno}">
					<button class="btn btn-primary"> ready</button>
				</a>
				<a class="notready" href="<%=notWithFormTag%>roNotReady&ready=ready&roomno=${requestScope.roomno}">
					<button class ="btn btn-danger">not</button>
				</a>
				<a class="out" href="<%=notWithFormTag%>roOut&roomno=${requestScope.roomno}">
					<button class="btn btn-secondary"> 방 나가기</button>
				</a>
          </div>
          
          <h4>메뉴 목록</h4>
           <table class="table table-hover" style="text-align: center;">
          <tr>
          	<th>Menu Image</th>
          	<th>Menu Name</th>
          	<th>Menu Description</th>
          	<th>Menu Price</th>
          	<th style="text-align: left;">Quantity</th>
          	<th>Total Price</th>          
          </tr>
	        
		<c:forEach items="${requestScope.lists4}" var="bean">
		    <tr> 
		    	
		        <td>${bean.menuImage}</td>
		        <td>${bean.menuname}</td> 
		        <td>${bean.menuDetail}</td> 
		        <td id = "${bean.menuno}price" >${bean.price}원</td>   
		       
			        <td >
			         	<form action="<%=withFormTag%>" method="post">
			            <ul class="pagination" style="align-items : center;"> 
			                <!-- - 버튼 -->
			                <li class="page-item">
			                    <a class="page-link ${bean.menuno}minus" href="#" onclick="updateQuantity('${bean.menuno}', -1)"> - </a>
			                </li>
			               
				                <li class="page-item">
				                    <a class="page-link" href="#" data-bs-toggle="popover" data-bs-trigger="hover"
				                       data-bs-content="기존 카트에 품목이 이미 존재하면 수량을 누적합니다." data-bs-title="${bean.menuno}qty">
				                       
				                        <input type="text"  name="qty" id="${bean.menuno}qty" class="qty" value="0">

				                        <input type="hidden" name="command" value="orInsert">
				        				<input type="hidden" name="menuno" value="${bean.menuno}">
				        				<input type="hidden" name="roomno" value ="${requestScope.roomno}">
				                    </a>
				                   
			        				
				                </li>
				                <!-- + 버튼 -->
				                <li class="page-item">
				                    <a class="page-link ${bean.menuno}plus" href="#" onclick="updateQuantity('${bean.menuno}', 1)"> + </a>
				                </li>
			            </ul>
			            	 <div style="text-align: left;">
			            		<button type="submit" class = "btn btn-warning">메뉴 담기</button>
			            	</div>
			            </form>
			            
			        </td>
			        <td>
			        	 <p>
			              <span class="totalprice" id="${bean.menuno}totalprice">0</span>원
			            </p>
			        </td>
		        
		    </tr>
		</c:forEach>

	</table>
			<c:if test="${requestScope.bangjang eq sessionScope.loginfo.id}">
				<form action="<%=withFormTag%>" method="post">
					<div style="text-align: right">
						<input type="hidden" name="command" value = "orConfirm">
						<input type="hidden" name="roomno" value="${requestScope.roomno}">
						<button type="submit" class="btn btn-info">주문 확정</button>
					</div>
				</form>
          	</c:if>
          
          	
          
     
        ${requestScope.pageInfo.pagingHtml} 
        
     <h4>방 채팅창</h4>
     	<div style="border: medium;">
	     	<div>
				<%-- 댓글 영역(Comment Zone) --%>
				<ul id="comment_list">
					<%-- 여기에 동적으로 요소들을 추가합니다. --%>
				</ul>
			</div>
	        <div id="insertComment">
				<form id="comment_form" method="post" role="form" class="form-horizontal" >
					<table class="table">
					    <thead>
					    </thead>
					    <tbody>
					      <tr>
						      <td>
									<input type="hidden" name="roomno" value="${requestScope.roomno}" />
									<input type="hidden" name="fakeid" id="fakeid" class="form-control" size="10" 
										disabled="disabled" value="${sessionScope.loginfo.name}(${sessionScope.loginfo.id})님">									
									<input type="hidden" name="id" id="id" value="${sessionScope.loginfo.id}">
						        </td>
					        <td>
					        	<label for="content" class="col-xs-3 col-lg-3 control-label">댓글 내용</label>
					        </td>
					        <td>
					        	<textarea id="content" name="content" rows="1" cols="60"></textarea>
					        </td>
					        <td>
					        	<button type="submit" class="btn btn-info">작성하기</button> 
					        </td>
					  
					     
					       
					        	
	
					    </tbody>
					</table>
				</form>	
			</div>
			<div style="text-align: right">
				<a href="<%=notWithFormTag%>roList">
						<button class="btn btn-secondary"> 주문 목록으로 돌아가기</button>
				</a>	
			</div>                 
	    </div>
    </div>
</body>
</html>