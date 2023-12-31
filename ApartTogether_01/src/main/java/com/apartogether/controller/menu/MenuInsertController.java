package com.apartogether.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.store.MyStoreListController;
import com.apartogether.controller.store.StoreListController;
import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Menu;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.MenuDao;
import com.apartogether.model.dao.StoreDao;
import com.oreilly.servlet.MultipartRequest;

public class MenuInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		메뉴 등록 폼으로 이동할 때
		super.doGet(request, response);
		
		Member biz = (Member) session.getAttribute("loginfo");
		String id = biz.getId();
		
//		어떤 가게 메뉴 추가할지 stno로 구분
		int stno = Integer.parseInt(request.getParameter("stno"));
		
//		가게 정보 가져오기
		StoreDao dao = new StoreDao();
		Store stBean = dao.getStorebyId(id, stno);

		String stname = stBean.getStname();
		
		request.setAttribute("stno", stno);
		request.setAttribute("stname", stname);
		super.gotoPage("menu/InsertMenu.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		입력 다 하고 db에 값 넣을 때
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest) request.getAttribute("mr");
		
		Menu menu = new Menu();
		
//		파라미터 값 받아오기
		menu.setStno(Integer.parseInt(mr.getParameter("stno")));
		menu.setMenuname(mr.getParameter("menuname"));
		menu.setPrice(Integer.parseInt(mr.getParameter("price")));
		menu.setMenuimage(mr.getFilesystemName("menuimage"));
		if (mr.getParameter("detailPlus").equals(null) || mr.getParameter("detailPlus") == null) {
			menu.setMenudetail(mr.getParameter("menudetail"));
		} else {
			menu.setMenudetail(mr.getParameter("menudetail") + "Δ" + mr.getParameter("detailPlus")); // 메뉴 설명 + 재료 | 기본옵션
		}
		
		MenuDao dao = new MenuDao();
		int cnt = -1;
		
		try {
//			메뉴 등록
			cnt = dao.InsertMenu(menu);
			
//			메뉴 등록 성공 여부 확인
			if (cnt == -1) {
//				실패 메세지 출력
				super.setAlertMessage("메뉴 등록 실패");
				new StoreListController().doGet(request, response);
				
			} else {
//				성공 메세지 출력
				super.setSuccessAlertMessage("메뉴 등록 성공");
				new MenuManageController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
