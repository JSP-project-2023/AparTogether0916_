package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.HomeController;
import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;

public class MemberLoginController extends SuperClass{
	private final String PREFIX = "member/" ;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meLoginForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 회원인서트에서 프로필이미지 업로드때문에 MultipartRequest 로 바꿔서 여기도 바꿨습니다. //로그인동작확인 완료
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr") ;
		
		String id = "";
		String password = "";
		if(mr == null) { 
			id = request.getParameter("id") ;
			password = request.getParameter("password") ;
		}else {
			id = mr.getParameter("id") ;
			password = mr.getParameter("password") ;
		}
		System.out.println(id + "/" + password);
		
		MemberDao dao = new MemberDao() ;
		Member bean = null ; 
		
		try {
			bean = dao.getDataByPk(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean == null) { // 로그인 실패
			String message = "로그인 정보가 잘못 되었습니다.";
			super.setAlertMessage(message) ;
			
			super.gotoPage(PREFIX + "meLoginForm.jsp");
			
		}else { // 로그인 성공
			System.out.println("로그인성공");
			
			// session 영역에 나의 로그인 정보를 저장합니다.
			super.session.setAttribute("loginfo", bean);
			
			// 홈 화면으로 이동합니다. 차후 상품 목록 페이지로 갈 예정
			new HomeController().doGet(request, response) ;
		}
	}
}











