package com.apartogether.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.MenuDao;

public class MenuDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int menuno = Integer.parseInt(request.getParameter("menuno"));
		System.out.println("menuno : " + menuno);
		
		MenuDao dao = new MenuDao();
		int cnt = -1;
		
		try {
			cnt = dao.DeleteMenu(menuno);
			
			if (cnt == -1) {
//				실패
				super.setAlertMessage("메뉴 삭제를 실패하였습니다.");
				new MenuManageController().doGet(request, response);
				
			} else {
				super.setSuccessAlertMessage("메뉴가 삭제되었습니다.");
				new MenuManageController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
