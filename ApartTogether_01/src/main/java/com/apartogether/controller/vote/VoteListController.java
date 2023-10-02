package com.apartogether.controller.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.dao.MemberDao;
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
		MemberDao mdao = new MemberDao();
		
		try {
			// 첫번째 totalCount : 투표제목, 작성자 아이디, 마감여부 검색 시 사용 + 작성자닉네임 검색 중 keyword가 ""인 경우 사용합니다.
			int totalCount = dao.GetTotalRecordCount(mode, keywordEndVote, keyword); //
			if(keyword != null && keyword.equals("")) {
			}else {
				if(mode != null && mode.equals("nickname")) {
					// 두번째 totalCount : 작성자닉네임 검색 시 사용합니다.
					List<String> lists_ID = mdao.getIdListByNick(keyword);
					totalCount = dao.GetTotalRecordCountByIdList(mode, keywordEndVote, keyword, lists_ID); 
				}
			}
			
			String url = super.getUrlInfomation("voteList") ;
			boolean isGrid = false;
			PagingVote pageInfo = new PagingVote(pageNumber, pageSize, totalCount, url, mode, keywordEndVote, keyword, isGrid);
			
			List<Vote> lists = new ArrayList<Vote>();
			lists = dao.selectAll(pageInfo);
			if(keyword != null && keyword.equals("")) {
			}else {
				if(mode != null && mode.equals("nickname")) { //작성자 닉네임을 검색하는 경우
				List<String> lists_ID = mdao.getIdListByNick(keyword);
				lists = dao.selectAllByIdList(pageInfo, lists_ID);
				}
			}
			request.setAttribute("datalist", lists); //VoteDao에서 가져온 투표lists를 바인딩
			
			// Map "idnickmap"은 key=ID, Value=Nickname 형태로, id를 사용해 nickname을 가져옵니다.
			Map<String, String> map = new HashMap<String, String>();
			map = mdao.getIdNickMap();
			request.setAttribute("idnickmap", map); 
			request.setAttribute("pageInfo", pageInfo); //페이징관련 정보를 바인딩
			
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
