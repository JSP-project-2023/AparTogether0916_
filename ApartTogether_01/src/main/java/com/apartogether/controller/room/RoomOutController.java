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
			
			// 방에 남아있는 사람이 없으면 방을 데이터 베이스에서 지움
			if(dao.getCountRoomMember(roomno)== 0) {
				cnt = dao.DeleteRoom(roomno);
			}
			new RoomListController().doGet(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}