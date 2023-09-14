package com.apartogether.servlet;

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


@WebServlet(
		urlPatterns = { "/Apartogether" }, //컨트롤러 경로 수정 바람.
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
	private String imageUploadWebPath ; 

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		
		// command Parameter : 컨트롤러 분기를 위한 핵심 파라미터 
		String command = request.getParameter("command") ;
		
		if(command == null) {
			System.out.println("file upload event invoked");
			
			MultipartRequest mr = MyUtility.getMultipartRequest(request, imageUploadWebPath);
			
			if(mr!=null) {
				command = mr.getParameter("command") ;
				
				if(command.equals("prUpdate")) {
					MyUtility.deleteOldImageFile(imageUploadWebPath, mr);	
				}
			
				// file upload object binding in request scope.
				request.setAttribute("mr", mr); // 승급
			}else{
				System.out.println("MultipartRequest object is null");
			}
		}
		
		System.out.println("command is [" + command + "]");
		
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
		
		this.todolist = config.getInitParameter("todolist");
		System.out.println("todolist is [" + this.todolist + "]"); 	
		
		ServletContext application = config.getServletContext() ;
		
		
		String todolistFile = application.getRealPath(todolist);
		System.out.println("todolistFile is [" + todolistFile + "]");
		
		System.out.println("imageUploadWebPath is [" + imageUploadWebPath + "]");
				
		this.todolistMap = MyUtility.getTodolistMap(todolistFile);
		System.out.println("todolist file element size = [" + todolistMap.size() + "]");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	public FrontController() {}
}




