package com.apartogether.controller.menu;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class MenuManageController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		Member biz = (Member) session.getAttribute("loginfo");
		String id = biz.getId();
		
		StoreDao stdao = new StoreDao();
		
		try {
			List<Store> myStoreList = stdao.selectAll(id);
			
			request.setAttribute("myStoreList", myStoreList);
			super.gotoPage("menu/MenuManage.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
