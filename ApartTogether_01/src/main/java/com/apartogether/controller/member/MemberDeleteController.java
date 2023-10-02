package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

public class MemberDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		String id = request.getParameter("id") ; // common에서 탈퇴하기를 누르면 id 파라메터를 붙여서 넘겨준다.
		MemberDao mdao = new MemberDao();
//		StoreDao sdao = new StoreDao();
		int cnt = -1;
		
		Member bean = new Member();
		try {
			// 사업자가 탈퇴하는 경우 연관된 가게들도 삭제해야한다.
			bean = mdao.getDataByPrimaryKey(id);
			String mtype = bean.getMtype();
			
			
				if(mtype.equals("biz")) { // 사업자가 탈퇴할 때 실행한다.
					cnt = mdao.deleteAllMyStore(id) ; // 가게테이블에서 탈퇴하는 회원의 "가게"를 모두 삭제하는 메서드
				}
				cnt = mdao.deleteData(id) ; // 회원테이블에서 회원 본인을 삭제하는 메서드
			
				
			super.session.invalidate(); // 세션에 저장된 로그인 정보를 삭제한다.
			new MemberLoginController().doGet(request, response); // 로그인페이지로 이동한다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
