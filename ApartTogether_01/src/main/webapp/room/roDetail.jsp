<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 상세 정보</title>
    <style type="text/css">
        .container { margin-top: 10px; }
        tr { opacity: 0.7; }
    </style>
    <script>
    	$(document).ready(function(){
    	
var price = '${bean.price}';
			
			$('#qty').innerWidth($('.minus').innerWidth() - 3);
			$('#totalprice').text('0') ; /* 최초 시작시 금액을 0으로 설정 */
			
			/* attr() 함수는 속성(attribute)을 읽거나 쓰기 위한 함수 */
			$('.small_image').click(function(){
				$('.active_image').attr('src', $(this).attr('src')) ;
			});
			
			$('.plus').click(function(){  /* 사용자가 + 버튼을 클릭함 */
				var qty = $('#qty').val();
				if(qty == maxPurchaseSize){
					alert('최대 ' + maxPurchaseSize + '개 까지만 주문이 가능합니다.');
					return ; /* 더이상 진행하지 않도록 할께요. */
				}
				/* Number 객체는 Integer.parseInt()와 동일한 효과 */
				var newQty = Number(qty) + 1 ;
				if(qty == ''){
					$('#qty').val('1');
				}else{
					$('#qty').val(newQty) ;
				}
				var amount = newQty*price ;
				$('#totalprice').text(amount.toLocaleString()) ;
			});
			 
			$('.minus').click(function(){ /* 사용자가 - 버튼을 클릭함 */
				var qty = $('#qty').val();
				if(qty == '0'){
					alert('최소 1개 이상 구매하셔야 합니다.');
					return ;
				}
				
				var newQty = Number(qty) - 1 ;
				if(qty == ''){
					$('#qty').val('');
					$('#totalprice').text('0') ;
				}else{
					$('#qty').val(newQty) ;
					
					var amount = newQty*price ;
					$('#totalprice').text(amount.toLocaleString()) ;
				}				
			});
			
			$('#qty').blur(function(){ /* 수량 입력란이 포커스를 잃을 때 */
				var qty = $('#qty').val();
			
				if(qty == '' || isNaN(qty) == true){
					alert('0이상' + maxPurchaseSize + '이하의 숫자만 가능합니다.' );
					$('#qty').val('0');
					$('#qty').focus();
					return ;
				}
				
				if(isNaN(qty) == false){
					var newQty = Number(qty) ;
					if(newQty < 0 || newQty > maxPurchaseSize){
						alert('0이상' + maxPurchaseSize + '이하의 숫자만 가능합니다.' );
						$('#qty').val('0');
						$('#qty').focus();
						return ;
					}
				}
    		
    		
    		
    	})
    	
    	
    
    
    </script>
</head>
<body>
    <div class="container mt-3">
<h2>방이름:${requestScope.bean2.roomname}</h2> 
        <table class="table">
            <tbody>
                <tr>
             <td>주문장소:${requestScope.bean2.orderplace}</td> 
                    <td>주문 마감까지 남은 시간: (값 넣을 예정)</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="container">
       <h5>가게명:${requestScope.bean2.stname}</h5> 
        <table class="table table-hover">
            <tr>
                <th>Room Number</th>
                <th>Menu Name</th>
                <th>Menu Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
            </tr>
            <c:forEach items="${requestScope.lists}" var="bean">
                <tr>
                    <td>${bean.roomno}</td>
                    <td>${bean.menuname}</td>
                    <td>${bean.price}</td>
                    <td>${bean.qty}</td>
                    <td>${bean.totalmenu}</td>      
                </tr>
             </c:forEach>   
             <tr>
             	 <td>배달비:${requestScope.bean2.fee}</td>  
             	<td>내 주문 금액:${requestScope.bean.personalprice}</td> 
             	<td>총 금액:${requestScope.bean3.allprice}</td>
           </tr>
        </table>
          <table class="table table-hover">
          <tr>
          <td>방 들어온 사람 아이디</td>
          <c:forEach items="${requestScope.lists2}" var="bean">
                    <td>${bean.id}</td>      
          </c:forEach>
          <td>레디 안한사람 아이디</td>
           <c:forEach items="${requestScope.lists3}" var="bean">
                    <td>${bean.id}</td>      
          </c:forEach>
           </tr>
          </table>
          
          <h4>메뉴 목록</h4>
           <table class="table table-hover">
          
	          <c:forEach items="${requestScope.lists4}" var="bean">
	          <tr> 
	                    <td>${bean.menuImage}</td>
	                    <td>${bean.menuname}</td> 
	                    <td>${bean.menuDetail}</td> 
	                    <td>${bean.price}</td>   
	                    <td></td>
	          </tr>
	        
	          
	          </c:forEach>
             
           
          </table>
          <a class="bigFont" href="<%=notWithFormTag%>roReady">레디</a>
          <button type="button" class="btn">주문 확정</button>
        ${requestScope.pageInfo.pagingHtml}
    </div>
</body>
</html>