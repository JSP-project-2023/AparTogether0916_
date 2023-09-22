package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
//	전체 메뉴 가져오기 - stno 해당하는 값 찾아서
	public List<Menu> selectAll(int stno) throws Exception {
		String sql = "select * from menu where stno=? order by menuno";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		ResultSet rs = pstmt.executeQuery();
		
		List<Menu> menuList = new ArrayList<Menu>();
		
		while (rs.next()) {
			menuList.add(this.getBeanData(rs));
		}
		
		if (rs!=null) {rs.close();}
		if (pstmt!=null) {pstmt.close();}
		if (conn!=null) {conn.close();}
		
		return menuList;
	}
	
	//	bean에 하나씩 값 셋팅
	private Menu getBeanData(ResultSet rs) throws Exception {
		Menu menuBean = new Menu();
		
		menuBean.setMenudetail(rs.getString("menudetail"));
		menuBean.setMenuimage(rs.getString("menuimage"));
		menuBean.setMenuname(rs.getString("menuname"));
		menuBean.setMenuno(rs.getInt("menuno"));
		menuBean.setPrice(rs.getInt("price"));
		menuBean.setStno(rs.getInt("stno"));
		
		System.out.println(menuBean);
		return menuBean;
	}
}
