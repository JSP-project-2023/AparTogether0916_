package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Vote;
import com.apartogether.utility.PagingVote;

public class VoteDao extends SuperDao{

	// 검색 조건에 따른 총 레코드 수를 반환하는 메서드
	public int GetTotalRecordCount(String mode, String keywordEndVote, String keyword) throws Exception{
		String sql = " select count(*) as cnt from vote ";
		
		// [ST] 검색옵션(mode)에 따른 sql문장 처리
		if( mode == null || mode.equals("all") ) {
			// 모든 투표 목록을 반환하므로 추가 조건 없음
		}else if(mode.equals("endvote")){ 
			// 마감여부로 검색
			if(keywordEndVote == null || keywordEndVote.equals("all") ) {
				 // 모든 마감 여부를 반환하므로 추가 조건 없음
			}else {
				sql += " where " + mode + " = '" + keywordEndVote + "'" ;
			}
		}else if(mode.equals("votetitle") || mode.equals("voteid")) { 
			 // 제목 또는 작성자로 검색
			if(keyword == null || keyword.equals("") ) {
				 // 검색어가 없으면 추가 조건 없음
			}else {
				sql += " where " + mode + " like '%" + keyword + "%' " ;
			}
		}
		// [ED] 검색옵션(mode)에 따른 sql문장 처리
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		int cnt = -1;
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return cnt;
	}
	
	 // 작성자 아이디 목록에 따른 총 레코드 수를 반환하는 메서드
	public int GetTotalRecordCountByIdList(String mode, String keywordEndVote, String keyword, List<String> lists_ID) throws Exception{
		// <투표리스트> 페이지에서 "작성자닉네임"으로 검색할 때 사용합니다.
		String sql = " select count(*) as cnt from vote ";
		
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 
		if(lists_ID.size() > 0) { 
			sql += " where voteid in ("; 
			for(int i=0; i < lists_ID.size(); i++) {
				String beanID = lists_ID.get(i);
				sql += " '" + beanID + "' " ;
				if (i < lists_ID.size() - 1) { sql += " , "; }
			}
			sql += " ) "; 
		}
		if(lists_ID.size() == 0) {sql += " WHERE voteid in ('" + "" + "') " ;} 
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		int cnt = -1;
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}
		return cnt ;
	}

	 // 투표 목록을 반환하는 메서드
	public List<Vote> selectAll(PagingVote pageInfo) throws Exception{
		// <투표리스트> 페이지에서 투표 목록을 가져오는데 사용합니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, voteid, endvote, votedate ";
		sql += " FROM " ;
		sql += " ( " ;
		sql += " SELECT voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, voteid, votedate, endvote, rank() over(order by voteno desc) as ranking  " ;
		sql += " FROM vote " ; ;
		
		String mode = pageInfo.getMode();
		String keywordEndVote = pageInfo.getKeywordEndVote();
		String keyword = pageInfo.getKeyword();
		
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 
		if( mode == null || mode.equals("all") ) {
			// 모든 투표 목록을 반환하므로 추가 조건 없음
		}else if(mode.equals("endvote")){ 
			// 마감여부로 검색
			if(keywordEndVote == null || keywordEndVote.equals("all") ) {
				// 모든 마감 여부를 반환하므로 추가 조건 없음
			}else {
				sql +=  " where " + mode + " = '" + keywordEndVote + "' " ;
			}
		}else if(mode.equals("votetitle") || mode.equals("voteid")) { 
			 // 제목 또는 작성자로 검색
			if(keyword == null || keyword.equals("") ) {
				// 검색어가 없으면 추가 조건 없음
			}else {
				sql +=  " where " + mode + " like '%" + keyword + "%' " ;
			}
		}
		sql += " )  where ranking between ? and ? " ;
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 
		
		conn = super.getConnection();

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<Vote> lists = new ArrayList<Vote>();
		while (rs.next()) {
			lists.add(getBeanData(rs));
		}
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return lists;
	}
	
	// 닉네임으로 검색한 경우 작성자 아이디 목록에 따른 투표 목록을 반환하는 메서드
	public List<Vote> selectAllByIdList(PagingVote pageInfo, List<String> lists_ID) throws Exception{
		// <투표리스트> 페이지에서 닉네임으로 검색하는 경우 사용합니다.
		List<Vote> lists = new ArrayList<Vote>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, voteid, endvote, votedate ";
		sql += " FROM ( SELECT voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, voteid, votedate, endvote, rank() over(order by voteno desc) as ranking  " ;
		sql += " FROM vote " ;
		
		// 작성자 아이디 목록에 따른 SQL 문장 처리
		if(lists_ID.size() > 0) { 
			sql += " where voteid in ("; 
			for(int i=0; i < lists_ID.size(); i++) {
				String beanID = lists_ID.get(i);
				sql += " '" + beanID + "' " ;
				if (i < lists_ID.size() - 1) { sql += " , "; }
			}
			sql += " ) "; 
		}
		if(lists_ID.size() == 0) {
			sql += " WHERE voteid in ('" + "" + "') " ;
		} 
		
		sql += " ) where ranking between ? and ? ";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			lists.add(getBeanData(rs));
		}
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}
		
		return lists;
	}
	
	// ResultSet에서 Vote 객체를 만들어 반환하는 메서드
	private Vote getBeanData(ResultSet rs) throws Exception{
		Vote bean = new Vote();
		
		bean.setVoteno(rs.getInt("voteno"));
		bean.setVotetitle(rs.getString("votetitle"));
		bean.setVotecol1(rs.getString("votecol1"));
		bean.setVotecol2(rs.getString("votecol2"));
		bean.setVotecol3(rs.getString("votecol3"));
		bean.setVotecol4(rs.getString("votecol4"));
		bean.setVotecol5(rs.getString("votecol5"));
		bean.setVotedate(String.valueOf(rs.getDate("votedate")));
		bean.setEndvote(rs.getInt("endvote"));
		bean.setVoteid(rs.getString("voteid"));
		return bean;
	}

	
}
