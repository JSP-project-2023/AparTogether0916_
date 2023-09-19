package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class StoreUpdateController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		
		
		
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		//수정 버튼으로 update폼으로 이동
		
		//아아디 가져옴.
		String id = request.getParameter("id");
		//가게 고유번호 가져옴
		String stno = request.getParameter("stno");
				
		StoreDao dao = new StoreDao();
				
		Store bean = dao.getStorebyId(id, stno);
				
		request.setAttribute("bean", bean);
		request.setAttribute("id", id);
		
		super.gotoPage("store/UpdateStore.jsp");
	}
}
