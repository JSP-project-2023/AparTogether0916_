package com.apartogether.controller.member;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;

// 회원 가입을 위한 컨트롤러 입니다.
public class MemberInsertController extends SuperClass {
	private final String PREFIX = "member/";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meInsertForm.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		MultipartRequest mr = (MultipartRequest) request.getAttribute("mr");
		Member bean = new Member();
		MemberDao dao = new MemberDao();

		bean.setId(mr.getParameter("id"));
		bean.setMtype(mr.getParameter("mtype"));
		bean.setName(mr.getParameter("name"));
		
		//[ST]비밀번호 암호화
		    String password = mr.getParameter("password");
		    // AES 암호화를 위한 키와 IV
	        String key = "mySecretKey12345"; // 16 바이트(128비트)
	        String iv = "myInitialization"; // 16 바이트
	        // AES 암호화
	        String encryptedPassword = encryptAES(password, key, iv);
			bean.setPassword(encryptedPassword);
		//[ED]비밀번호 암호화
		
		bean.setPhone(mr.getParameter("phone"));
		bean.setBirth(mr.getParameter("birth"));
		bean.setGender(mr.getParameter("gender"));

		/* [st] 닉네임 랜덤 생성 */
		if (mr.getParameter("nickname").equals("")) {
			bean.setNickname(MemberDao.RandomName());
		} else {
			bean.setNickname(mr.getParameter("nickname"));
		} /* [ed] 닉네임 랜덤 생성 */

		bean.setAddress(mr.getParameter("address") + "Δ" 
				+ mr.getParameter("address_detail")); /* 주소(카카오API값) + 상세주소(사용자가 입력하는값) */
		bean.setProfile(mr.getFilesystemName("profile"));
		bean.setPasswordanswer(mr.getParameter("passwordanswer"));
		bean.setPasswordquest(mr.getParameter("passwordquest"));

		// gotoStoreInsert : 회원타입을 사업자로 선택한 경우 <내 가게 등록 화면>으로 이동할지 묻는 컨펌창의 결과를
		// 저장합니다.(yes/no)
		String gotoStoreInsert = mr.getParameter("gotoStoreInsert"); // yes이면 <내 가게 등록 화면>으로 이동합니다.

		int cnt = -1;
		try {
			cnt = dao.InsertData(bean);
			if (cnt == -1) { // 가입 실패
				new MemberInsertController().doGet(request, response);
			}else { // 가입 성공
				if (mr.getParameter("nickname").equals("")) {
					setSuccessAlertMessage(bean.getNickname() + " 으로 닉네임이 랜덤 생성되었습니다. 환영합니다!");
				} else {
					setSuccessAlertMessage(bean.getName() + "님 환영합니다!");
				}
				if(gotoStoreInsert.equals("yes")) {//  yes이면 <내 가게 등록 화면>으로 이동합니다.
					// [ST]자동로그인기능
					String id = mr.getParameter("id") ;
					System.out.println(id + "/" + encryptedPassword);
					try {bean = dao.getDataByPk(id, encryptedPassword);
					} catch (Exception e) {e.printStackTrace();}
					super.session.setAttribute("loginfo", bean);
					// [ED]자동로그인기능
					String gotopage = super.getUrlInfomation("stInsert"); 
					response.sendRedirect(gotopage);
				}else {
					new MemberLoginController().doPost(request, response); // 일반회원으로 가입했을경우, 컨펌창no
				}
			}
		} catch (SQLIntegrityConstraintViolationException e) { /* member - pk(id)중복 발생 시 */
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