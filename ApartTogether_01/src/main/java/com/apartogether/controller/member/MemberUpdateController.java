package com.apartogether.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;

public class MemberUpdateController extends SuperClass  {
private final String PREFIX = "member/";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String id = request.getParameter("id");
		
		MemberDao dao = new MemberDao();
		Member bean = dao.getDataByPrimaryKey(id);
		request.setAttribute("bean", bean);
		System.out.println(bean.toString());
		
		super.gotoPage(PREFIX + "meUpdateForm.jsp"); 
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception { // 작성중입니다.
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr") ;
		
		Member bean = new Member();
		
		bean.setId(mr.getParameter("id"));
		bean.setMtype(mr.getParameter("mtype"));
		bean.setName(mr.getParameter("name"));
		bean.setNickname(mr.getParameter("nickname"));
		bean.setProfile(mr.getFilesystemName("profile"));
		bean.setPassword(mr.getParameter("password"));
		bean.setGender(mr.getParameter("gender"));
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setAddress(mr.getParameter("address"));
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt == -1) {
				super.gotoPage(PREFIX + "meUpdateForm.jsp");
			}else {
//				new MemberDetailController().doGet(request, response);
				String gotopage = super.getUrlInfomation("meDetail");
				gotopage += "&id=" + mr.getParameter("id");
				response.sendRedirect(gotopage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
