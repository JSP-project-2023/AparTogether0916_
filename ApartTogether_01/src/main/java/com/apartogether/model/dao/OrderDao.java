package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Order;
import com.apartogether.model.cart.CartItem;



public class OrderDao extends SuperDao{

	public List<Order> GetHistory(String id) throws Exception {
		
		String sql = "SELECT DISTINCT ro.roomno, ro.ordertime, st.stname, st.stlogo  ";
		sql +=" from room ro inner join store st on ro.stno = st.stno ";
		sql +=" inner join personal pe on ro.roomno = pe.roomno where pe.id = ?";
		sql +=" order by ordertime desc";
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		List<Order> orderList = new ArrayList<Order>();
		
		while(rs.next()) {
			orderList.add(this.makeOrderBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return orderList;
	}

	private Order makeOrderBean(ResultSet rs) throws Exception {
		
		Order bean = new Order();
		

		bean.setOrdertime(rs.getString("ordertime"));
		bean.setStlogo(rs.getString("stlogo"));
		bean.setStname(rs.getString("stname"));
		bean.setRoomno(rs.getInt("roomno"));
			
		return bean;
	}

	public Order getDetailHistory(int roomno, String id) throws Exception{
		
		String sql = " select ordertime, stname, stlogo, roomno from room inner join store on room.stno = store.stno where roomno = ?";
		sql += " and id = ?";
		Order bean = null;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2,id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = this.makeOrderBean(rs);
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
		
	
	}
	
	public List<CartItem> showDetail(int roomno, String id) throws Exception {
		String sql = " select menu.menuno, menu.menuname , pe.qty, menu.price " ;
		sql += " from (room ro inner join personal pe " ;
		sql += " on ro.roomno = pe.roomno) inner join menu   " ;
		sql += " on pe.menuno = menu.menuno and ro.roomno = ?  " ;
		sql += " and pe.id = ?" ;
		sql += " order by pe.orderno desc " ;
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);
		ResultSet rs = pstmt.executeQuery();
		
		List<CartItem> lists = new ArrayList<CartItem>();
		while(rs.next()) {
			lists.add(this.makeCartItemBena(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
		
	}

	private CartItem makeCartItemBena(ResultSet rs) throws Exception{
		CartItem item = new CartItem();
		
		item.setMenuname(rs.getString("menuname"));
		item.setMenuno(rs.getInt("menuno"));
		item.setPrice(rs.getInt("price"));
		item.setQty(rs.getInt("qty"));
		return item;
	}

	
	
	
	
}
