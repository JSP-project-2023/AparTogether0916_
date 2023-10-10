package com.apartogether.controller.order;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;
import com.apartogether.model.bean.Personal;
import com.apartogether.model.dao.OrderDao;

public class OrderInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		if(super.loginfo==null) {
			super.youNeededLogin(); 
			return ;
		}
		
		Personal bean = new Personal();
		
		bean.setId(super.loginfo.getId());
		bean.setMenuno(Integer.parseInt(request.getParameter("menuno")) );
		bean.setRoomno(Integer.parseInt(request.getParameter("roomno")) );
		bean.setQty(Integer.parseInt(request.getParameter("qty")));
		
		
			OrderDao dao = new OrderDao();
			
			// 기존 주문내역을 key 값을 이용해 비교하기 위해서 만든 map 구조		
			Map<Integer, Integer> exsistdata = dao.getDatabyPk(bean.getRoomno(),super.loginfo.getId());
			
			
			
			int cnt = -1;
				try {
					if(bean.getQty()==0) {
						// 수량이 0 이라면
						super.setAlertMessage("수량을 입력해주세요");
						new RoomDetailController().doGet(request, response);
					}else {
						// room status 테이블에 id 가 존재하지 않다면 추가 
						if(dao.findIdroomstatus(bean.getId(),bean.getRoomno())== null) {
							cnt = dao.InsertIdroomstatus(bean.getRoomno(),bean.getId());
						}
							// 만약 MENU 번호가 존재 한다면
						if(exsistdata.containsKey(bean.getMenuno())){
								// UPDATE 문 실행
								System.out.println("데이터 업데이트");
								cnt = dao.UpdateData(bean);

						}else {
								// 아니면 데이터 삽입
								System.out.println("데이터 삽입");
								cnt = dao.InsertData(bean);
							}
						new RoomDetailController().doGet(request, response);
						}
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
		}
	
}
