package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Comment;

public class CommentDao extends SuperDao{
	public int DeleteData(int cnum) throws Exception{
		// 댓글 번호 cnum을 삭제합니다.		
		PreparedStatement pstmt = null ;
		String sql = " delete from roomreply where cnum = ? " ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql) ;
				
		int cnt = -1 ;
		pstmt.setInt(1, cnum);
		
		cnt = pstmt.executeUpdate() ;
		conn.commit();
		
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return cnt ;
	}
	
	public int InsertData(Comment bean) throws Exception {
		// 넘겨진 댓글 정보를 데이터 베이스에 추가합니다.
		System.out.println(bean); 
		
		int cnt = -1 ;
		
		String sql = " insert into roomreply(cnum, roomno, id, content)" ;
		sql += " values(seqcnum.nextval, ?, ?, ?)" ; 
		
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setInt(1, bean.getRoomno());
		pstmt.setString(2, bean.getId());		
		pstmt.setString(3, bean.getContent());		
		
		cnt = pstmt.executeUpdate() ; 
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}	

	public List<Comment> GetDataByPk(int roomno) throws Exception {
		// 해당 게시물 번호에 달려 있는 댓글 목록을 정렬 시켜 반환해 줍니다.
		String sql = " select * from roomreply " ;
		sql += " where roomno = ? order by cnum asc " ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;

		conn = super.getConnection() ;
		pstmt = conn.prepareStatement(sql) ;
		pstmt.setInt(1, roomno);
		
		rs = pstmt.executeQuery() ;
		
		List<Comment> lists = new ArrayList<Comment>();
		
		while(rs.next()) {
			lists.add(GetBeanData(rs));
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return lists;
	}

	private Comment GetBeanData(ResultSet rs) throws Exception{
		Comment bean = new Comment() ;
		
		bean.setCnum(rs.getInt("cnum"));
		bean.setContent(rs.getString("content"));
		bean.setId(rs.getString("id"));
		bean.setRoomno(rs.getInt("roomno"));
	
		
		return bean;
	}





}
