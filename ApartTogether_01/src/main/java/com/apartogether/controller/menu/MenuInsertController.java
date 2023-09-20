package com.apartogether.controller.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;

public class MenuInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		메뉴 등록 폼으로 이동할 때
		super.doGet(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		request.setAttribute("stno", stno);
		super.gotoPage("menu/InsertMenu.jsp");
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		입력 다 하고 db에 값 넣을 때
		super.doPost(request, response);
		
		
		
	}
}
