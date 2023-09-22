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

		if(super.loginfo == null) {
			super.youNeededLogin();
			return;
		}
		OrderDao dao = new OrderDao();
		
		try {
			int roomno = Integer.parseInt(request.getParameter("roomno"));
			Order order = dao.getDetailHistory(roomno,super.loginfo.getId());
			List<CartItem> lists = dao.showDetail(roomno,super.loginfo.getId());
			
			request.setAttribute("order", order); // 주문 정보
			request.setAttribute("lists", lists); // 쇼핑 정보
			
			super.gotoPage("order/myOrderDetail.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
