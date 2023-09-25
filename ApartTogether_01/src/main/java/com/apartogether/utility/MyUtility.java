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
	
	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String uploadPath) {
		System.out.println("업로드 경로 : "+ uploadPath);
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
				Class<?> handleClass = Class.forName(className);
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
		System.out.println("prop.toString() : " + prop.toString());
		
		return prop;
	}

<<<<<<< HEAD
	//파일 삭제 메소드
	public static void deleteFile(String oldFile, String newFile, MultipartRequest mr, String uploadImage) {
		 if(newFile != null) { //새로운 파일이 있다면 해당 항목을 삭제
			System.out.println("newFileeeee : " + newFile);
			System.out.println("oldFileeeee : " + oldFile);
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
