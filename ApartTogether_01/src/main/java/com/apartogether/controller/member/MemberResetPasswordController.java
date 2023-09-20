package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

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
		
		//Member bean = new Member();
		String id = request.getParameter("id");
		
		// meResetPassword.jsp 에서 입력받은 기존 비밀번호, 새 비밀번호, 새 비밀번호 재입력 파라메타를 가져옵니다.
		String oldPassword = request.getParameter("oldPassword") ; // 기존 비밀번호
		String newPassword = request.getParameter("newPassword") ; // 새 비밀번호
		String newPasswordCheck = request.getParameter("newPasswordCheck"); // 새 비밀번호 입력 확인용
		
		//bean.setPassword(newPassword);
		
		MemberDao dao = new MemberDao();
		int cnt01 = -1;
		int cnt02 = -1;
		
		// step 01 : 입력받은 기존비밀번호(oldPassword)가 올바른지 비교합니다. dao.CheckOldPassword
		// step 02 : DB에서 password를 newPassword로 update합니다.
		
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
//		try {
//			cnt = dao.UpdatePassword(id, oldPassword, newPassword); // DB에서 password를 update합니다.
//			
//			if(cnt == -1) { // 등록 실패
//				setAlertMessage("입력하신 비밀번호를 다시 확인해주세요.");
//				new MemberResetPasswordController().doGet(request, response);
//			}else {// 성공
//				// 마이페이지(meDetail) 페이지로 이동
//				// 마이페이지로 이동하기 위하여 id 관련 파라미터도 넘겨줍니다.
//				String gotopage = super.getUrlInfomation("meDetail");
//				gotopage += "&id=" + request.getParameter("id");
//				response.sendRedirect(gotopage);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
	}
}
