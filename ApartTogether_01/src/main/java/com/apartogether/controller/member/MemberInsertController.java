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
		bean.setNickname(request.getParameter("nickname"));
		bean.setAddress(request.getParameter("address"));
		bean.setProfile(request.getParameter("profile")); 
		// profile 문자열만 DB에 입력되고 파일업로드는 안되는 문제있음. 
		// image 폴더에 이미 들어있는 사진과 같은 이름이면 표시되지만 그 외는 안뜸
		// MemberUpdateController처럼 MultipartRequest로 이미지 업로드 구현해야 할 지 고민
		
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