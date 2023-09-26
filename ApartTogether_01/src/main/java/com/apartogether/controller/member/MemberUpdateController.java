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
		
		// gotoStoreInsert : 일반회원에서 사업자로 변경하는 경우 <가게등록페이지>로 갈 것인지 묻는 컴펌창의 결과를 저장합니다. .
		String gotoStoreInsert = mr.getParameter("gotoStoreInsert"); 
		// changeBizToUser : 사업자에서 일반회원으로 변경하려는 경우 컨펌창의 결과(true/false)에 따라 ("yes"/"no")의 값을 가집니다. .
		String changeBizToUser = mr.getParameter("changeBizToUser"); 
		if(changeBizToUser.equals("yes")) {
			dao.deleteAllMyStore(mr.getParameter("id"));// 사업자가 가지고 있던 가게들을 모두 삭제합니다.
			bean.setMtype(mr.getParameter("mtype")); // 일반회원으로 변경해줍니다.
		}else if(changeBizToUser.equals("no")) {
			bean.setMtype(mr.getParameter("oldmtype")); //  사업자로 유지합니다.
		}
		
		String oldmtype =  mr.getParameter("oldmtype"); // 기존 mtype
		String mtype =  mr.getParameter("mtype"); // 새 mtype
		// [ST] 컨펌창 결과 처리
		if(oldmtype.equals("biz")) { // 수정 전에 사업자 이었음
				if(mtype.equals("biz")) { // 사업자를 그대로 유지
					super.setSuccessAlertMessage("사장님! 수정 완료되었습니다.");
				}else if(mtype.equals("user")) { // 사업자가 일반회원으로 변경
					if(changeBizToUser.equals("yes")){// 컨펌창 yes 
						// deleteStore(); //내 가게를 삭제하는 작업이 필요합니다.
						setAlertMessage("회원유형이 사업자에서 일반회원으로 변경되었습니다.");
					}else{// 컨펌창 no 사업자로 유지
						setAlertMessage("회원유형을 사업자로 유지합니다.");
					}
				}
			}else if(oldmtype.equals("user")) { // 수정 전에 일반회원 이었음
				if(mtype.equals("user")) { // 일반회원을 그대로 유지하면 알럿창(수정완료)띄우고 마이페이지로 이동
					super.setSuccessAlertMessage("회원님! 수정 완료되었습니다.");
				}else if(mtype.equals("biz")) { // 일반회원이 사업자로 변경한 거면 컨펌창(내가게 등록하러 가시겠습니까?)
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
		if(mr.getFilesystemName("profile")==null) { // 회원정보 수정시 프로필사진 선택했을 때만 갱신한다.
			bean.setProfile(mr.getParameter("deleteProfile"));
		}else { // 회원정보 수정시 프로필사진을 안 건드렸으면 그대로 유지한다. 
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
