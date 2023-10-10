package com.apartogether.controller.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.controller.room.RoomDetailController;
import com.apartogether.model.bean.Comment;
import com.apartogether.model.dao.CommentDao;

public class CommentInsertController extends SuperClass {
	@Override // 부모 글번호에 대하여 로그인 한 사람이 댓글을 작성하는 로직입니다.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		super.doPost(request, response);
		
		String nickname = null;
		CommentDao dao = new CommentDao() ;
		
		nickname = dao.getNickname(super.loginfo.getId());
		request.setAttribute("nickname", nickname);
		
		
		Comment bean = new Comment() ;
		bean.setRoomno(Integer.parseInt(request.getParameter("roomno"))); // 부모(게시물) 글번호
		bean.setContent(request.getParameter("content")); // 내가 작성한 댓글 내용
		bean.setId(nickname); // 닉네임
	
		int cnt = -1 ; 
		
		try {
			cnt = dao.InsertData(bean) ;
			
			new RoomDetailController().doGet(request, response); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
