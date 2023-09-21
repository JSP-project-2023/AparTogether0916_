package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

public class MemberFindPasswordController extends SuperClass {
private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meFindPassword.jsp");		
	}
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		String name = request.getParameter("name") ;
		String id = request.getParameter("id") ;
		String passwordanswer = request.getParameter("passwordanswer") ;
		String passwordquest = request.getParameter("passwordquest") ;
		System.out.println(name + "/" + id + "/" + passwordanswer + "/" + passwordquest);
		
		MemberDao dao = new MemberDao() ;
		Member bean = null ;
		
		try {
			bean = dao.findPassword(name, id, passwordanswer, passwordquest);
			
			
			if(bean == null) { // 찾기 실패
				String message = "사용자 정보가 잘못 되었습니다. 분실시 관리자에게 연락바랍니다.";
				super.setAlertMessage(message) ;
				super.gotoPage(PREFIX + "meFindPassword.jsp");
				
			}else { // 로그인 성공
				request.setAttribute("bean", bean) ;
				// session 영역에 나의 로그인 정보를 저장합니다.
				String result = bean.getPassword();
				super.setAlertMessage(bean.getId() + "님의 로그인 패스워드는 [ " + result + " ] 입니다.") ;
				super.gotoPage(PREFIX + "meLoginForm.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}