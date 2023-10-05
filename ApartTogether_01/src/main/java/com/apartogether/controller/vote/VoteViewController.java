package com.apartogether.controller.vote;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.dao.VoteDao;

public class VoteViewController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		try {
			// 투표 게시물 가져오기
			int voteno = Integer.parseInt(request.getParameter("voteno"));

			// 회원 아이디 가져오기
			String id = request.getParameter("id");
			VoteDao dao = new VoteDao();

			// 투표 정보 가져오기
			Vote voteinfo = dao.getVoteTitle(voteno);
			request.setAttribute("voteinfo", voteinfo);
			int resultVote = voteinfo.getEndvote();
			
			//투표 결과로 이동
			if(resultVote == 1) {
				//결과 이동 컨트롤러 추가
				new VoteResultController().doGet(request, response);
			}
			else {
				// 투표 항목 가져오기
				List<String> votelist = null;
				votelist = dao.getvotelist(voteno);
				request.setAttribute("votelist", votelist);

				// 투표 여부 확인
				int voteVal = dao.voteVal(voteno, id);
				request.setAttribute("voteVal", voteVal);
			
				//투표 했던 항목 가져오기
				if(voteVal == 1) {
					String selectVote = dao.selectVote(voteno, id);
					request.setAttribute("selectVote", selectVote);
				}
				super.gotoPage("vote/voteView.jsp");
			}
			
		} catch (Exception e) {
			super.setAlertMessage("올바르지 않은 접근입니다.");
			super.gotoPage("common/home.jsp");
		}
	}
}
