package com.apartogether.controller.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.CompositDao;


public class RoomReadyController extends SuperClass {
	@Override 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		super.doGet(request, response);
		
		String ready = request.getParameter("ready");
		int roomno = Integer.parseInt(request.getParameter("roomno"));
		CompositDao dao = new CompositDao() ;
		int cnt = -1 ;
		
		try {
			cnt = dao.UpdateReady(roomno,super.loginfo.getId(),ready) ;
			super.setSuccessAlertMessage("ready");
			new RoomDetailController().doGet(request, response); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}