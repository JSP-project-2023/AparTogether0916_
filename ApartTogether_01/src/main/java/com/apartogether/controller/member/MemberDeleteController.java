package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.MemberDao;

public class MemberDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		String id = request.getParameter("id") ; // common에서 탈퇴하기를 누르면 id 파라메터를 붙여서 넘겨준다.
		MemberDao dao = new MemberDao();
		int cnt = -1;
		
		try {
			cnt = dao.deleteData(id) ; // MemberDao.deleteData 
			super.session.invalidate(); // 세션에 저장된 로그인 정보를 삭제한다.
			new MemberLoginController().doGet(request, response); // 로그인페이지로 이동한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
