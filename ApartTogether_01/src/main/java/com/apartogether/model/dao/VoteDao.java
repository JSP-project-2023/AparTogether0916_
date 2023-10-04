package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apartogether.model.bean.Store;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.bean.VoteCount;
import com.apartogether.model.bean.Votelog;

public class VoteDao extends SuperDao {
	/* voteno 값으로만 vote 테이블 조회 */
	public Vote getDataByPrimaryKey(int voteno) throws Exception {
		// 기본 키 정보를 이용하여 Bean 객체를 구합니다.
		String sql = " select * from vote ";
		sql += " where voteno = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);

		rs = pstmt.executeQuery();

		Vote bean = null;

		if (rs.next()) {
			bean = this.getBeanData(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean;
	}
	
	/* voteno 값만으로 votelog 조회 */
	public Votelog getVotelogBypk(int voteno) throws Exception {
		// voteno 값만으로 votelog를 조회
		String sql = " select * from votelog ";
		sql += " where voteno = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);

		rs = pstmt.executeQuery();

		Votelog bean = null;

		if (rs.next()) {
			bean = this.getvotelogData(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean;
	}
	
	/* 투표자 퍼센트를 구하기 위한 총 참여자 확인 */
	public VoteCount getTotalvote(int voteno) throws Exception {
		VoteCount bean = new VoteCount();
		
		String sql = " SELECT COUNT(*) as total FROM votelog WHERE voteno=? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, voteno);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			bean.setTotal(rs.getInt("total"));
		}
		
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		System.out.println(bean);
		return bean;
	}
	
	
	public HashMap<String, Integer> voteCnt(int voteno) throws Exception {
		// 기본 키 정보를 이용하여 Bean 객체를 구합니다.
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String sql = " SELECT COUNT(*) as cnt, selectvotecol FROM votelog WHERE voteno=? " ;
		sql += "group by selectvotecol; ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);

		rs = pstmt.executeQuery();

		/* VoteCount bean = null; */

		while(rs.next()) {
			int cnt = rs.getInt(1);
			String selectvotecol = rs.getString(2);
			
			result.put(selectvotecol, cnt);
		}
		
		/*
		 * if (rs.next()) { bean = this.getvoteCountData(rs); }
		 */
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return result;
		/* return bean; */
	}
	
	public List<VoteCount> voteCnt2(int voteno) throws Exception {
		// 기본 키 정보를 이용하여 Bean 객체를 구합니다.
		String sql = " SELECT COUNT(*) as cnt, selectvotecol FROM votelog WHERE voteno=? group by selectvotecol ";
		
		List<VoteCount> voteResult = new ArrayList<VoteCount>();
		VoteCount bean = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, voteno);

		rs = pstmt.executeQuery();

		/* VoteCount bean = null; */
		
		
		while(rs.next()) {
			bean = getvoteCountData(rs);
			voteResult.add(getvoteCountData(rs));
		}
		
		/*
		 * if (rs.next()) { bean = this.getvoteCountData(rs); }
		 */
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return voteResult;
		/* return bean; */
	}
	
	
	
	/* [getBeanData] ResultSet의 데이터를 읽어서 Bean에 기록한 다음, 반환해 줍니다. */
	private Vote getBeanData(ResultSet rs) throws Exception {
		Vote bean = new Vote(); /* DB순으로 생성 */

		bean.setVoteno(rs.getInt("voteno")); /* pk값 vote seq넘버 */
		bean.setVotetitle(rs.getString("votetitle")); /* 투표제목 */
		bean.setVotecol1(rs.getString("votecol1")); /* 투표컬럼1 */
		bean.setVotecol2(rs.getString("votecol2")); /* 투표컬럼2  */
		bean.setVotecol3(rs.getString("votecol3")); /* 투표컬럼3  */
		bean.setVotecol4(rs.getString("votecol4")); /* 투표컬럼4  */
		bean.setVotecol5(rs.getString("votecol5")); /* 투표컬럼5  */
		bean.setVotedate(rs.getString("votedate")); /* 투표 시작 시각 */
		bean.setEndvote(rs.getBoolean("endvote")); /* 투표 종료 시각 */
		bean.setVoteid(rs.getString("voteid")); /* 투표 계시한 사람 */

		return bean;
	}
	
	private Votelog getvotelogData(ResultSet rs) throws Exception {
		Votelog bean = new Votelog(); /* DB순으로 생성 */

		bean.setVoteno(rs.getInt("voteno")); /* vote seq넘버 */
		bean.setVoteid(rs.getString("voteid")); /* 투표한 사람의 id에 대한 fk */
		bean.setSelectvotecol(rs.getString("selectvotecol")); /* 투표컬럼 몇번 */

		return bean;
	}
	
	private VoteCount getvoteCountData(ResultSet rs) throws Exception {
		VoteCount bean = new VoteCount(); /* DB순으로 생성 */

		bean.setCnt(rs.getInt("cnt")); /* vote seq넘버 */
		bean.setSelectvotecol(rs.getString("selectvotecol")); /* 투표컬럼 몇번 */

		return bean;
	}
	
	
}
