package com.apartogether.controller.vote;

import java.util.HashMap;
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
		
		/* HashMap<String, Integer> voteResult = new */
		try {
			
			
			VoteCount votetotal = dao.getTotalvote(voteno);
			request.setAttribute("voteTotal", votetotal);
			
			
			List<VoteCount> voteResult = dao.voteCnt2(voteno);
			bean = dao.getDataByPrimaryKey(voteno);
			voteResult = dao.voteCnt2(voteno);
			
			/* voteResult = dao.voteCnt(voteno); */
			request.setAttribute("bean", bean);
			/* request.setAttribute("voteCount", result); */
			request.setAttribute("voteResult", voteResult);
			
			
			
			super.gotoPage("vote/voteresultForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
