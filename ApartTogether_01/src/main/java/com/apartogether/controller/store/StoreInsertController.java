package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;
import com.oreilly.servlet.MultipartRequest;

public class StoreInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Store store = new Store();
		
		//가게 전화번호
		String sttel = String.valueOf(mr.getParameter("areacode1")) + "-";
		sttel += String.valueOf(mr.getParameter("areacode2")) + "-";
		sttel += String.valueOf(mr.getParameter("areacode3"));
		
		//가게 운영시간
		String sttime = String.valueOf(mr.getParameter("startShopAmPm")) + " ";
		sttime += String.valueOf(mr.getParameter("startShopTime")) + " ~ ";
		sttime += String.valueOf(mr.getParameter("endShopAmPm")) + " ";
		sttime += String.valueOf(mr.getParameter("endShopTime"));
		
		//가게 주소
		String stplace = String.valueOf(mr.getParameter("stplace1")) + "Δ";
		stplace += String.valueOf(mr.getParameter("stplace2"));
		
		//store 객체에 set. 11개
		store.setStname(String.valueOf(mr.getParameter("stname")));
		store.setCategory(mr.getParameter("category"));
		store.setStplace(stplace);
		store.setSttel(sttel);
		store.setContent(mr.getParameter("content"));
		store.setCeofile(mr.getFilesystemName("ceofile"));
		store.setStlogo(mr.getFilesystemName("stlogo")); 
		store.setFee(Integer.parseInt(mr.getParameter("fee")));
		store.setRedday(mr.getParameter("redday"));
		store.setCeono(mr.getParameter("ceono"));
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
		super.gotoPage("common/home.jsp");
	}
}
