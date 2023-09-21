package com.apartogether.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomListController;
import com.apartogether.model.bean.Order;
import com.apartogether.model.dao.OrderDao;

public class MyOrderListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		OrderDao dao = new OrderDao();
		try {
			
			List<Order> orderList = dao.GetHistory(super.loginfo.getId());

			if(orderList.size()==0) {
				super.setAlertMessage("이전 주문 내역이 존재하지 않습니다.");
				new RoomListController().doGet(request, response);
				
			}else {
				request.setAttribute("orderList", orderList);
				super.gotoPage("order/myOrderList.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
