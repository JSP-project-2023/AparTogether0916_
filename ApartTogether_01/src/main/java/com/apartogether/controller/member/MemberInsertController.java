package com.apartogether.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

// 회원 가입을 위한 컨트롤러 입니다.
public class MemberInsertController extends SuperClass {
	private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meInsertForm.jsp");		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		Member bean = new Member();

		bean.setId(request.getParameter("id"));
		bean.setMtype(request.getParameter("mtype"));
		bean.setName(request.getParameter("name"));
		bean.setPassword(request.getParameter("password"));
		bean.setPhone(request.getParameter("phone"));
		bean.setBirth(request.getParameter("birth"));
		bean.setGender(request.getParameter("gender"));
		/* [st] 닉네임 랜덤 생성 */
		bean.setNickname(request.getParameter("nickname"));
		/* bean.setNickname(MemberDao.nName()); //동작 확인완료! */
		
		if (bean.getNickname() != "") { /* 아놔 ㅋㅋㅋ null값이 아닌 ""값을 가진다.ㅋㅋㅋ */
			bean.setNickname(request.getParameter("nickname"));
			setAlertMessage(bean.getName() + "님 환영합니다!");
		} else {
			bean.setNickname(MemberDao.RandomName());
			setAlertMessage(bean.getNickname() + " 으로 닉네임이 랜덤 생성되었습니다. 환영합니다!");
		}
		/* [ed] 닉네임 랜덤 생성 */
		bean.setAddress(request.getParameter("address") + " "
				+ request.getParameter("address_detail"));/* 주소(카카오API값) + 상세주소(사용자가 입력하는값) */
		bean.setProfile(request.getParameter("profile"));
		
		MemberDao dao = new MemberDao() ;
		int cnt = -1 ;
		try {
			cnt = dao.InsertData(bean) ;
			if(cnt == -1) { // 가입 실패
				new MemberInsertController().doGet(request, response);
				
			}else { // 가입 성공
				new MemberLoginController().doPost(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new MemberInsertController().doGet(request, response);
		}
	}
}