package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;

public class StoreInsertController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		Store store = new Store();
		
		//가게 전화번호
		String sttel = String.valueOf(request.getParameter("areacode1")) + "-";
		sttel += String.valueOf(request.getParameter("areacode2")) + "-";
		sttel += String.valueOf(request.getParameter("areacode3"));
		
		//가게 운영시간
		String sttime = String.valueOf(request.getParameter("startShopAmPm")) + " ";
		sttime += String.valueOf(request.getParameter("startShopTime")) + " ~ ";
		sttime += String.valueOf(request.getParameter("endShopAmPm")) + " ";
		sttime += String.valueOf(request.getParameter("endShopTime"));
		
		//store 객체에 set. 11개
		store.setStname(String.valueOf(request.getParameter("stname")));
		store.setCategory(request.getParameter("category"));
		store.setStplace(String.valueOf(request.getParameter("stplace")));
		store.setSttel(sttel);
		store.setContent(request.getParameter("content"));
		store.setCeofile(request.getParameter("ceofile"));
		store.setStlogo(request.getParameter("stlogo"));
		store.setFee(Integer.parseInt(request.getParameter("fee")));
		store.setRedday(request.getParameter("redday"));
		store.setCeono(request.getParameter("ceono"));
		store.setSttime(sttime);
		
		
		StoreDao dao = new StoreDao();
		int cnt = -1;
		
		try {
			//dao를 통해 데이터 삽입
			cnt = dao.Insertstore(store);
			
			if (cnt == -1) {
				System.out.println("가입 실패");
			}
			else {
				System.out.println("가입성공");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//등록후 메인으로 이동
		//super.gotoPage("common/home.jsp");
	}
}
