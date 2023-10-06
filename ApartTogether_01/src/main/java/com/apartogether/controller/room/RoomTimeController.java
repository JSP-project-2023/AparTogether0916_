package com.apartogether.controller.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.CompositDao;


public class RoomTimeController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		Integer roomno = Integer.parseInt(request.getParameter("roomno"));
		String roomtime;
		int cnt = -1;
		try {
			CompositDao dao = new CompositDao();
			roomtime = dao.getRemainingTime(roomno);
			if(roomtime.equals("00:00:00")) {
				cnt = dao.DeleteRoomInfo(roomno);
				super.setAlertMessage("시간초과로 방 삭제");
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(roomtime);
				System.out.println(roomtime);
			}else {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(roomtime);
				System.out.println(roomtime);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}