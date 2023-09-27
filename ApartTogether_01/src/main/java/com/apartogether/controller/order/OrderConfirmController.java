package com.apartogether.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;
import com.apartogether.controller.room.RoomListController;
import com.apartogether.model.bean.Personal;
import com.apartogether.model.dao.OrderDao;

public class OrderConfirmController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Integer roomno = Integer.parseInt(request.getParameter("roomno"));
		
		OrderDao dao = new OrderDao();
		int cnt = -1;
		
		// 전체 멤버수 구하는 변수 
		int totalmember = dao.getTotalMember(roomno);
		
		// 레디를 한 멤버수 구하는 변수
		int readymember = dao.getReadyMember(roomno);
		

		
		
		try {
			// 레디를 한사람이랑 전체 멤버수같을 경우에만 주문이 진행됨
			if(totalmember == readymember) {
				cnt = dao.updatePersonal(roomno);
				
				super.setAlertMessage("주문이 완료되었습니다.");
				new RoomListController().doGet(request, response);
				
			}else {
				super.setAlertMessage("레디를 하지 않은 사람이 있습니다.");
				new RoomDetailController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
