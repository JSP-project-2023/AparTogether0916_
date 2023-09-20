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
		
		String oldmtype =  mr.getParameter("oldmtype"); // 수정 전에 회원이 사업자였는지 확인하기 위해 기존mtype값을 가져온다.
		String mtype =  mr.getParameter("mtype"); // 회원이 회원정보수정 페이지에서 선택한 회원유형을 가져옵니다.
		
//		if(oldmtype == "biz") { // 수정 전에 사업자 였음
//			if(mtype == "biz") {
//				//alert("수정 완료되었습니다.");
//				// 사업자를 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
//				bean.setMtype(mr.getParameter("mtype"));
//			}else if(mtype == "user") {
//				// 사업자가 일반회원으로 변경한 거면 컨펌창(내가게 다 사라집니다)
//				// 컨펌창 yes 알럿창(수정완료)띄우고 마이페이지로 이동
//				// 컨펌창 no 사업자로 유지
//				bean.setMtype(oldmtype);
//			}
//		}else if(oldmtype == "user") { // 수정 전에 일반회원 이었음
//			if(mtype == "user") {
//				// 일반회원을 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
//				bean.setMtype(mr.getParameter("mtype"));
//			}else if(mtype == "biz") {
//				// 일반회원이 사업자로 변경한 거면 컨펌창(내가게 등록하러 가시겠습니까?)
//				// 컨펌창 yes '내 가게등록 페이지'로 이동
//				// 컨펌창 no 마이페이지로 이동
//				bean.setMtype(mr.getParameter("mtype"));
//			}
//		}
		
		bean.setMtype(mr.getParameter("mtype")); // 컨펌창 결과에 따라 달라질 수 있습니다.
		
		
		bean.setName(mr.getParameter("name"));
		bean.setNickname(mr.getParameter("nickname"));
		
		// 회원정보 수정시 프로필사진을 새 파일로 선택했을 때만 갱신한다. 건드리지않았으면 기존 사진을 유지한다.
		if(mr.getFilesystemName("profile")==null) {
			bean.setProfile(mr.getParameter("deleteProfile"));
		}else {
			bean.setProfile(mr.getFilesystemName("profile"));
		}
		
		bean.setPassword(mr.getParameter("password"));
		bean.setGender(mr.getParameter("gender"));
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setAddress(mr.getParameter("address"));
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean); // DB에 업데이트합니다.
			
			if(cnt == -1) { // DB 업데이트 실패
				super.gotoPage(PREFIX + "meUpdateForm.jsp");
			}else { // DB 업데이트 성공
				String gotopage = super.getUrlInfomation("meDetail");
				gotopage += "&id=" + mr.getParameter("id");
				response.sendRedirect(gotopage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
