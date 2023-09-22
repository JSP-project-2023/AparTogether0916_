package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.StoreDao;

public class StoreDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		/*  내 가게 보기 화면에서 delete한 경우 파라미터로 id 값 받아옴  */
		String id = request.getParameter("id");
		int cnt = -1;
		
		StoreDao dao = new StoreDao();
		
		try {
			cnt = dao.deleteStore(stno);
			
			/*  완료 알럿(초록색)  */
			super.setSuccessAlertMessage("삭제가 완료되었습니다.");
			
			
			if (id!=null) { // 내 가게 보기 화면으로 돌아가기
				new MyStoreListController().doGet(request, response);
				
			} else { // 일반 가게 목록
				new StoreListController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}