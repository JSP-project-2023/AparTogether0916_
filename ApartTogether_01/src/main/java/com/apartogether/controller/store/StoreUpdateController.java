package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class StoreUpdateController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Store bean = new Store();
		
		
		//TODO: 다오를 통해 업데이트
		request.getParameter("stname");
		request.getParameter("category");
		
		//주소
		request.getParameter("stplace1");
		request.getParameter("stplace2");
		
		//번호
		request.getParameter("areacode1");
		request.getParameter("areacode2");
		request.getParameter("areacode3");
		
		request.getParameter("cotent");
		request.getParameter("ceofile");
		
		//가게 오픈, 마감시간
		request.getParameter("startShopAmPm");
		request.getParameter("startShopTime");
		request.getParameter("endShopAmPm");
		request.getParameter("endShopTime");
		
		request.getParameter("stlogo");
		request.getParameter("fee");
		request.getParameter("redday");
		request.getParameter("ceono");

		
		
		//TODO: 업데이트 후 알럿 창
		super.setAlertMessage("수정이 완료되었습니다.");
		
		//TODO: 업데이트 후 가게 상세화면으로 이동
		
		
		
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		//수정 버튼으로 update폼으로 이동
		
		//아아디 가져옴.
		String id = request.getParameter("id");
		//가게 고유번호 가져옴
		String stno = request.getParameter("stno");
				
		StoreDao dao = new StoreDao();
				
		Store bean = dao.getStorebyId(id, stno);
		
		//가게 전화번호
		String[] sttel = bean.getSttel().split("-");
		
		//가게 운영시간 split
		String str = bean.getSttime().replace("~", " ");
		String[] sttime = str.split("\\s+");
		
		request.setAttribute("sttime", sttime);
		request.setAttribute("sttel", sttel);
		request.setAttribute("bean", bean);
		request.setAttribute("id", id);
		
		super.gotoPage("store/UpdateStore.jsp");
	}
}
