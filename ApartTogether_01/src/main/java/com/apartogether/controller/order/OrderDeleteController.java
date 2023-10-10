package com.apartogether.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;
import com.apartogether.model.bean.Personal;
import com.apartogether.model.dao.OrderDao;

public class OrderDeleteController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Personal bean = new Personal();
		
		bean.setId(super.loginfo.getId());
		bean.setRoomno(Integer.parseInt(request.getParameter("roomno")));
		
		OrderDao dao = new OrderDao();
		
		int cnt = -1;
		
		try {
			
			cnt = dao.DeleteRoomStatus(bean.getId(),bean.getRoomno());

			cnt = -1;
			
			cnt = dao.DeletePersonal(bean.getId(),bean.getRoomno());
			
			new RoomDetailController().doGet(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		Personal bean = new Personal();
		OrderDao dao = new OrderDao();
		bean.setId(super.loginfo.getId());
		bean.setRoomno(Integer.parseInt(request.getParameter("roomno")));
		bean.setMenuno(dao.getMenuno(request.getParameter("menuname")));
		List<Personal>lists = new ArrayList<Personal>();

		int cnt = -1;
		
		try {
		
			cnt = dao.Deletemenu(bean.getId(),bean.getRoomno(),bean.getMenuno());
			lists= dao.getPersonal(bean.getId(),bean.getRoomno());
			
			if(lists.isEmpty()) {
				
				cnt = -1;
				
				cnt = dao.DeleteRoomStatus(bean.getId(),bean.getRoomno());
			}
	
			new RoomDetailController().doGet(request, response);
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
