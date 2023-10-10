package com.apartogether.controller.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Comment;
import com.apartogether.model.dao.CommentDao;

// 해당 게시물에 대한 댓글을 보여 주는 컨트롤러 입니다.
public class CommentListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		int roomno = Integer.parseInt(request.getParameter("roomno")) ;
		
		CommentDao dao = new CommentDao();
		List<Comment> comments = null ; 
		
		try {
			comments = dao.GetDataByPk(roomno) ;
			System.out.println(roomno + "번글에 대한 댓글 갯수 : " + comments.size());
			
			JSONArray jsArr = new JSONArray() ;
			
			for(Comment comm : comments) {
				JSONObject obj = new JSONObject() ;
				
				// no 컬럼은 사용하지 않을 예정
				obj.put("cnum", comm.getCnum());
				obj.put("id", comm.getId());
				obj.put("content", comm.getContent());
				
				jsArr.add(obj) ;
			}
			
			System.out.println("jsArr.toString() 결과 보기");
			System.out.println(jsArr.toString());
			
			request.setAttribute("jsArr", jsArr);
			
			super.gotoPage("comment/cmList.jsp");
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
