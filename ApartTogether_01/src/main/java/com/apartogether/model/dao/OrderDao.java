package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apartogether.model.bean.Combo01;
import com.apartogether.model.bean.Order;
import com.apartogether.model.bean.Personal;
import com.apartogether.model.cart.CartItem;



public class OrderDao extends SuperDao{

	public List<Order> GetHistory(String id) throws Exception {
		
		String sql = "SELECT DISTINCT ro.roomno, ro.ordertime, st.stname, st.stlogo  ";
		sql +=" from room ro inner join store st on ro.stno = st.stno ";
		sql +=" inner join personal pe on ro.roomno = pe.roomno where pe.id = ? and pe.confirm = 'success'" ;
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

	public int InsertData(Personal bean) throws Exception{
		// 주문 정보를 입력하는 함수
		
		System.out.println(bean); 
		
		String sql = " insert into personal(orderno, menuno, roomno, id, qty, confirm) " ;
		sql += " values(seqorderno.nextval, ?,  ?, ?, ? , 'not')" ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bean.getMenuno());
		pstmt.setInt(2, bean.getRoomno());
		pstmt.setString(3, bean.getId());
		pstmt.setInt(4, bean.getQty());

		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public Map<Integer, Integer> getDatabyPk(int roomno, String id) throws Exception{
		String sql = " select menuno, qty " ;
		sql += " from personal" ;
		sql += " where roomno = ?  " ;
		sql += " and id = ?" ;

		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);

		ResultSet rs = pstmt.executeQuery();
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(rs.next()) {
			map.put(rs.getInt("menuno"),rs.getInt("qty"));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return map;
	}

	public int UpdateData(Personal bean) throws Exception{

		System.out.println(bean); 
		
		String sql = " update personal set qty = qty + ?" ;
		sql += " where menuno = ?";
		sql += " and roomno = ?";
		sql += " and id = ?";
		
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bean.getQty());
		pstmt.setInt(2, bean.getMenuno());
		pstmt.setInt(3, bean.getRoomno());
		pstmt.setString(4, bean.getId());
		
		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public Object findIdroomstatus(String id, int roomno) throws Exception{

		String sql = " select ready from room_status where id = ? and roomno = ?";
		String bean = null;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,id);
		pstmt.setInt(2,roomno);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = rs.getString("ready");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
		
	}



	public int InsertIdroomstatus(int roomno, String id) throws Exception {
		String sql = " insert into room_status" ;
		sql += " values(?, ?, 'not')" ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);


		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
		
	}

	public int DeletePersonal(String id, int roomno) throws Exception{
		String sql = " delete from personal" ;
		sql += " where roomno = ? and id = ?" ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);


		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public int DeleteRoomStatus(String id, int roomno) throws Exception{
		String sql = " delete from room_status" ;
		sql += " where roomno = ? and id = ?" ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);


		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public int Deletemenu(String id, int roomno, int menuno) throws Exception{
		String sql = " delete from personal" ;
		sql += " where roomno = ? and id = ? and menuno = ?" ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);
		pstmt.setInt(3, menuno);

		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public int getMenuno(String menuname) throws Exception{
		String sql = " select menuno from menu where menuname = ?";
		int bean = 0;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,menuname);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = rs.getInt("menuno");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
		
	}

	public List<Personal> getPersonal(String id, int roomno) throws Exception {
		String sql = " select * from personal " ;
		sql += " where roomno = ? and id = ?" ;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);
		ResultSet rs = pstmt.executeQuery();
		
		List<Personal> lists = new ArrayList<Personal>();
		while(rs.next()) {
			lists.add(this.makePersonal(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
		
	}

	private Personal makePersonal(ResultSet rs) throws Exception{
		Personal bean = new Personal();
		
		bean.setId(rs.getString("id"));
		bean.setMenuno(rs.getInt("menuno"));
		bean.setRoomno(rs.getInt("roomno"));
		bean.setQty(rs.getInt("qty"));
		return bean;
	}

	public int updatePersonal(Integer roomno) throws Exception{
		
		String sql = " update personal set confirm = 'success'" ;
		sql += " where roomno = ?";
		
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, roomno);
	
		
		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}

	public int getTotalMember(Integer roomno) throws Exception{
		String sql = " select count(*) from room_status where roomno = ? ";
		int bean = 0;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,roomno);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = rs.getInt("count(*)");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	public int getReadyMember(Integer roomno) throws Exception{
		String sql = " select count(*) from room_status where roomno = ? ";
		sql += " and ready = 'ready'";
		int bean = 0;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,roomno);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = rs.getInt("count(*)");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	public int getStorefee(int roomno) throws Exception{
		String sql = " select fee from store st inner join room ro on ro.stno = st.stno ";
		sql += " where roomno = ?";
		int bean = 0;
		
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,roomno);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bean = rs.getInt("fee");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	

	



	

	
	

	
	
	
	
}
