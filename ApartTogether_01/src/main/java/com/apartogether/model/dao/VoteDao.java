package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.apartogether.model.bean.Vote;
import com.apartogether.utility.MyUtility;
import com.apartogether.utility.Paging;

import java.sql.SQLException;
import java.util.*;

import com.apartogether.model.bean.Vote;

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
			bean.setVoteid(rs.getString("voteid"));
			bean.setEndVote(Integer.parseInt( rs.getString("endvote")));
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
		
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
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
	
	//투표 마감 여부 업데이트
	public void endVote(String voteno) throws Exception {
		String sql ="update vote set endvote=1 where voteno=? ";
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, voteno);	
		
		//문제의 코드
		pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}