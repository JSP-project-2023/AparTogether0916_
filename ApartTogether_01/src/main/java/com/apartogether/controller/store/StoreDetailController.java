package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;

public class StoreDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		//회원 아이디
		String id = request.getParameter("");
		//회원 가게 고유번호
		request.getParameter("");
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
