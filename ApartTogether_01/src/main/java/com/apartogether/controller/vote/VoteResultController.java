package com.apartogether.controller.vote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.dao.VoteDao;

public class VoteResultController extends SuperClass {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int voteno = Integer.parseInt(request.getParameter("voteno"));
		VoteDao dao = new VoteDao();
		Vote bean = null;
		
		try {
			bean = dao.getDataByPrimaryKey(voteno);
			
			if(bean == null) {
				super.setAlertMessage("잘못된 투표 게시판의 정보입니다.");
				super.gotoPage("common/home.jsp");
			}else {
				request.setAttribute("bean", bean);
				super.gotoPage("vote/voteresult.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
