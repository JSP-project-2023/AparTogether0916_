package com.apartogether.controller.menu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.HomeController;
import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Menu;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.MenuDao;
import com.apartogether.model.dao.StoreDao;

public class MenuManageController extends SuperClass {
	Member biz = null;
	List<Object> obj = new ArrayList<Object>();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		biz = (Member) session.getAttribute("loginfo");
		String id = biz.getId();
		System.out.println("biz : " + biz);
		
		StoreDao stdao = new StoreDao();
		
		try {
			List<Store> myStoreList = stdao.selectAll(id);
			
			if (myStoreList.size() == 0) {
				super.setAlertMessage("등록된 가게가 없습니다. 가게 등록 후 이용해주세요");
				new HomeController().doGet(request, response);
				
			} else {
				obj.add(myStoreList);
				System.out.println("obj size : " + obj.size());
				request.setAttribute("myStoreList", myStoreList);
				super.gotoPage("menu/MenuManage.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
//		String id = request.getParameter("id");
		int stno = Integer.parseInt(request.getParameter("stno"));
		MenuDao meDao = new MenuDao();
		
		try {
			if (stno == -1) { // 가게를 선택해주세요 인 경우
				super.setAlertMessage("메뉴를 관리할 가게를 선택해주세요");
				new MenuManageController().doGet(request, response);
				return;
				
			} else {
				List<Menu> menuList = meDao.selectAll(stno); // stno 해당하는 값 찾아서 전체 메뉴 가져오기
				request.setAttribute("myMenuList", menuList);
				super.gotoPage("menu/MenuManage.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
