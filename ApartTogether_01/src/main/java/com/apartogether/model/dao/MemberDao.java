package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Member;
import com.apartogether.utility.MyUtility;
import com.apartogether.utility.Paging;

public class MemberDao extends SuperDao {
	
	public int UpdateData(Member bean) throws Exception{
		System.out.println("상품 수정 빈 :\n" + bean);
		PreparedStatement pstmt = null;
		String sql = " update members set mtype = ? , name = ? ,  phone = ? , birth = ? , gender = ? , nickname = ? , address = ? , profile = ? ";
		sql += " where id = ? " ;
		int cnt = -1 ;
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getMtype());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getPhone());
		pstmt.setString(4, bean.getBirth());
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getNickname());
		pstmt.setString(7, bean.getAddress());
		pstmt.setString(8, bean.getProfile());

		pstmt.setString(9, bean.getId());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}
	
	public int deleteData(String id)  throws Exception{ 
		// MemberDeleteController.doGet에서 사용합니다.
		// id 회원이 탈퇴합니다.
		int cnt = -1 ;
		String sql = "" ;
		Member bean = this.getDataByPrimaryKey(id);
		PreparedStatement pstmt = null;
 		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		// 회원 테이블의 id 행을 삭제
		sql = " delete from members where id = ? " ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) {pstmt.close();}
		
		conn.commit();
		if(conn!=null) {conn.close();}
		return cnt ;
	}
	
	/* [selectAll(pageinfo를 위함)] TopN 구문을 사용하여 페이징 처리된 게시물 목록을 반환합니다. */
	public List<Member> selectAll(Paging pageInfo) throws Exception{
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select id, mtype, name, password, phone, birth, gender, nickname, address, profile ";
		sql += " from ";
		sql += " (select id, mtype, name, password, phone, birth, gender, nickname, address, profile, rank() over(order by name asc) as ranking ";
		sql += " from members) ";
		sql += " where ranking between ? and ? ";
		
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery() ;
		
		List<Member> lists = new ArrayList<Member>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}	
	
	
	/* [GetTotalRecordCount] 테이블의 총 행개수를 구합니다. */
	public int GetTotalRecordCount() throws Exception {
		
		String sql = " select count(*) as cnt from members " ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection() ;
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ; 
		
		int cnt = -1 ;
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}	
	
	/* [getDataByPrimaryKey] id값으로만 member 조회 */
	public Member getDataByPrimaryKey(String id) throws Exception{
		// 기본 키 정보를 이용하여 Bean 객체를 구합니다.
		String sql = " select * from members " ;
		sql += " where id = ?" ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		conn = super.getConnection() ;
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setString(1, id);
		
		rs = pstmt.executeQuery() ; 
		
		Member bean = null ;
		
		if(rs.next()) {
			bean = this.getBeanData(rs) ;
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return bean;
	}
	
	
	
	/* [getDataByPk] id,pw값으로 member 조회 */
	public Member getDataByPk(String id, String password) throws Exception {		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from members " ;
		sql += " where id = ? and password = ? " ;
		
		conn = super.getConnection() ; // 단계 02		
		pstmt = conn.prepareStatement(sql) ; // 단계 03
		
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery() ; // 단계 04-01
		
		Member bean = null ;
		if(rs.next()) { // 1건이 조회됨
			bean = getBeanData(rs); 
		}
		
		// 단계 05
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean ;
	}
	
	
	
	/* [getBeanData] ResultSet의 데이터를 읽어서 Bean에 기록한 다음, 반환해 줍니다. */
	private Member getBeanData(ResultSet rs) throws Exception{
		Member bean = new Member()  ;				/* DB순으로 생성 */
													
		bean.setId(rs.getString("id"));				/* 멤버 아이디 */
		bean.setMtype(rs.getString("mtype")); 		/* admin / user / biz | 3종류 */
		bean.setName(rs.getString("name"));			/* 이름 */
		bean.setPassword(rs.getString("password"));	/* 패스워드 */
		bean.setPhone(rs.getString("phone"));		/* 휴대폰번호(String형식) */
		bean.setBirth(String.valueOf(rs.getDate("birth")));
		bean.setGender(rs.getString("gender"));		/* male / female | 성별 */
		bean.setNickname(rs.getString("nickname"));	/* 닉네임 */		
		bean.setAddress(rs.getString("address"));	/* 주소 */
		bean.setProfile(rs.getString("profile"));	/* 프로필 이미지 */
		
		return bean;
	}
	
	/* [InsertData] Member bean에 기록한 다음, 반환해 줍니다. */
	public int InsertData(Member bean) throws Exception {
		System.out.println(bean); 
		
		// Bean 객체 정보를 이용하여 데이터 베이스에 추가합니다.
		int cnt = -1 ;

		String sql = " insert into members(id, mtype, name, password, phone, birth, gender, nickname, address, profile) " ;
		sql += " values(					?,	   ?,	 ?,	       ?,	  ?,     ?,	     ?, 	   ?,	    ?,	     ?) " ; 
		
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getMtype());
		pstmt.setString(3, bean.getName());
		pstmt.setString(4, bean.getPassword());
		pstmt.setString(5, bean.getPhone());
		pstmt.setString(6, bean.getBirth());
		pstmt.setString(7, bean.getGender());
		pstmt.setString(8, bean.getNickname());
		pstmt.setString(9, bean.getAddress());
		pstmt.setString(10, bean.getProfile());
		
		cnt = pstmt.executeUpdate() ; 
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}
	
	
	/* [selectAll] member List객체에 담아서 '모든 정보' 조회 */
	public List<Member> selectAll() throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from members order by name asc";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ;
		
		List<Member> lists = new ArrayList<Member>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}





	
}
