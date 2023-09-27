package com.apartogether.controller.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.CompositDao;

public class RoomOutController extends SuperClass {
	@Override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		super.doGet(request, response);
		
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		CompositDao dao = new CompositDao() ;
		int cnt = -1 ;
		
		try {
			cnt = dao.DeleteReady(roomno,super.loginfo.getId());
			new RoomListController().doGet(request, response); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}