package com.apartogether.controller.room;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Wishlist;
import com.apartogether.model.dao.RoomDao;

public class RoomWishListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int roomno = 0 ;
		
		RoomDao dao = new RoomDao();
		
		List<Wishlist>wishlist = dao.GetRoomWishList(roomno);
		
		request.setAttribute("wishlist", wishlist);
		
		super.gotoPage("room/roWishList.jsp");
	}
	
}
