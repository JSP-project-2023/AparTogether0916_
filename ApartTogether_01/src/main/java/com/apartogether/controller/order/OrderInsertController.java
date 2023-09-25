package com.apartogether.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;
import com.apartogether.model.bean.Order;
import com.apartogether.model.bean.Personal;
import com.apartogether.model.dao.OrderDao;

public class OrderInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Personal bean = new Personal();
		
		bean.setId(super.loginfo.getId());
		bean.setMenuno(Integer.parseInt(request.getParameter("menuno")) );
		bean.setRoomno(Integer.parseInt(request.getParameter("roomno")) );
		
		System.out.println(request.getParameter("menuno"));
		System.out.println(request.getParameter("menunoqty"));

		System.out.println((request.getParameter("qty")));
		bean.setQty(Integer.parseInt(request.getParameter("qty")));
		
			OrderDao dao = new OrderDao();
			
			
			int cnt = -1;
				try {
					cnt = dao.InsertData(bean);
					new RoomDetailController().doGet(request, response);
				}catch (Exception e) {
					e.printStackTrace();
				}
			new RoomDetailController().doPost(request, response);
		}
	
}
