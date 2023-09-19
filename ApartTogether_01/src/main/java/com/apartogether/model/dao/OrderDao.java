package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Order;



public class OrderDao extends SuperDao{

	public List<Order> GetHistory(String id) throws Exception {
		
		String sql = " select pe.orderno, ro.ordertime,st.stname, me.menuname,st.stlogo,pe.qty,me.price ";
		sql +=" from (personal pe inner join menu me on pe.menuno = me.menuno) ";
		sql +=" inner join store st on me.stno = st.stno ";
		sql +=" inner join room ro on ro.roomno = pe.roomno where pe.id = ?";
		
		
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
		
		bean.setMenuname(rs.getString("menuname"));
		bean.setOrderno(rs.getInt("orderno"));
		bean.setOrdertime(rs.getString("ordertime"));
		bean.setQty(rs.getInt("qty"));
		bean.setStlogo(rs.getString("stlogo"));
		bean.setStname(rs.getString("stname"));
		bean.setTotalprice(rs.getInt("price")*rs.getInt("qty"));
			
		return bean;
	}
	
	
}
