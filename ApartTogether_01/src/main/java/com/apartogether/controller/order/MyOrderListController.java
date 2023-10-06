package com.apartogether.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomListController;
import com.apartogether.model.bean.Order;
import com.apartogether.model.dao.OrderDao;
import com.apartogether.utility.Paging;

public class MyOrderListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		OrderDao dao = new OrderDao();
		if(super.loginfo==null) {
			super.youNeededLogin(); 
			return ;
		}
		
		try {
			int totalCount = dao.GetTotalRecordCount(super.loginfo.getId(),mode,keyword); 
			String url = super.getUrlInfomation("roList") ;
			boolean isGrid = false ;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, mode, keyword,url, isGrid);
			
			List<Order> orderList = dao.GetHistory(super.loginfo.getId(),pageInfo);
		
			
			if(orderList.size()==0) { // 주문내역이 없을시
				super.setAlertMessage("이전 주문 내역이 존재하지 않습니다.");
				new RoomListController().doGet(request, response);
				
			}else { // 주문 내역이 존재할 때
				request.setAttribute("orderList", orderList);
				
				request.setAttribute("pageInfo", pageInfo);

				super.gotoPage("order/myOrderList.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
