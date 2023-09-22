package com.apartogether.controller.member;

import java.sql.SQLIntegrityConstraintViolationException;

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
		
		if (bean.getNickname() != "") { /* null값이 아닌 ""값을 가진다. */
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
		bean.setPasswordanswer(request.getParameter("passwordanswer"));
		bean.setPasswordquest(request.getParameter("passwordquest"));
		
		// 회원가입할 때 profile 파일이름이 문자열만 DB에 입력되고 파일업로드는 안되는 문제있음. 
		// image 폴더에 이미 들어있는 사진과 같은 이름이면 표시되지만 그 외는 안뜸
		// MemberUpdateController처럼 MultipartRequest로 이미지파일까지 /upload에 업로드되도록 수정해야 할 지 고민

		
		MemberDao dao = new MemberDao() ;
		int cnt = -1 ;
		try {
			cnt = dao.InsertData(bean) ;
			if(cnt == -1) { // 가입 실패
				new MemberInsertController().doGet(request, response);
				
			}else { // 가입 성공
				new MemberLoginController().doPost(request, response);
			}
		} catch (SQLIntegrityConstraintViolationException e) { /* member - pk(id)중복 발생 시  */
			e.printStackTrace();
			System.out.println("pk중복 발생");
			bean.setNickname("");
			setAlertMessage(" 해당 아이디는 중복입니다.");
			new MemberInsertController().doGet(request, response);/* 회원가입창으로 넘어감 */
			} catch (Exception e) {
			e.printStackTrace();
			new MemberInsertController().doGet(request, response);
		}
	}
}