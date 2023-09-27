package com.apartogether.controller.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Combo01;
import com.apartogether.model.bean.Room;
import com.apartogether.model.dao.CompositDao;



public class RoomDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		super.doGet(request, response);
		
		Integer roomno = Integer.parseInt(request.getParameter("roomno"));
		CompositDao dao = new CompositDao() ;
		Combo01 bean = null ;
		Combo01 bean2 = null ;
		Combo01 bean3 = null ;
		int minorderno = 0;
		String name = null;
		List<Combo01> lists = null;
		List<Combo01> lists2 =null;
		List<Combo01> lists3 =null;
		List<Combo01> lists4 =null;
		
		if(super.loginfo==null) {
			super.youNeededLogin(); 
			return ;
		}
		
		try {
			lists = dao.View01(roomno,super.loginfo.getId());
			request.setAttribute("roomno", roomno);
			bean = dao.getPrice(roomno,super.loginfo.getId());
			request.setAttribute("lists", lists);
			request.setAttribute("bean", bean) ;
			bean2 = dao.getRoomDetailInfo(roomno);
			bean3 = dao.getAllPrice(roomno);
			request.setAttribute("bean2", bean2) ;
			request.setAttribute("bean3", bean3) ;
			lists2 = dao.selectReadyId(roomno);
			request.setAttribute("lists2", lists2);
			lists3 = dao.selectNotReadyId(roomno);
			request.setAttribute("lists3", lists3);
			
			
			// 방장을 비교하기 위한 bean orderno로 순서 비교
			minorderno = dao.getMinOrderno(roomno);

			
			name = dao.getBangjang(roomno,minorderno);
			request.setAttribute("bangjang", name);
			
			// 메뉴 관련 리스트
			lists4 = dao.getAllMenu(roomno);
			request.setAttribute("lists4", lists4);
			
			
			
			super.gotoPage("room/roDetail.jsp");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	

		
		

