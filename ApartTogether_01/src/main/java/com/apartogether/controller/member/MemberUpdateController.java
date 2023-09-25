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
		
		String oldmtype =  mr.getParameter("oldmtype"); // 수정 전에 회원이 사업자였는지 확인하기 위해 기존mtype값을 가져온다.
		String mtype =  mr.getParameter("mtype"); // 회원이 회원정보수정 페이지에서 선택한 회원유형을 가져옵니다.
		
		// changeBizToUser : mtype을 변경했을 경우 띄우는 컨펌창의 결과(true=yes, false-no)입니다.
		// 사업자에서 일반회원으로 변경하려는 경우 컨펌창의 결과(true/false)에 따라 ("yes"/"no")의 값을 가집니다. .
		String changeBizToUser = mr.getParameter("changeBizToUser"); 
		if(changeBizToUser.equals("yes")) {
			bean.setMtype(mr.getParameter("mtype"));
		}else if(changeBizToUser.equals("no")) {
			bean.setMtype(oldmtype); // 사업자에서 일반회원으로 변경하려다가, 컨펌창에서 false(no)하면, 사업자로 유지합니다.
		}
		
		
		// gotoStoreInsert : 일반회원에서 사업자로 변경하는 경우 <가게등록페이지>로 갈 것인지 묻는 컴펌창의 결과를 저장합니다. .
		String gotoStoreInsert = mr.getParameter("gotoStoreInsert"); 
				
		bean.setId(mr.getParameter("id"));
		bean.setName(mr.getParameter("name"));
		bean.setNickname(mr.getParameter("nickname"));
		
		if(mr.getFilesystemName("profile")==null) { // 회원정보 수정시 프로필사진 선택했을 때만 갱신한다.
			bean.setProfile(mr.getParameter("deleteProfile"));
		}else { // 회원정보 수정시 프로필사진을 안 건드렸으면 그대로 유지한다. 
			// 파일명은(회원아이디_파일이름.png)식으로 저장합니다.
			bean.setProfile(mr.getFilesystemName("profile"));
		}
		bean.setPassword(mr.getParameter("password"));
		bean.setGender(mr.getParameter("gender"));
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setAddress(mr.getParameter("address"));
		bean.setPasswordquest(mr.getParameter("passwordquest"));
		bean.setPasswordanswer(mr.getParameter("passwordanswer"));
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		try {
			cnt = dao.UpdateData(bean); // DB에 업데이트합니다.
			
			if(cnt == -1) { // DB 업데이트 실패
				super.gotoPage(PREFIX + "meUpdateForm.jsp");
			}else { // DB 업데이트 성공
				//마이페이지로 갈것인가, 가게등록페이지로 갈것인가? 
				if(gotoStoreInsert.equals("yes")) {
					// <가게등록페이지>로 이동합니다.
					// 임시로 home으로 가게 해두었습니다. 나중에 꼭 수정해주세요. //////////////////////////
					String gotopage = super.getUrlInfomation("home"); 
//					String gotopage = super.getUrlInfomation("insertStore"); 
//					gotopage += "&id=" + mr.getParameter("id");
					response.sendRedirect(gotopage);
				}else if(gotoStoreInsert.equals("no")) {
					// 마이페이지로 이동합니다.
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
