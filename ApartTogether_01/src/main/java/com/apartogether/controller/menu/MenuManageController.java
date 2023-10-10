package com.apartogether.controller.menu;

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
	StoreDao stdao = new StoreDao(); // get, post 둘다 사용
	Member biz = null;
	String id = null; // loginfo 아이디

	
//	loginfo에서 아이디 가져오기
	public void bizID(HttpServletRequest request, HttpServletResponse response) throws Exception {
		biz = (Member) session.getAttribute("loginfo");
		id = biz.getId();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		this.bizID(request, response);

		try {
			List<Store> myStoreList = stdao.selectAll(id); // 현재 로그인된 사업자 가게 모두 불러오기
			
//			가게가 없는 경우
			if (myStoreList.size() == 0) {
				super.setAlertMessage("등록된 가게가 없습니다. 가게 등록 후 이용해주세요");
				new HomeController().doGet(request, response);

			} else {
//				가게정보 담아서 이동
				request.setAttribute("myStoreList", myStoreList);
				super.gotoPage("menu/MenuManage.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		메뉴관리 화면에서 가게 선택 후 메뉴 리스트 화면에 뿌리기
		super.doPost(request, response);
		
//		선택한 가게 정보
		int stno = Integer.parseInt(request.getParameter("stno"));
		MenuDao meDao = new MenuDao();

		try {
			if (stno == -1) { // '가게를 선택해주세요' 인 경우
				super.setAlertMessage("메뉴를 관리할 가게를 선택해주세요");
				new MenuManageController().doGet(request, response); // 다시 사업자가 보유한 가게 리스트 불러오기
				return;

			} else { // 가게 선택한 경우
				List<Store> myStoreList = stdao.selectAll(id); // 가게 리스트 가져오기
				
				if (myStoreList.size() == 0) {
					super.setAlertMessage("등록된 가게가 없습니다. 가게 등록 후 이용해주세요");
					new HomeController().doGet(request, response);

				} else { // 가게 정보 + 해당 가게의 메뉴 정보 담아서 이동
					request.setAttribute("myStoreList", myStoreList);

					List<Menu> menuList = meDao.selectAll(stno); // stno 해당하는 값 찾아서 전체 메뉴 가져오기
					
					request.setAttribute("getStno", stno);
					request.setAttribute("myMenuList", menuList);
					super.gotoPage("menu/MenuManage.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
