package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.apartogether.model.bean.Vote;
import java.sql.SQLException;
import com.apartogether.utility.PagingVote;

public class VoteDao extends SuperDao{
	private int cnt = -1;
	
	//기능 추가?
	public Vote getVoteTitle(int voteno) throws SQLException {
		String sql ="select * from vote where voteno=?";
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		Vote bean = new Vote();
		if(rs.next()) {
			bean.setVoteno(rs.getInt("voteno"));
			bean.setVotetitle(rs.getString("votetitle"));

			//bean.setVotedate((rs.getString(voteno));
		}

		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		return bean;
	}
	
	//투표 항목 가져오기
	public List<String> getvotelist(int voteno) throws SQLException {
		
		List<String> votelist = new ArrayList<String>();
		
		String sql ="select * from vote where voteno=?";
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			votelist.add(rs.getString("votecol1"));
			votelist.add(rs.getString("votecol2"));
			votelist.add(rs.getString("votecol3"));
			votelist.add(rs.getString("votecol4"));
			votelist.add(rs.getString("votecol5"));
		}
		
		votelist = checkNull(votelist);
		
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		return votelist;
	}
	//투표 항목 null 체크
	private List<String> checkNull(List<String> votelist) {
		votelist.removeIf(vote -> vote == null);
	    return votelist;
	}
	
	//투표 여부 확인
	public int voteVal(int voteno, String id) throws SQLException {
		int val = 0;
		
		System.out.println(voteno + "// "+  id);
		String sql = "select count(*) as val from votelog where voteid=? and voteno=? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, voteno);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			val = rs.getInt("val");
		}
		
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return val;
	}

	public void doVote(String voteno, String id, String selectvotecol) throws SQLException {
		String sql = "insert into votelog values (?, ?, ?)";

		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, voteno);
		pstmt.setString(2, id);
		pstmt.setString(3, selectvotecol);
		
		pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

	public void reVote(String voteno, String id, String selectvotecol) throws SQLException {
		String sql = "update votelog set selectvotecol=? where voteno=? and voteid=?";

		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, selectvotecol);
		pstmt.setString(2, voteno);
		pstmt.setString(3, id);
		
		pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

	//전에 투표한 항목 가져오기
	public String selectVote(int voteno, String id) throws SQLException {
		String sql = "select selectvotecol from votelog where voteno=? and voteid=? ";
		String selectVote = "";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, voteno);
		pstmt.setString(2, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			selectVote = rs.getString("selectvotecol");
		}
		return selectVote;
	}
	
//	voteInsert (투표 게시글 등록)
	public int InsertVote(Vote vote) throws Exception {
		String sql = "insert into vote(voteno, votetitle, votecol1, votecol2, votecol3, votecol4, votecol5, votedate, endvote, voteid)";
		sql += " values(seqvote.nextval, ?, ?, ?, ?, ?, ?, sysdate, default, ?)";
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vote.getVotetitle());
		pstmt.setString(2, vote.getVotecol1());
		pstmt.setString(3, vote.getVotecol2());
		pstmt.setString(4, vote.getVotecol3());
		pstmt.setString(5, vote.getVotecol4());
		pstmt.setString(6, vote.getVotecol5());
		pstmt.setString(7, vote.getVoteid());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt!=null) {pstmt.close();}
		if (conn!=null) {conn.close();}
		
		return cnt;
	}
	
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