package com.apartogether.controller.vote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.dao.VoteDao;

//투표하기
public class VoteDoController extends SuperClass {
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		VoteDao dao = new VoteDao();
		
		//투표 마감 파라미터
		String voteEnd = request.getParameter("sendEndVote");
		// 투표 게시물 번호
		String voteno = request.getParameter("voteno");
		// 투표한 사람
		String id = request.getParameter("id");
		// 투표 내용
		String selectvotecol = request.getParameter("choice");
		// 투표 여부
		String voteVal = request.getParameter("voteVal");
		
		// 투표 마감 여부 업데이트
		if(voteEnd != null) {
			if (voteEnd.equals("endvote")) {
				dao.endVote(voteno);
			}
		}
		// 처음 투표라면 삽입
		if (voteVal.equals("0")) {
			try {
				dao.doVote(voteno, id, selectvotecol);
			} catch (Exception e) {
				super.setAlertMessage("잘못된 접근입니다.");
			}
		}
		else {// 처음이 아니라면 업데이트
			try {
				dao.reVote(voteno, id, selectvotecol);
			} catch (Exception e) {
				super.setAlertMessage("잘못된 접근입니다.");
			}
		}
		//투표 화면으로 이동.
//		new VoteViewController().doPost(request, response);
		new VoteListController().doGet(request, response);
	}
}
