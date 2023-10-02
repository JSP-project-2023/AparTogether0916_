package com.apartogether.controller.vote;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.bean.VoteCount;
import com.apartogether.model.bean.Votelog;
import com.apartogether.model.dao.VoteDao;

public class VoteResultController extends SuperClass {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int voteno = Integer.parseInt(request.getParameter("voteno"));
		VoteDao dao = new VoteDao();
		Vote bean = null;
		Map<String, Integer> result = null;
		
		try {
			bean = dao.getDataByPrimaryKey(voteno);
			result = dao.voteCnt(voteno);
			
			if(bean == null || result == null) {
				super.setAlertMessage("잘못된 투표 게시판의 정보입니다.");
				super.gotoPage("common/home.jsp");
			}else {
				request.setAttribute("bean", bean);
				request.setAttribute("voteCount", result);
				super.gotoPage("vote/voteresultForm.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
