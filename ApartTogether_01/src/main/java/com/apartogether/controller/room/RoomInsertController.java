package com.apartogether.controller.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.store.StoreListController;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;


public class RoomInsertController extends SuperClass{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		StoreDao dao = new StoreDao();
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		
		String status = dao.StoreStatuss(stno);
		
				
		System.out.println(status);
		
		if (status.equals("open") ) {
			request.setAttribute("stno", stno);
			super.gotoPage("room/roInsert.jsp");

		}else {
			super.setAlertMessage("가게가 영업중이 아닙니다.");
			new StoreListController().doGet(request, response);
		}
	}
}
