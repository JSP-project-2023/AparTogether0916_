package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.apartogether.model.bean.Vote;

public class VoteDao extends SuperDao {
	/* [getDataByPrimaryKey] id값으로만 member 조회 */
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
}
