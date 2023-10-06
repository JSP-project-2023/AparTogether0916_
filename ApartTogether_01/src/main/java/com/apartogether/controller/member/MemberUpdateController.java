package com.apartogether.controller.member;

import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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
		
		//[ST]비밀번호 복호화
			// AES 암호화를 위한 키와 IV
			// id값으로 secretDao에서 key와 iv값을 가져옴
	        String key = "mySecretKey12345"; // 16 바이트(128비트)
	        String iv = "myInitialization"; // 16 바이트
			String password = bean.getPassword();
			String decryptedPassword = decryptAES(password, key, iv);
			request.setAttribute("decryptedPassword", decryptedPassword) ;
		//[ED]비밀번호 복호화
		
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
				bean.setMtype(mr.getParameter("mtype"));
				if(gotoStoreInsert.equals("yes")){ // 컨펌창 yes '내 가게등록 페이지'로 이동
				}else{ // 컨펌창 no 마이페이지로 이동
					setAlertMessage("회원유형이 일반회원에서 사업자로 변경되었습니다.");
				}
			}
		}
		// [ED] 컨펌창 결과 처리
		
		bean.setId(mr.getParameter("id"));
		bean.setName(mr.getParameter("name"));

		/* [st] 닉네임 랜덤 생성 */
		if (mr.getParameter("nickname").equals("")) {
			bean.setNickname(MemberDao.RandomName());
		} else {
			bean.setNickname(mr.getParameter("nickname"));
		} /* [ed] 닉네임 랜덤 생성 */
		
		if(mr.getFilesystemName("profile")==null) { // 회원정보 수정시 프로필사진을 건들이지 않았으면 그대로 유지한다. 
			bean.setProfile(mr.getParameter("deleteProfile"));
		}else {
			bean.setProfile(mr.getFilesystemName("profile"));
		}
		
		//[ST]비밀번호 암호화
			// AES 암호화를 위한 키와 IV
	        String key = "mySecretKey12345"; // 16 바이트(128비트)
	        String iv = "myInitialization"; // 16 바이트
			String password = mr.getParameter("password");
			String encryptedPassword = encryptAES(password, key, iv);
			bean.setPassword(encryptedPassword);
		//[ED]비밀번호 암호화
			
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
				setAlertMessage("회원정보 수정에 실패하였습니다.");
				super.gotoPage(PREFIX + "meUpdateForm.jsp");
			}else { // DB 업데이트 성공
				// session 영역에 나의 로그인 정보를 갱신합니다.
				super.session.setAttribute("loginfo", bean);
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
	public static String encryptAES(String plaintext, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptAES(String encryptedText, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}
