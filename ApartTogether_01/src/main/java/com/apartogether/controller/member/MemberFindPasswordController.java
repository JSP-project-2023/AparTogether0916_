package com.apartogether.controller.member;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;

public class MemberFindPasswordController extends SuperClass {
private final String PREFIX = "member/" ;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meFindPassword.jsp");		
	}
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		String name = request.getParameter("name") ;
		String id = request.getParameter("id") ;
		String passwordanswer = request.getParameter("passwordanswer") ;
		String passwordquest = request.getParameter("passwordquest") ;
		System.out.println(name + "/" + id + "/" + passwordanswer + "/" + passwordquest);
		
		MemberDao dao = new MemberDao() ;
		Member bean = null ;
		String gotopage = "gotopagePW";/* meFindResult.jsp에서 출력값, 분기처리를 위한 변수 */
		try {
			bean = dao.findPassword(name, id, passwordanswer, passwordquest);
			
			if(bean == null) { // 찾기 실패
				String message = "입력하신 정보와 일치하는 회원정보를 찾을 수 없습니다. 비밀번호 분실시 관리자에게 연락바랍니다.";
				super.setAlertMessage(message) ;
				super.gotoPage(PREFIX + "meFindPassword.jsp");
				
			}else { // 로그인 성공
				//[ST]비밀번호 복호화
					String password = bean.getPassword();
					// AES 암호화를 위한 키와 IV
			        String key = "mySecretKey12345"; // 16, 24 또는 32 바이트(AES 128, 192, 256)
			        String iv = "myInitialization"; // 16 바이트
			        // AES 복호화
			        String decryptedPassword = decryptAES(password, key, iv);
			        request.setAttribute("decryptedPassword", decryptedPassword) ;
		        //[ED]비밀번호 복호화
		        
				request.setAttribute("bean", bean) ;
				// session 영역에 나의 로그인 정보를 저장합니다.
				request.setAttribute("gotopage", gotopage);/* meFindResult.jsp에서 출력값, 분기처리를 위한 변수 */
				String result = bean.getPassword();
				//super.setAlertMessage(bean.getId() + "님의 로그인 패스워드는 [ " + result + " ] 입니다.") ;
				super.gotoPage(PREFIX + "meFindResult.jsp");
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
