package com.apartogether.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperController;
import com.apartogether.utility.MyUtility;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet(
		urlPatterns = { "/Apartogether" },
		initParams = {  
				@WebInitParam(name = "todolist", value = "/WEB-INF/todolist.txt")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 초기화 파라미터 관련 변수
	private String todolist = null ;

	// map for todolist.txt file
	private Map<String, SuperController> todolistMap = null ;
	
	// imageUploadWebPath 변수 : 실제 이미지가 업로드 되는 경로
	//private String imageUploadWebPath ; 
	// 가게 이미지 업로드 경로변수 
	private String uploadImage;
	//이미지 경로 변수
	ServletContext application = null;
	// 프로필 이미지 업로드폴더 경로변수 
	private String uploadImageProfile;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		
		// command Parameter : 컨트롤러 분기를 위한 핵심 파라미터 
		String command = request.getParameter("command") ; // input command에 적혀진 value값 불러오기
		
		if(command == null) {
			
			System.out.println("file upload event invoked");
			
			MultipartRequest mr = MyUtility.getMultipartRequest(request, uploadImage);
			
			if(mr!=null) {
				System.out.println("mr확인합니다");
				command = mr.getParameter("command") ;
				
				if(command.equals("stUpdate")) {//가게 수정시 변경.
					// 옛날 파일 있으면 삭제X, 파일을 교체했다면 삭제하고 업로드
					//사업자 등록증
					String oldFile = mr.getParameter("ceofileUpdate");
					String newFile = mr.getFilesystemName("ceofile");
					//파일삭제 유효성 검사
					MyUtility.deleteFile(oldFile, newFile, mr, uploadImage);
					
					//가게 로고
					oldFile = mr.getParameter("stlogoUpdate");
					newFile = mr.getFilesystemName("stlogo");
					//파일삭제 유효성 검사
					MyUtility.deleteFile(oldFile, newFile, mr, uploadImage);	
					
//				메뉴 수정
				} else if (command.equals("menuUpdate")) {
					String oldFile = mr.getParameter("menuimageUp");
					String newFile = mr.getFilesystemName("menuimage");
					
					MyUtility.deleteFile(oldFile, newFile, mr, uploadImage);
				}
				// MultipartRequest를 또하나 만드는 방법 대신
				// uploadImage경로(/uploadStoreImage)로 일단 업로드 한 후 
				// uploadImageProfile경로(/uploadProfileImage)로 파일이동 시키는 방법을 사용합니다.
				if(command.equals("meUpdate")) { // 회원정보 수정
					MyUtility.deleteOldProfileImageFile(uploadImageProfile, mr);
					MyUtility.moveFolderProfileImage(uploadImage, uploadImageProfile, mr);
				}
				
				if(command.equals("meInsert")) { // 회원가입
					MyUtility.moveFolderProfileImage(uploadImage, uploadImageProfile, mr);
				}
				
				// file upload object binding in request scope.
				request.setAttribute("mr", mr); // 승급
			}else{
				System.out.println("MultipartRequest object is null");
			}
		}
		
		System.out.println("command is [" + command + "]");
		System.out.println("command controller is : " + this.todolistMap.get(command));
		
		SuperController controller = this.todolistMap.get(command) ;
		
		if (controller != null) {
			String method = request.getMethod() ;
		
			try {
				if(method.equalsIgnoreCase("get")) {
					System.out.println(this.getClass() + " get method called");
					controller.doGet(request, response);
					
				}else {
					System.out.println(this.getClass() + " post method called");
					controller.doPost(request, response);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}			

		}else {
			System.out.println("request command is not found");
		}
	
	}	
	
	public void init(ServletConfig config) throws ServletException {
		// 프로그램에서 서블렛 호출 시 최초에 호출되는 메서드입니다.
		this.todolist = config.getInitParameter("todolist"); // 프로젝트 내 todolist 위치한 경로 (WEB-INF)
		System.out.println("todolist is [" + this.todolist + "]"); 	
		
		ServletContext application = config.getServletContext() ; // application Scope 사용위해 선언
		
		String todolistFile = application.getRealPath(todolist); // 실제 위치한 전체 경로 (C드라이브 부터)
		System.out.println("todolistFile is [" + todolistFile + "]");
				
		this.todolistMap = MyUtility.getTodolistMap(todolistFile); // todolist 읽어서 Map에 저장 (String, Controller) 
		System.out.println("todolist file element size = [" + todolistMap.size() + "]");
		
		//이미지 파일 업로드 경로
		uploadImage = application.getRealPath("uploadStoreImage");
		File file = new File(uploadImage);
		
		//파일 유효성 검사 후, 존재하지 않으면 디렉터리 생성
		if(!file.exists()) {
			if(!file.isDirectory()) {
				System.out.println("디렉토리가 존재하지 않아 생성합니다.");
				file.mkdir();
			}
		}
		System.out.println("imageUploadWebPath is [" + uploadImage + "]");
	
		// 이미지 파일 업로드 경로 :: 프로필이미지 저장용 경로입니다. 폴더이름은 /uploadProfileImage 입니다.
		uploadImageProfile = application.getRealPath("uploadProfileImage");
		File fileProfile = new File(uploadImageProfile);
		
		//파일 유효성 검사 후, 존재하지 않으면 디렉터리 생성
		if(!fileProfile.exists()) {
			if(!fileProfile.isDirectory()) {
				System.out.println("디렉토리가 존재하지 않아 생성합니다.");
				fileProfile.mkdir();
			}
		}
		System.out.println("프로필ImageUploadWebPath is [" + uploadImageProfile + "]");
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	public FrontController() {}
}




