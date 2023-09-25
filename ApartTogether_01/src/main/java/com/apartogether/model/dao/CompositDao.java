package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Combo01;
import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Order;

public class CompositDao extends SuperDao {

	public List<Combo01> View01(Integer roomno, String id) throws Exception {

		String sql = " select distinct ro.roomno, mn.menuname, pr.qty, mn.price, (mn.price * pr.qty ) as totalmenu , rs.id  ";
		sql += " from room ro inner join personal pr on ro.roomno = pr.roomno inner join   ";
		sql += "  menu mn on pr.menuno = mn.menuno  inner join room_status rs on rs.id= pr.id  ";
		sql += "  where ro.roomno = ? AND pr.id IN (SELECT id FROM room_status WHERE id = ?) ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);
		rs = pstmt.executeQuery();

		List<Combo01> lists = new ArrayList<Combo01>();

		while (rs.next()) {
			Combo01 bean = new Combo01();
			bean.setRoomno(rs.getInt("roomno"));
			bean.setMenuname(rs.getString("menuname"));
			bean.setQty(rs.getInt("qty"));
			bean.setTotalmenu(rs.getInt("totalmenu"));
			bean.setPrice(rs.getInt("price"));
			bean.setId(rs.getString("id"));

			lists.add(bean);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	public Combo01 getPrice(Integer roomno, String id) throws Exception {

		String sql = " SELECT sum(mn.price * pr.qty)   as personalprice ";
		sql += " FROM room ro  ";
		sql += " INNER JOIN personal pr ON ro.roomno = pr.roomno  ";
		sql += " INNER JOIN menu mn ON pr.menuno = mn.menuno ";
		sql += " WHERE ro.roomno = ? and  pr.id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);

		rs = pstmt.executeQuery();

		Combo01 bean = null;

		if (rs.next()) {
			bean = this.getBeanData(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean;
	}
	private Combo01 getBeanData(ResultSet rs) throws Exception {
		Combo01 bean = new Combo01();
		bean.setPersonalprice(rs.getInt("personalprice"));
		return bean;
	}
	private Combo01 getBeanData2(ResultSet rs) throws Exception {
		Combo01 bean = new Combo01();
		bean.setOrderplace(rs.getString("orderplace"));
		bean.setFee(rs.getInt("fee"));
		bean.setStname(rs.getString("stname"));
		bean.setRoomname(rs.getString("roomname"));
		return bean;
	}
	private Combo01 getBeanData3(ResultSet rs) throws Exception {
		Combo01 bean = new Combo01();
		bean.setAllprice(rs.getInt("allprice"));
		return bean;
	}
	public Combo01 getRoomDetailInfo(Integer roomno) throws Exception {
		String sql = " select ro.orderplace ,st.stname ,st.fee,ro.roomname ";
		sql += " from room ro ";
		sql += " inner join store st on ro.stno = st.stno ";
		sql += " where ro.roomno = ? "; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);

		rs = pstmt.executeQuery();

		Combo01 bean2 = null;

		if (rs.next()) {
			bean2 = this.getBeanData2(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean2;

	}

	public Combo01 getAllPrice(Integer roomno) throws Exception {
		String sql = " SELECT sum(mn.price * pr.qty) as allprice ";
		sql += " FROM room ro ";
		sql += " INNER JOIN personal pr ON ro.roomno = pr.roomno ";
		sql += " INNER JOIN menu mn ON pr.menuno = mn.menuno ";
		sql += " WHERE ro.roomno = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);

		rs = pstmt.executeQuery();

		Combo01 bean3 = null;

		if (rs.next()) {
			bean3 = this.getBeanData3(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean3;
	}

	public List<Combo01> selectReadyId(Integer roomno) throws Exception {
		String sql = " select id from room_status where roomno = ? and ready='ready' ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		rs = pstmt.executeQuery();

		List<Combo01> lists2 = new ArrayList<Combo01>();

		while (rs.next()) {
			Combo01 bean = new Combo01();
			bean.setId(rs.getString("id"));
			lists2.add(bean);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists2;
	}
	public List<Combo01> selectNotReadyId(Integer roomno) throws Exception {
		String sql = " select id from room_status where roomno = ? and ready='not'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		rs = pstmt.executeQuery();

		List<Combo01> lists3 = new ArrayList<Combo01>();

		while (rs.next()) {
			Combo01 bean = new Combo01();
			bean.setId(rs.getString("id"));
			lists3.add(bean);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists3;
	}

	public int InsertReady(Integer roomno,String id) {
		String sql = "insert into room_sataus(roomno,id,ready) values(?,?,'not') ";
		
		
		return 0;
	}

	public List<Combo01> getAllMenu(Integer roomno) throws Exception{
		
		String sql = "select menuname , menuimage, menudetail, price, menuno  ";
		sql +=" from menu  ";
		sql +=" inner join store st on menu.stno = st.stno";
		sql +=" inner join room ro on st.stno = ro.stno";
		sql +=" where ro.roomno = ?";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		ResultSet rs = pstmt.executeQuery();
		
		List<Combo01> menuList = new ArrayList<Combo01>();
		
		while(rs.next()) {
			menuList.add(this.makeMenuBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return menuList;
	}

	private Combo01 makeMenuBean(ResultSet rs) throws Exception{
		
		Combo01 bean = new Combo01();
		
		bean.setMenuname(rs.getString("menuname"));
		bean.setMenuImage(rs.getString("menuimage"));
		bean.setMenuDetail(rs.getString("menudetail"));
		bean.setPrice(rs.getInt("price"));
		bean.setMenuno(rs.getInt("menuno"));
		return bean;
	}

	

	
}
