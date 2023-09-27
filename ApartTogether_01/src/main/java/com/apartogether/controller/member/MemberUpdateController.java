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
		
		// Δ를 기준으로 주소를 나눈다.
		String[] addressSet = bean.getAddress().split("Δ");
		request.setAttribute("addressSet", addressSet);
		// -를 기준으로 생일을 나눈다.
		String[] birthSet = bean.getBirth().split("-");
		request.setAttribute("birthSet", birthSet);
		
		super.gotoPage(PREFIX + "meUpdateForm.jsp"); 
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception { // 작성중입니다.
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr") ;
		Member bean = new Member();
		MemberDao dao = new MemberDao();
		
		// [ST] 컨펌창 결과 처리 
		String gotoStoreInsert = mr.getParameter("gotoStoreInsert"); 
		String changeBizToUser = mr.getParameter("changeBizToUser"); 
		String oldmtype =  mr.getParameter("oldmtype"); // 기존 DB에 저장되있던 mtype
		String mtype =  mr.getParameter("mtype"); // 사용자가 선택한 mtype
		if(oldmtype.equals("biz")) { 
			if(mtype.equals("biz")) { // 사업자->사업자
				bean.setMtype(mr.getParameter("mtype"));
				super.setSuccessAlertMessage("사장님! 수정 완료되었습니다.");
			}else if(mtype.equals("user")) { // 사업자->일반회원
				if(changeBizToUser.equals("yes")){
					dao.deleteAllMyStore(mr.getParameter("id"));// 사업자가 가지고 있던 가게들을 모두 삭제합니다.
					bean.setMtype(mr.getParameter("mtype")); // 일반회원으로 변경해줍니다.
					setAlertMessage("회원유형이 사업자에서 일반회원으로 변경되었습니다.");
				}else{
					bean.setMtype(mr.getParameter("oldmtype")); //  사업자로 유지합니다.
					setAlertMessage("회원유형을 사업자로 유지합니다.");
				}
			}
		}else if(oldmtype.equals("user")) {
			if(mtype.equals("user")) { // 일반회원->일반회원 : 알럿창(수정완료)띄우고 마이페이지로 이동
				bean.setMtype(mr.getParameter("mtype"));
				super.setSuccessAlertMessage("회원님! 수정 완료되었습니다.");
			}else if(mtype.equals("biz")) { // 일반회원->사업자
				if(gotoStoreInsert.equals("yes")){ // 컨펌창 yes '내 가게등록 페이지'로 이동
				}else{ // 컨펌창 no 마이페이지로 이동
					setAlertMessage("회원유형이 일반회원에서 사업자로 변경되었습니다.");
				}
			}
		}
		// [ED] 컨펌창 결과 처리
		
		bean.setId(mr.getParameter("id"));
		bean.setName(mr.getParameter("name"));
		bean.setNickname(mr.getParameter("nickname"));
		if(mr.getFilesystemName("profile")==null) { // 회원정보 수정시 프로필사진을 건들이지 않았으면 그대로 유지한다. 
			bean.setProfile(mr.getParameter("deleteProfile"));
		}else {
			bean.setProfile(mr.getFilesystemName("profile"));
		}
		bean.setPassword(mr.getParameter("password"));
		bean.setGender(mr.getParameter("gender"));
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setAddress(mr.getParameter("address")+"Δ"+mr.getParameter("address_detail"));
		bean.setPasswordquest(mr.getParameter("passwordquest"));
		bean.setPasswordanswer(mr.getParameter("passwordanswer"));
		
		
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean); // DB에 업데이트합니다.
			
			if(cnt == -1) { // DB 업데이트 실패
				super.gotoPage(PREFIX + "meUpdateForm.jsp");
			}else { // DB 업데이트 성공
				// gotoStoreInsert값에 따라 마이페이지로 갈것인가, 가게등록페이지로 갈것인가? 
				if(gotoStoreInsert.equals("yes")) { // <가게등록페이지>로 이동합니다.
					// 임시로 home으로 가게 해두었습니다. 나중에 꼭 수정해주세요. //////////////////////////
					String gotopage = super.getUrlInfomation("stInsert"); 
					response.sendRedirect(gotopage);
				}else if(gotoStoreInsert.equals("no")) { // 마이페이지로 이동합니다.
					String gotopage = super.getUrlInfomation("meDetail");
					gotopage += "&id=" + mr.getParameter("id");
					response.sendRedirect(gotopage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
