package com.apartogether.controller.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.dao.VoteDao;
import com.apartogether.utility.PagingVote;

public class VoteListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		//페이징 처리를 위한 파라미터
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keywordEndVote = request.getParameter("keywordEndVote") ;
		String keyword = request.getParameter("keyword") ;
		
		VoteDao dao = new VoteDao();
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keywordEndVote, keyword); //검색 시 페이지징에 사용
			String url = super.getUrlInfomation("voteList") ;
			boolean isGrid = false;
			PagingVote pageInfo = new PagingVote(pageNumber, pageSize, totalCount, url, mode, keywordEndVote, keyword, isGrid);
			
			List<Vote> lists = dao.selectAll(pageInfo);
			System.out.println("><><>>"+lists.size());
			
			request.setAttribute("datalist", lists); //VoteDao에서 가져온 투표lists를 바인딩
			
			//페이징관련 정보를 바인딩
			request.setAttribute("pageInfo", pageInfo);
			
			super.gotoPage("vote/voteList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
	}
}
