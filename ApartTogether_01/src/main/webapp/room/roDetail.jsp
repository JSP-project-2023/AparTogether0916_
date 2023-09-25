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
        .qty{width: 25px;}
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
                    <c:set var="roomno" value="${bean.roomno}"></c:set>
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
          <tr>
          	<th>Menu Image</th>
          	<th>Menu Name</th>
          	<th>Menu Description</th>
          	<th>Menu Price</th>
          	<th>Quantity</th>
          	<th>Total Price</th>          
          </tr>
	        
		<c:forEach items="${requestScope.lists4}" var="bean">
		    <tr> 
		    	
		        <td>${bean.menuImage}</td>
		        <td>${bean.menuname}</td> 
		        <td>${bean.menuDetail}</td> 
		        <td id = "${bean.menuno}price" >${bean.price}원</td>   
		       
			        <td>
			         	<form action="<%=withFormTag%>" method="post">
			            <ul class="pagination">
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
				        				<input type="hidden" name="roomno" value ='<c:out value="${roomno}"/>'>
				                    </a>
				                   
			        				
				                </li>
				                <!-- + 버튼 -->
				                <li class="page-item">
				                    <a class="page-link ${bean.menuno}plus" href="#" onclick="updateQuantity('${bean.menuno}', 1)"> + </a>
				                </li>
			            </ul>
			            
			            	<button type="submit">메뉴 담기</button>
			            </form>
			        </td>
			        <td>
			        	 <p>
			              <span class="totalprice" id="${bean.menuno}totalprice">0</span>원
			            </p>
			        </td>
			        <td>
			        
			        	
			        	
			        
			        </td>
		        
		    </tr>
		</c:forEach>

	</table>
          <a class="bigFont" href="<%=notWithFormTag%>roReady">레디</a>
          <button type="button" class="btn">주문 확정</button>
        ${requestScope.pageInfo.pagingHtml}                  
    </div>
</body>
</html>