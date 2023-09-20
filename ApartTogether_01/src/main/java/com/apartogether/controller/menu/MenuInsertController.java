package com.apartogether.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.store.MyStoreListController;
import com.apartogether.controller.store.StoreListController;
import com.apartogether.model.bean.Menu;
import com.apartogether.model.dao.MenuDao;
import com.oreilly.servlet.MultipartRequest;

public class MenuInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		메뉴 등록 폼으로 이동할 때
		super.doGet(request, response);
		
//		어떤 가게 메뉴 추가할지 stno로 구분
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		request.setAttribute("stno", stno);
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
		menu.setMenudetail(mr.getParameter("menudetail"));
		
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
				request.setAttribute("", Integer.parseInt(mr.getParameter("stno")));
//				가게 상세 화면으로 이동 예정
				new StoreListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
