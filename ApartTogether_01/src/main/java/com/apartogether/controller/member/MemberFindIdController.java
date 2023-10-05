package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.HomeController;
import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

public class MemberFindIdController extends SuperClass{
	private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meFindId.jsp");		
	}
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		
		String name = request.getParameter("name") ;
		String phone = request.getParameter("phone") ;
		String birth = request.getParameter("birth") ;
		System.out.println(name + "/" + phone + "/" + birth);
		
		MemberDao dao = new MemberDao() ;
		Member bean = null ;
		String gotopage = "gotopageID";/* meFindResult.jsp에서 출력값, 분기처리를 위한 변수 */
		
		try {
			bean = dao.findId(name, phone, birth);
			
			
			if(bean == null) { // 찾기 실패
				String message = "입력하신 정보와 일치하는 회원정보를 찾을 수 없습니다.";
				super.setAlertMessage(message) ;
				super.gotoPage(PREFIX + "meFindId.jsp");
				
			}else { // 로그인 성공
				request.setAttribute("bean", bean) ;
				// session 영역에 나의 로그인 정보를 저장합니다.
				request.setAttribute("gotopage", gotopage);/* meFindResult.jsp에서 출력값, 분기처리를 위한 변수 */
				String result = bean.getId();
				request.setAttribute("result", result);
<<<<<<< HEAD
				/* super.setAlertMessage(bean.getName() + "님의 로그인 id는 " + result + " 입니다.") ; */
=======
				//super.setAlertMessage(bean.getName() + "님의 로그인 id는 " + result + " 입니다.") ;
>>>>>>> origin/member_merge_01_yh33
				super.gotoPage(PREFIX + "meFindResult.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}












