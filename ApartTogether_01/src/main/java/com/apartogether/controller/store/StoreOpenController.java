package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.StoreDao;

// 가게 영업시작 버튼을 눌렀을 때 컨트롤러
public class StoreOpenController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		String id = request.getParameter("id");
		
		StoreDao dao = new StoreDao();
		
		int cnt = -1;
		
		try {
			cnt = dao.StoreOpen(stno,id);

			request.setAttribute("stno", stno);
			new MyStoreListController().doGet(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
