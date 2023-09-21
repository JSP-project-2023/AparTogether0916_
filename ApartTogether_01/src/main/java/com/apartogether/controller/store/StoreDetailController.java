package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class StoreDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		//회원 아이디
		String id = request.getParameter("id");
		//회원 가게 고유번호
		int stno =Integer.parseInt(request.getParameter("stno"));
		
		StoreDao dao = new StoreDao();
		Store bean = dao.getStorebyId(id, stno);
		
		//가게 주소
		String[] staddr = bean.getStplace().split("Δ");

		// 가게 전화번호
		String[] sttel = bean.getSttel().split("-");
		request.setAttribute("staddr", staddr);
		request.setAttribute("sttel", sttel);
		request.setAttribute("bean", bean);
		request.setAttribute("id", id);
		
		super.gotoPage("store/DetailStore.jsp");
	}
}
