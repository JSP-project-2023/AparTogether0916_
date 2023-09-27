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
		super.youNeededLogin();
		super.gotoPage("vote/InsertVote.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		Vote vote = new Vote();
		
		vote.setVoteno(Integer.parseInt(request.getParameter("voteno")));
		vote.setVotetitle(request.getParameter("votetitle"));
		vote.setVotecol1(request.getParameter("votecol1"));
		vote.setVotecol2(request.getParameter("votecol2"));
		vote.setVotecol3(request.getParameter("votecol3"));
		vote.setVotecol4(request.getParameter("votecol4"));
		vote.setVotecol5(request.getParameter("votecol5"));
		vote.setVotedate(request.getParameter("votedate"));
		vote.setEndVote(Integer.parseInt(request.getParameter("endVote")));
		
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
