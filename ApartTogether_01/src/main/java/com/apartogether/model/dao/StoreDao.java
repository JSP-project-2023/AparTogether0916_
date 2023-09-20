package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

		conn = super.getConnection();
		conn.setAutoCommit(false);
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
	
	//스토어 수정하기 위한 1개 가게 조회
	public Store getStorebyId(String id, String stno) throws Exception {
		Store bean = new Store();
		
		String sql = "select * from store where id=? and stno=? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setString(2, stno);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			bean.setStno(rs.getString("stno"));
			bean.setId(rs.getString("id"));
			bean.setStname(rs.getString("stname"));
			bean.setFee(rs.getInt("fee"));
			bean.setCategory(rs.getString("category"));
			bean.setStplace(rs.getString("stplace"));
			bean.setSttel(rs.getString("sttel"));
			bean.setContent(rs.getString("content"));
			bean.setCeofile(rs.getString("ceofile"));
			bean.setCeono(rs.getString("ceono"));
			bean.setSttime(rs.getString("sttime"));
			bean.setStlogo(rs.getString("stlogo"));
			bean.setRedday(rs.getString("redday"));
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

		return bean;
	}

	//가게 업데이트
	public int UpdateStore(Store bean) throws SQLException {
		System.out.println("들어온 객체 : " + bean);
		
		int cnt = 0;
		String sql ="update store set stname=?, fee=?, category=?, stplace=?, sttel=?, content=?, ceofile=?, ceono=?, sttime=?, stlogo=?, redday=?, btime=40 ";
		sql += "where id=? and stno=? ";
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getStname());
		pstmt.setInt(2, bean.getFee());
		pstmt.setString(3, bean.getCategory());
		pstmt.setString(4, bean.getStplace());
		pstmt.setString(5, bean.getSttel());
		pstmt.setString(6, bean.getContent());
		pstmt.setString(7, bean.getCeofile());
		pstmt.setString(8, bean.getCeono());
		pstmt.setString(9, bean.getSttime());
		pstmt.setString(10, bean.getStlogo());
		pstmt.setString(11, bean.getRedday());
		pstmt.setString(12, bean.getId());
		pstmt.setString(13, bean.getStno());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		return cnt;
	}
}
