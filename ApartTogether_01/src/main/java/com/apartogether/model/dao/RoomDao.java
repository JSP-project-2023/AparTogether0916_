package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Room;
import com.apartogether.utility.Paging;

public class RoomDao extends SuperDao{

	public List<Room> selectInfo(Paging pageInfo) throws Exception{ //방정보를 보여줌(방번호 , 주문시간, 방제목, 위치)
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT roomno, ordertime, roomname, place, stname, category ";
		sql += "FROM ( ";
		sql += "  SELECT ro.roomno, ro.ordertime, ro.roomname, pl.place, st.stname, st.category, ";
		sql += "  ROW_NUMBER() OVER (ORDER BY ro.roomno DESC) AS ranking ";
		sql += "  FROM room ro ";
		sql += "  INNER JOIN placetable pl ON ro.placecode = pl.placecode ";
		sql += "  INNER JOIN store st ON ro.stno = st.stno ";

		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();

		if (mode != null && !mode.equals("all")) {
		    if (mode.equals("category")) {
		        sql += "  WHERE st." + mode + " LIKE '%" + keyword + "%' ";
		    } else if (mode.equals("place")) {
		        sql += "  WHERE pl." + mode + " LIKE '%" + keyword + "%' ";
		    } else if (mode.equals("stname")) {
		        sql += "  WHERE st." + mode + " LIKE '%" + keyword + "%' ";
		    }
		}

		sql += "  ORDER BY ro.roomno DESC ";
		sql += ") ";
		sql += "WHERE ranking BETWEEN ? AND ?";
		
		
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
		Room bean = new Room();
		
		bean.setCategory(rs.getString("category"));
		bean.setOrdertime(rs.getString("ordertime"));
		bean.setPlace(rs.getString("place"));
		bean.setStname(rs.getString("stname"));
		bean.setRoomname(rs.getString("roomname"));
		bean.setRoomno(rs.getInt("roomno"));

		
		return bean;
	}

	public int GetTotalRecordCount() throws Exception {
		// 테이블의 총 행개수를 구합니다.
		String sql = " select count(*) as cnt from room " ;
		
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
		System.out.print("검색할 필드명 : " + mode);
		System.out.println(", 검색할 키워드 : " + keyword);
		
		// 테이블의 총 행개수를 구합니다.
		String sql = " select count(*) as cnt from room ro" ;
		sql += " inner join placetable pl ON ro.placecode = pl.placecode";
		sql += " inner join store st ON ro.stno = st.stno";
		
		if(mode == null || mode.equals("all") ) {			
		}else if(mode.equals("category")){ // 전체 모드가 아니면
			sql += " where st." + mode + " like '%" + keyword + "%'" ;
		}else if(mode.equals("place")){
			sql += " where pl." + mode + " like '%" + keyword + "%'" ;
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
