package com.apartogether.controller.member;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;

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
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr") ;
		Member bean = new Member();
		MemberDao dao = new MemberDao() ;
		
		bean.setId(mr.getParameter("id"));
		bean.setMtype(mr.getParameter("mtype"));
		bean.setName(mr.getParameter("name"));
		bean.setPassword(mr.getParameter("password"));
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setGender(mr.getParameter("gender"));
		
		/* [st] 닉네임 랜덤 생성 */
		// meInsertForm에서 멀티파트로 바꿨기 때문에 닉네임 생성부분을 수정했습니다.
		// 수정내용 : != 를 .equels()식으로 수정했습니다. // 정상작동 확인했습니다.
		if(mr.getParameter("nickname").equals("") ) {
			bean.setNickname(MemberDao.RandomName());
			setAlertMessage(bean.getNickname() + " 으로 닉네임이 랜덤 생성되었습니다. 환영합니다!");
		}else  {
			bean.setNickname(mr.getParameter("nickname"));
			setAlertMessage(bean.getName() + "님 환영합니다!");
		}/* [ed] 닉네임 랜덤 생성 */
		
		bean.setAddress(mr.getParameter("address") + "Δ"
				+ mr.getParameter("address_detail"));/* 주소(카카오API값) + 상세주소(사용자가 입력하는값) */
//		bean.setAddress(mr.getParameter("address") + " "
//				+ mr.getParameter("address_detail"));/* 주소(카카오API값) + 상세주소(사용자가 입력하는값) */
		bean.setProfile(mr.getFilesystemName("profile"));
		bean.setPasswordanswer(mr.getParameter("passwordanswer"));
		bean.setPasswordquest(mr.getParameter("passwordquest"));
		
		// gotoStoreInsert : 회원타입을 사업자로 선택한 경우 <내 가게 등록 화면>으로 이동할지 묻는 컨펌창의 결과를 저장합니다.(yes/no)
		String gotoStoreInsert = mr.getParameter("gotoStoreInsert"); //  yes이면 <내 가게 등록 화면>으로 이동합니다.
		
		int cnt = -1 ;
		try {
			cnt = dao.InsertData(bean) ;
			if(cnt == -1) { // 가입 실패
				new MemberInsertController().doGet(request, response);
				
			}else { // 가입 성공
				if(gotoStoreInsert.equals("yes")) {//  yes이면 <내 가게 등록 화면>으로 이동합니다.
					// 임시로 meList으로 가게 해두었습니다. 나중에 꼭 수정해주세요. @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					// 자동로그인기능 여기 추가하세요
					String gotopage = super.getUrlInfomation("stInsert"); 
//					gotopage += "&id=" + mr.getParameter("id");
					response.sendRedirect(gotopage);
					// 자동로그인이 되는지 꼭 확인해 주세요.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				}else {
					new MemberLoginController().doPost(request, response);
				}
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