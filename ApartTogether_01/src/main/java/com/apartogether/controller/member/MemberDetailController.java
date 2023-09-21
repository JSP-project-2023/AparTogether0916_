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
		
		String wid = request.getParameter("whologin_id"); 
		System.out.println("whologin_id : "+ wid);
		String id = request.getParameter("id");
		System.out.println("here : "+ id);
		MemberDao dao = new MemberDao();
		Member bean = null;
		
		try {
			bean = dao.getDataByPrimaryKey(id);
			
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
