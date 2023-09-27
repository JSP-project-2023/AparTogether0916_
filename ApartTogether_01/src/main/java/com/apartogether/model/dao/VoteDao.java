package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Vote;
import com.apartogether.utility.PagingVote;

public class VoteDao extends SuperDao{

	public int GetTotalRecordCount(String mode, String keywordEndVote, String keyword) throws Exception{
		String sql = " select count(*) as cnt from vote ";
		
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		if( mode == null || mode.equals("all") ) {
		}else if(mode.equals("endvote")){ // 마감여부으로 검색
			if(keywordEndVote == null || keywordEndVote.equals("all") ) {
			}else {
				sql += " where " + mode + " = '" + keywordEndVote + "'" ;
			}
		}else if(mode.equals("votetitle") || mode.equals("voteid")) { // 제목, 작성자로 검색
			if(keyword == null || keyword.equals("") ) {
			}else {
				sql += " where " + mode + " like '%" + keyword + "%' " ;
			}
		}
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		
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

	public List<Vote> selectAll(PagingVote pageInfo) throws Exception{
		// <투표리스트> 페이지에서 사용합니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, voteid, nickname, endvote, votedate ";
		sql += " FROM  " ;
		sql += " ( " ;
		sql += " SELECT v.voteno as voteno, v.votetitle as votetitle, v.votecol1 as votecol1, v.votecol2 as votecol2, v.votecol3 as votecol3, v.votecol4 as votecol4, v.votecol5 as votecol5, v.voteid as voteid , m.nickname as nickname, v.votedate as votedate, v.endvote as endvote, rank() over(order by m.id asc) as ranking  " ;
		sql += " FROM vote v " ;
		sql += " LEFT JOIN members m ON v.voteid = m.id  " ;
		sql += " ) " ;
		sql += " where " ;
		
		String mode = pageInfo.getMode();
		String keywordEndVote = pageInfo.getKeywordEndVote();
		String keyword = pageInfo.getKeyword();
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		if( mode == null || mode.equals("all") ) {
		}else if(mode.equals("endvote")){ // 마감여부으로 검색
			if(keywordEndVote == null || keywordEndVote.equals("all") ) {
			}else {
				sql += " where " + mode + " = '" + keywordEndVote + "' and " ;
			}
		}else if(mode.equals("votetitle") || mode.equals("voteid")) { // 제목, 작성자로 검색
			if(keyword == null || keyword.equals("") ) {
			}else {
				sql += " where " + mode + " like '%" + keyword + "%'  and " ;
			}
		}
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		sql += "  ranking between ? and ? ";
		
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
		bean.setNickname(rs.getString("nickname"));
		System.out.println(">>>" + rs.getString("nickname"));
		return bean;
	}

	
}
