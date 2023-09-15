package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;

public class StoreInsertController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.doGet(request, response);
		super.gotoPage("store/InsertStore.jsp");
	}
}
