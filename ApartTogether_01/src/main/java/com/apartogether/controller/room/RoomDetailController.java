package com.apartogether.controller.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Combo01;
import com.apartogether.model.bean.Room;
import com.apartogether.model.dao.CompositDao;
import com.apartogether.model.dao.RoomDao;



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

			// 방장의 이름을 찾기 위한 변수
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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		RoomDao rdao= new RoomDao();

		try {
			if(request.getParameter("roomname")==null){
				super.setAlertMessage("방제목을 입력해 주세요");
				new RoomInsertController().doPost(request, response);			
			}else {
				if(request.getParameter("orderplace") == null) {
					super.setAlertMessage("배달 주소를 입력해 주세요");
					new RoomInsertController().doPost(request, response);
				}else {
					
					Integer stno = Integer.parseInt(request.getParameter("stno"));
					String roomname = request.getParameter("roomname");
					String orderplace = request.getParameter("orderplace");
					
					int cnt = -1;
					
					Integer roomno = rdao.getRoomNo();
					request.setAttribute("roomno", roomno );
					cnt = rdao.InsertData(stno,roomno,orderplace,roomname);
					
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
						
				}
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
	

		
		

