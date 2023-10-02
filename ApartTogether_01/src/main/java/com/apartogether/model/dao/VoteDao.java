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

public class VoteDao extends SuperDao {
	private int cnt = -1;
	
	public int InsertVote(Vote vote) throws Exception{
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
}