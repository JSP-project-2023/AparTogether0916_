package com.apartogether.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;

public class OrderInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 제작 중
		new RoomDetailController().doPost(request, response);
	}
}
