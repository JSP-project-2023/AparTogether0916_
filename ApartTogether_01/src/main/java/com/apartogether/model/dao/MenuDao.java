package com.apartogether.model.dao;

import java.sql.PreparedStatement;

import com.apartogether.model.bean.Menu;

public class MenuDao extends SuperDao {
	int cnt = -1;
	
	public int InsertMenu(Menu menu) throws Exception {
		System.out.println("InsertMenu()에 들어온 객체 : " + menu);
		
		String sql = "insert into menu(menuno, stno, menuname, price, menuimage, menudetail) ";
		sql += " values (seqmenuno.nextval, ?, ?, ?, ?, ?)";
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, menu.getStno());
		pstmt.setString(2, menu.getMenuname());
		pstmt.setInt(3, menu.getPrice());
		pstmt.setString(4, menu.getMenuimage());
		pstmt.setString(5, menu.getMenudetail());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt!=null) {pstmt.close();}
		if (conn!=null) {conn.close();}
		
		return cnt;
	}
	
	
}
