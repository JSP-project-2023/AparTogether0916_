package com.apartogether.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Menu;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.MenuDao;
import com.apartogether.model.dao.StoreDao;
import com.oreilly.servlet.MultipartRequest;

public class MenuUpdateController extends SuperClass {
	MenuDao dao = new MenuDao();
	Menu menuBean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
//		가게 이름 가져오기
		Store store = new Store();
		StoreDao stdao = new StoreDao();
		
		String id = request.getParameter("id");
		int stno = Integer.parseInt(request.getParameter("stno"));
		int menuno = Integer.parseInt(request.getParameter("menuno"));
		
		try {
//			가게 이름 정보 담기
			store = stdao.getStorebyId(id, stno);
			String stname = store.getStname(); 
			
//			메뉴 정보 가져오기
			menuBean = dao.GetDataByNo(menuno);
			
			String detail = menuBean.getMenudetail();
			String str[] = null;
			
//			메뉴 설명 구분자 처리
			if (detail.indexOf("Δ") < 0) { // 구분자 없으면 detail로 입력
				menuBean.setMenudetail(detail);
				
			} else { // detailPlus랑 구분해서 따로 requestScope에 저장
				str = detail.split("Δ");
				menuBean.setMenudetail(str[0]);
				request.setAttribute("detailPlus", str[1]);
			}
			
			request.setAttribute("stname", stname);
			request.setAttribute("menuBean", menuBean);
			super.gotoPage("menu/UpdateMenu.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		menuBean = new Menu();
		
		String menudetail = mr.getParameter("menudetail");
		String detailPlus = mr.getParameter("detailPlus");
		
		System.out.println("새로운 이미지 : " + mr.getFilesystemName("menuimage") + " 옛날 : " + mr.getParameter("menuimageUp"));
		
//		새로운 메뉴 이미지 등록 여부에 따라 어떤 파일로 저장할건지 결정 
		String menuimage = dao.changeFile(mr.getFilesystemName("menuimage"), mr.getParameter("menuimageUp")); // new, old 순서
		
		menuBean.setMenuimage(menuimage);
		menuBean.setMenuname(mr.getParameter("menuname"));
		menuBean.setMenuno(Integer.parseInt(mr.getParameter("menuno")));
		menuBean.setPrice(Integer.parseInt(mr.getParameter("price")));
		menuBean.setStno(Integer.parseInt(mr.getParameter("stno")));
		menuBean.setMenudetail(menudetail + "Δ" + detailPlus);
		
		dao = new MenuDao();
		int cnt = -1;
		
		try {
			cnt = dao.UpdateMenu(menuBean);
			
			if (cnt == -1) {
				super.setAlertMessage("메뉴 수정에 실패하였습니다.");
			}else {
				super.setSuccessAlertMessage("메뉴 수정이 완료되었습니다.");
				
				// 업데이트 후 가게 상세화면으로 이동
				new MenuManageController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
