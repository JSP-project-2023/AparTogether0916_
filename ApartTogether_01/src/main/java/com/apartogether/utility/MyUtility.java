package com.apartogether.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.apartogether.controller.SuperController;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyUtility {

	public static void moveFolderProfileImage(String webPathFrom, String webPathTo, MultipartRequest mr) {
		// FrontController.doProcess에서 사용합니다.
		// 회원정보 수정시 webPathFrom폴더로 업로드한 프로필이미지를
		// webPathTo폴더로 이동시킵니다.
		String profileFileName = mr.getFilesystemName("profile");
		File file = new File(webPathFrom + "/"+ profileFileName);
	        File fileToMove = new File(webPathTo + "/"+ profileFileName);
		System.out.println("move profile From : " + webPathFrom + "/"+ profileFileName);
		System.out.println("move profile To : " + webPathTo + "/"+ profileFileName);
		
	        boolean success = file.renameTo(fileToMove); // rename이동에 성공하면 true, 실패하면 false를 반환합니다. 타켓파일이 없을 때 false입니다.
	        if (!success) { // 실패
	            System.out.println("Failed to rename to " + fileToMove);
	        }else { // 성공
	        	System.out.println("Succeed to rename to " + fileToMove);
	        }
	}
	
	public static void deleteOldProfileImageFile(String webPath, MultipartRequest mr) {
		// FrontController.doProcess에서 사용합니다.(meUpdateForm.jsp)
		System.out.println("profile : " + mr.getFilesystemName("profile"));
		System.out.println("deleteProfile : " + mr.getParameter("deleteProfile"));
		// 회원정보 수정시 과거에 업로드했던 이미지를 웹 서버에서 삭제합니다.
		if(mr.getFilesystemName("profile")!= null){ // 회원정보 수정에서 프로필사진을 선택한 경우(not null)에만 delete 실행
			String deleteImages = mr.getParameter("deleteProfile") ;
			if(deleteImages != null) {
				String deleteFile = webPath + "/" + deleteImages ;
				File target = new File(deleteFile) ;
				if(target.delete()) {
					System.out.println(deleteFile + " file delete success"); 
				}
			}
		}else { // meUpdateForm에서 profile을 선택한 값이 없는 경우(mr.getFilesystemName("profile") == null)
			System.out.println("meUpdate : 프로필이미지에 변동이 없으므로 파일을 그대로 유지합니다.");
		}
	}
	

	
	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String uploadPath) {
		// 이미지 업로드에 필요한 멀티 파트 객체를 생성하여 반환 합니다.
		MultipartRequest mr = null ;
		int maxPostSize = 10 * 1024 *1024 ;
		String ENCODING = "UTF-8" ;		
		try {
			mr = new MultipartRequest(
					request, 
					uploadPath, 
					maxPostSize,
					ENCODING,
					new DefaultFileRenamePolicy()) ;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return mr ;
	}	
	
	
	
	public static Map<String, SuperController> getTodolistMap(String filename) {
		Map<String, SuperController> map = new HashMap<String, SuperController>();
		
		Properties prop = getPropertiesData(filename);
		
		Enumeration<Object> keys =  prop.keys() ;
		
		while(keys.hasMoreElements()) {
			String command = keys.nextElement().toString() ;
			String className = prop.getProperty(command);
			System.out.println(command + "/" + className);
			
			try {
				Class<?> handleClass = Class.forName(className) ;
				SuperController instance = (SuperController)handleClass.newInstance() ;
				
				map.put(command, instance) ;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}	
	
	private static Properties getPropertiesData(String webFullPathName) {
		// 스트림을 이용하여 프로퍼티 목록을 반환해 줍니다.
		FileInputStream fis = null ;
		Properties prop = null ;
		
		try {
			fis = new FileInputStream(webFullPathName);
			
			prop = new Properties() ;
			prop.load(fis); 
			
		} catch (Exception e) { 
			e.printStackTrace();
			
		} finally {
			try {
				if(fis != null) {fis.close();}		
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		System.out.println("prop.toString()");
		System.out.println(prop.toString());
		
		return prop;
	}

	public static Map<String, String> getSettingMap(String webSettingName) {
		// webSettingName 파일을 이용하여 자바의 Map 형식으로 반환해 줍니다.
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = null ;
		prop = getPropertiesData(webSettingName) ;
		
		Enumeration<Object> keys = prop.keys() ;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement().toString() ;
			String value = prop.getProperty(key) ;
			
			//map.put(key, value) ;
			
			try { // 한글 깨짐 문제 해결
				map.put(key, new String(value.getBytes("ISO-8859-1"), "UTF-8")) ;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	//파일 삭제 메소드
	public static void deleteFile(String oldFile, String newFile, MultipartRequest mr, String uploadImage) {
		 if(newFile != null) { //새로운 파일이 있다면 해당 항목을 삭제
			System.out.println("실행1");
			MyUtility.deleteImageFile(oldFile, uploadImage, mr);
		}
	}

	private static void deleteImageFile(String oldFile, String uploadImage, MultipartRequest mr) {
		// 옛날 파일을 삭제하는 메소드
		if (oldFile != null) {
			String deleteFile = uploadImage + "/" + oldFile;
			File target = new File(deleteFile);
			if (target.delete()) {
				System.out.println(deleteFile + " file delete success");
			}
		}
		
	}

}
