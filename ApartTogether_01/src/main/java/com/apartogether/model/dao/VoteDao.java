package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.apartogether.model.bean.Vote;
import com.apartogether.model.bean.VoteCount;
import com.apartogether.model.bean.Votelog;
import com.apartogether.utility.MyUtility;
import com.apartogether.utility.Paging;
import java.sql.SQLException;


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
			bean.setEndvote(Integer.parseInt( rs.getString("endvote")));
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
	}
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
		bean.setEndvote(Integer.parseInt(rs.getString("endvote"))  ); /* 투표 종료 시각 */
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
