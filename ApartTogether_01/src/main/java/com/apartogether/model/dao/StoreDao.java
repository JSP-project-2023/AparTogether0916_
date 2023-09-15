package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.apartogether.model.bean.Store;

public class StoreDao extends SuperDao {
	int cnt = -1;
	
	//스토어 등록 메소드
	public int Insertstore(Store store) throws SQLException {
		System.out.println("Insertstore()에 들어온 객체 : " + store);
		
		//가게 고유코드, id는 추후 가져오는 걸로... BTIME 1-16분 임의로 넣을것
		String sql = " insert into store(stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime , stlogo, redday, btime)";
		sql += " values(seqstore.nextval, 'soon', ?,?,?,?, ?,?,?,?,?, ?,?,40)";

		conn = super.getConnection(); conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, store.getStname());
		pstmt.setInt(2, store.getFee());
		pstmt.setString(3, store.getCategory());
		pstmt.setString(4, store.getStplace());
		pstmt.setString(5, store.getSttel());
		pstmt.setString(6, store.getContent());
		pstmt.setString(7, store.getCeofile());
		pstmt.setString(8, store.getCeono());
		pstmt.setString(9, store.getSttime());
		pstmt.setString(10, store.getStlogo());
		pstmt.setString(11, store.getRedday());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		return cnt;
	}
}
