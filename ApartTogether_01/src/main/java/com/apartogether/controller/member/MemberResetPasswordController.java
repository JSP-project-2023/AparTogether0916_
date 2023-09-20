package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.shopping.controller.board.BoardUpdateController;

public class MemberResetPasswordController extends SuperClass {
	private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		Member bean = null;
		
		try {
			bean = dao.getDataByPrimaryKey(id);
			
			if(bean == null) {
				super.setAlertMessage("잘못된 회원 정보입니다.");
				super.gotoPage("common/home.jsp");
			}else {
				request.setAttribute("bean", bean);
				super.gotoPage(PREFIX + "meResetPassword.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Member bean = new Member();
		
		// meResetPassword.jsp 에서 입력받은 기존 비밀번호, 새 비밀번호, 새 비밀번호 재입력 파라메타를 가져옵니다.
		String oldPassword = request.getParameter("oldPassword") ;
		String newPassword = request.getParameter("newPassword") ;
		String newPasswordCheck = request.getParameter("newPasswordCheck");
		
		bean.setPassword(newPassword);
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		try {
			cnt = dao.UpdatePassword(bean);
			
			if(cnt == -1) { // 등록 실패
				new MemberResetPasswordController().doGet(request, response);
			}else {// 성공
				// 마이페이지(meDetail) 페이지로 이동
				// 마이페이지로 이동하기 위하여 id 관련 파라미터도 넘겨줍니다.
				String gotopage = super.getUrlInfomation("meDetail");
				gotopage += "&id=" + request.getParameter("id");
				response.sendRedirect(gotopage);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
