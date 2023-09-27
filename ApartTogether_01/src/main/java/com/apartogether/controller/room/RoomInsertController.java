package com.apartogether.controller.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;


public class RoomInsertController extends SuperClass{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		request.setAttribute("stno", stno);
		super.gotoPage("room/roInsert.jsp");
		
		
	}
}
