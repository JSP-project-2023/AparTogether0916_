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
		bean.setSttime(rs.getString("sttime"));
		bean.setRoomno(rs.getInt("roomno"));
		return bean;
	}
	private Combo01 getBeanData3(ResultSet rs) throws Exception {
		Combo01 bean = new Combo01();
		bean.setAllprice(rs.getInt("allprice"));
		return bean;
	}
	public Combo01 getRoomDetailInfo(Integer roomno) throws Exception {

		String sql = " select ro.orderplace ,st.stname ,st.fee,ro.roomname,st.sttime,ro.roomno";
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

	public int UpdateReady(int roomno, String id, String ready) throws Exception {
		String sql = " update room_status set ready = 'ready' ";
		sql += " where roomno = ? and id = ? ";
		PreparedStatement pstmt = null;

		int cnt = -1;
		conn = super.getConnection();
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);

		cnt = pstmt.executeUpdate();

		conn.commit();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return cnt;
	}

	public int DeleteReady(int roomno, String id) throws Exception {

		PreparedStatement pstmt = null;
		int cnt = -1;

		conn = super.getConnection();
		conn.setAutoCommit(false);

		String sql = " delete from room_status where id = ? and roomno = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setInt(2, roomno);
		
		
		cnt = pstmt.executeUpdate();

		conn.commit();

		
		
		sql = " delete from personal where id = ? and roomno = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, id);
		pstmt.setInt(2, roomno);
		
		cnt = pstmt.executeUpdate();

		conn.commit();

		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		return cnt;
	}

	public int UpdateReady2(int roomno, String id, String ready) throws Exception {
		String sql = " update room_status set ready = 'not' ";
		sql += " where roomno = ? and id = ? ";
		PreparedStatement pstmt = null;

		int cnt = -1;
		conn = super.getConnection();
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setString(2, id);

		cnt = pstmt.executeUpdate();

		conn.commit();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return cnt;
	}

	
	

	public List<Combo01> getAllMenu(Integer roomno) throws Exception{
		
		// 한 가게에 대한 모든 메뉴 정보를 가져옴
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
		Combo01 menuBean = new Combo01();
		while(rs.next()) {
			menuBean = this.makeMenuBean(rs);
			
			String detail = menuBean.getMenuDetail();
			String str[] = null;
			
//			메뉴 설명 구분자 처리
			if (detail.indexOf("Δ") < 0) { // 구분자가 없으면 그래도 입력
				menuBean.setMenuDetail(detail);
			} else {
				str = detail.split("Δ");
				menuBean.setMenuDetail(str[0] + "<br>" + str[1]); // 화면에 보여줄 때는 한 칸에 다 넣기
			}
			
			menuList.add(menuBean);
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
	

	public int getMinOrderno(Integer roomno) throws Exception{
		// 방장을 찾아내기 위해 가장 작은 주문번호를 얻어냄
		String sql = "select Min(orderno)from room ro  ";
		sql +=" inner join personal pe on ro.roomno = pe.roomno  ";
		sql +=" where ro.roomno = ?";

		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		ResultSet rs = pstmt.executeQuery();
		
		int bean = 0;
		
		if(rs.next()) {
			bean = rs.getInt("Min(orderno)");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}

	// 방장 아이디 구하기
	public String getBangjang(Integer roomno, int minorderno) throws Exception{
		// 가장 작은 주문번호를 이용해 방장을 구해냄
		String sql = "select id from room ro ";
		sql +=" inner join personal pe on ro.roomno = pe.roomno  ";
		sql +=" where ro.roomno = ? and pe.orderno = ?";

		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		pstmt.setInt(2, minorderno);
		ResultSet rs = pstmt.executeQuery();
		
		String bean = null;
		
		if(rs.next()) {
			bean = rs.getString("id");
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return bean;
	}
	
	// 방에 남아있는 사람수를 세기위함
	public int getCountRoomMember(int roomno) throws Exception{
				String sql = "select count(*) from personal ";
				sql +=" where roomno = ?";

				conn = super.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, roomno);
				ResultSet rs = pstmt.executeQuery();
				
				int su = 0;
				
				if(rs.next()) {
					su = rs.getInt("count(*)");
				}
				
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
				
				return su;
	}
	// 방을 지움
	public int DeleteRoom(int roomno) throws Exception {
		PreparedStatement pstmt = null;
		int cnt = -1;

		conn = super.getConnection();
		conn.setAutoCommit(false);

		String sql = " delete from room where roomno = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, roomno);
		
		
		cnt = pstmt.executeUpdate();

		conn.commit();

		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		return cnt;
	}
	public String getRemainingTime(Integer roomno) throws Exception{
		String roomtime = "";
		
		String sql =" SELECT "; 
		sql+= "		CASE  ";
		sql+= "			 WHEN ordertime + NUMTODSINTERVAL(pointtime,'MINUTE') - SYSDATE < 0   ";
		sql+= "		 THEN TO_CHAR(  ";
		sql+= "		 TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),'HH24:MI:SS')  ";
		sql+= "			  ELSE TO_CHAR(  ";
		sql+= "			  TO_DATE('1970-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')  ";
		sql+= "		 + (ordertime + NUMTODSINTERVAL(pointtime,'MINUTE') - SYSDATE), ' HH24:MI:SS')  ";
		sql+= "		  END AS roomtime ";
		sql+= "		 FROM room where roomno=? ";
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, roomno);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			roomtime = rs.getString("roomtime");
		
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return roomtime;

	}

	public int DeleteRoomInfo(Integer roomno) throws Exception{
		PreparedStatement pstmt = null;
		int cnt = -1;

		conn = super.getConnection();
		conn.setAutoCommit(false);

		String sql = " delete from personal where roomno = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, roomno);
		
		cnt = pstmt.executeUpdate();

		
		
		sql = " delete from room_status where roomno = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, roomno);
		
		cnt = pstmt.executeUpdate();
		
		sql = " delete from room where roomno = ?";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, roomno);
		
		
		cnt = pstmt.executeUpdate();


		conn.commit();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		return cnt;
	}

	

	
}
