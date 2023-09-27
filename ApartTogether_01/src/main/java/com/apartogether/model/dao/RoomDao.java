package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Room;
import com.apartogether.utility.Paging;

public class RoomDao extends SuperDao{

	

	public List<Room> selectInfo(Paging pageInfo) throws Exception{ //방정보를 보여줌(방번호 , 주문시간, 방제목, 위치)
		// 전체 방 목록 불러오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT roomno, ordertime, roomname, orderplace, stname, category, row_count ";
		sql += " FROM ( ";
		sql += "  SELECT ro.roomno, ro.ordertime, ro.roomname, ro.orderplace, st.stname, st.category, ";
		sql += "  ROW_NUMBER() OVER (ORDER BY ro.roomno DESC) AS ranking ,";
		sql += " COUNT(distinct rs.id) AS row_count ";
		sql += "  FROM room ro ";
		sql += "  INNER JOIN store st ON ro.stno = st.stno ";
		sql += " INNER JOIN room_status rs ON ro.roomno = rs.roomno ";
		
		sql += " INNER JOIN personal pe ON ro.roomno = pe.roomno ";
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		
		sql += " WHERE pe.confirm != 'success'";
		
		
		// 모드 및 키워드 이용 검색 하기
		if (mode != null && !mode.equals("all")) { // 괄호 열기
		    if (mode.equals("category")) {
		        sql += " and st." + mode + " LIKE '%" + keyword + "%' ";
		    }  else if (mode.equals("stname")) {
		        sql += " and st." + mode + " LIKE '%" + keyword + "%' ";
		    } else if (mode.equals("orderplace")) {
		        sql += " and ro." + mode + " LIKE '%" + keyword + "%' ";
		    }
		} 
			
		sql += "GROUP BY ro.roomno, ro.ordertime, ro.roomname, ro.orderplace, st.stname, st.category";
		sql += ") ";
		sql += "WHERE ranking BETWEEN ? AND ? ";
		sql += "ORDER BY roomno DESC ";

		
		conn = super.getConnection();
		pstmt=conn.prepareStatement(sql);

		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs= pstmt.executeQuery();
		
		List<Room> lists = new ArrayList<Room>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
	
		return lists;
	}

	private Room getBeanData(ResultSet rs) throws Exception{
		// 방목록 로딩할 떄 사용
		Room bean = new Room();
		// 데이터 불러오기
		bean.setCategory(rs.getString("category"));
		bean.setOrdertime(rs.getString("ordertime"));
		bean.setPlace(rs.getString("orderplace"));
		bean.setStname(rs.getString("stname"));
		bean.setRoomname(rs.getString("roomname"));
		bean.setRoomno(rs.getInt("roomno"));
		bean.setRow_count(rs.getInt("row_count"));
		
		return bean;
	}
	

	public int GetTotalRecordCount() throws Exception {
		// 테이블의 총 행개수를 구합니다.
		String sql = " select count(*) as cnt from room ro" ;
		sql += " inner join personal pe on ro.roomno = pe.roomno";
		sql += " where pe.confirm != 'success'";
		
		
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
	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		// 키워드 페이징 처리하기
		System.out.print("검색할 필드명 : " + mode);
		System.out.println(", 검색할 키워드 : " + keyword);
		
		// 테이블의 총 행개수를 구합니다.
		String sql = " select count(*) as cnt from room ro" ;
		sql += " inner join store st ON ro.stno = st.stno";
		sql += " inner join personal pe on ro.roomno = pe.roomno";
		sql += " where pe.confirm != 'success'";

		
		if (mode != null && !mode.equals("all")) { // 괄호 열기
		    if (mode.equals("category")) {
		        sql += " and st." + mode + " LIKE '%" + keyword + "%' ";
		    }  else if (mode.equals("stname")) {
		        sql += " and st." + mode + " LIKE '%" + keyword + "%' ";
		    } else if (mode.equals("orderplace")) {
		        sql += " and ro." + mode + " LIKE '%" + keyword + "%' ";
		    }
		} 
		
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
	
	
	
	
}
