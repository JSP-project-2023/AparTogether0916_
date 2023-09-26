package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;


public class MemberDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		Member bean = null;
		
		try {
			bean = dao.getDataByPrimaryKey(id);
			System.out.println(bean.toString());
			
			// Δ를 기준으로 주소를 나눈다.
			String[] addressSet = bean.getAddress().split("Δ");
			request.setAttribute("addressSet", addressSet);
			// -를 기준으로 생일을 나눈다.
			String[] birthSet = bean.getBirth().split("-");
			request.setAttribute("birthSet", birthSet);
			
			if(bean == null) {
				super.setAlertMessage("잘못된 회원 정보입니다.");
				super.gotoPage("common/home.jsp");
			}else {
				request.setAttribute("bean", bean);
				super.gotoPage("member/meDetail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
