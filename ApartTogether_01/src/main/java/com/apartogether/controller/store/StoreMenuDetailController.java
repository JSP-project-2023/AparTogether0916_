package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Menu;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class StoreMenuDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		StoreDao dao = new StoreDao();
		Store bean = new Store();
		bean = dao.getStorebyStno(stno);
		
		request.setAttribute("bean", bean);
		request.setAttribute("stno", stno);
		
		//메뉴 정보 받아옴
		List<Menu> lists = null;
		lists = dao.getMenubyStno(stno);
		request.setAttribute("menuBean", lists);
		
		super.gotoPage("store/StoreMenuDetail.jsp");
		
	}
}
