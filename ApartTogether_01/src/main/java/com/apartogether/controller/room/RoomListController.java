package com.apartogether.controller.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Room;
import com.apartogether.model.dao.RoomDao;
import com.apartogether.utility.Paging;

public class RoomListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		RoomDao dao = new RoomDao();
		try {
			int totalCount = dao.GetTotalRecordCount(mode,keyword); 
			String url = super.getUrlInfomation("roList") ;
			boolean isGrid = false ;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			// 전체 페이지를 보여주는 리스트
			List<Room> lists = dao.selectInfo(pageInfo);
			
			
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("roomlist", lists);
			
			super.gotoPage("room/roList.jsp");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
}
}
