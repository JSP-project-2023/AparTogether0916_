package com.apartogether.controller.member;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.HomeController;
import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;

public class MemberLoginController extends SuperClass{
	private final String PREFIX = "member/" ;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		super.doGet(request, response);
		super.gotoPage(PREFIX + "meLoginForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		// 회원인서트에서 프로필이미지 업로드때문에 MultipartRequest 로 바꿔서 여기도 바꿨습니다. //로그인동작확인 완료
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr") ;
		
		String id = "";
		String password = "";
		if(mr == null) { 
			id = request.getParameter("id") ;
			password = request.getParameter("password") ;
			//password = request.getParameter("hashedPassword") ;
		}else {
			id = mr.getParameter("id") ;
			password = mr.getParameter("password") ;
			//password = mr.getParameter("hashedPassword") ;
		}
		
		//[ST]비밀번호 암호화
			// AES 암호화를 위한 키와 IV
	        String key = "mySecretKey12345"; // 16, 24 또는 32 바이트
	        String iv = "myInitialization"; // 16 바이트
	        // AES 암호화
	        String encryptedPassword = encryptAES(password, key, iv);
        //[ED]비밀번호 암호화
        
        System.out.println(id + "/" + encryptedPassword);
		
		MemberDao dao = new MemberDao() ;
		Member bean = null ; 
		
		try {
			bean = dao.getDataByPk(id, encryptedPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 if(bean == null) { // 로그인 실패 
			 String message = "로그인 정보가 잘못 되었습니다.";
			 super.setAlertMessage(message) ;
			  
			 super.gotoPage(PREFIX + "meLoginForm.jsp");
		  
		 }else { // 로그인 성공 
			 System.out.println("로그인성공");
		  
			 // session 영역에 나의 로그인 정보를 저장합니다. 
			 super.session.setAttribute("loginfo", bean);
			  
			 // 홈 화면으로 이동합니다. 차후 상품 목록 페이지로 갈 예정 
			 new HomeController().doGet(request,response) ; }
				 
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