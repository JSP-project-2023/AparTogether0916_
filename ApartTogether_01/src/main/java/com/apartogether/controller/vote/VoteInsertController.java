package com.apartogether.controller.vote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.dao.VoteDao;

public class VoteInsertController extends SuperClass {
	private int cnt = -1;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		super.gotoPage("vote/InsertVote.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		Vote vote = new Vote();
		
//		request로 받아온 정보 담을 공간
		String[] votecol = new String[5];
		String[] requstVotecol = request.getParameterValues("votecol");
		
//		덮어쓰기
		for (int i = 0; i < requstVotecol.length; i++) {
			votecol[i] = requstVotecol[i];
		}
		
		vote.setVotetitle(request.getParameter("votetitle"));
		vote.setVoteid(id);
		vote.setVotecol1(votecol[0]);
		vote.setVotecol2(votecol[1]);
		vote.setVotecol3(votecol[2]);
		vote.setVotecol4(votecol[3]);
		vote.setVotecol5(votecol[4]);
		
		VoteDao dao = new VoteDao();
		try {
			cnt = dao.InsertVote(vote);
			
			if (cnt == -1) {
//				실패
				super.setAlertMessage("투표 등록에 실패하였습니다.");
//				new VoteListController 이동
				
			} else { // 성공
				super.setSuccessAlertMessage("투표 등록이 완료되었습니다.");
//				new VoteListController 이동
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}