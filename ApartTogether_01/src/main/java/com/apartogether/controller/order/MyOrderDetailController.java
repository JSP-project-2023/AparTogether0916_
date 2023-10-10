package com.apartogether.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Order;
import com.apartogether.model.cart.CartItem;
import com.apartogether.model.dao.OrderDao;

public class MyOrderDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		// 사람 수를 구하는 변수
		int su = 0;
		
		// 배달료를 가져오는 변수
		int fee = 0;
		
		
		if(super.loginfo == null) {
			super.youNeededLogin();
			return;
		}
		OrderDao dao = new OrderDao();
		
		try {
			int roomno = Integer.parseInt(request.getParameter("roomno"));
			Order order = dao.getDetailHistory(roomno,super.loginfo.getId());
			List<CartItem> lists = dao.showDetail(roomno,super.loginfo.getId());
			
			fee = dao.getStorefee(roomno);
			su = dao.getTotalMember(roomno);
	
			// 1인당 배달료
			int suFee = fee/su;
			request.setAttribute("su", su);
			request.setAttribute("suFee", suFee);
			request.setAttribute("fee", fee);
			request.setAttribute("order", order); // 주문 정보
			request.setAttribute("lists", lists); // 쇼핑 정보
			
			super.gotoPage("order/myOrderDetail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
