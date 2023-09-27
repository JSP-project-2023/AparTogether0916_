package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Menu;

public class MenuDao extends SuperDao {
	int cnt = -1;
	
//	메뉴 등록
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
	
//	전체 메뉴 가져오기
	public List<Menu> selectAll(int stno) throws Exception {
		String sql = "select * from menu where stno=? order by menuno desc";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		ResultSet rs = pstmt.executeQuery();
		
		List<Menu> menuList = new ArrayList<Menu>();
		Menu menuBean = new Menu(); // detail 구분자 수정용
		
		while (rs.next()) {
			menuBean = this.getBeanData(rs);
			
			String detail = menuBean.getMenudetail();
			String str[] = null;
			
//			메뉴 설명 구분자 처리
			if (detail.indexOf("Δ") < 0) { // 구분자가 없으면 그래도 입력
				menuBean.setMenudetail(detail);
			} else {
				str = detail.split("Δ");
				menuBean.setMenudetail(str[0] + "<br>" + str[1]); // 화면에 보여줄 때는 한 칸에 다 넣기
			}
			menuList.add(menuBean);
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
		
		return menuBean;
	}
	
//	메뉴 업데이트
	public int UpdateMenu(Menu bean) throws Exception {
		String sql = "update menu set menuname=?, price=?, menuimage=?, menudetail=? where menuno=?";
		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getMenuname());
		pstmt.setInt(2, bean.getPrice());
		pstmt.setString(3, bean.getMenuimage());
		pstmt.setString(4, bean.getMenudetail());
		pstmt.setInt(5, bean.getMenuno());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt!=null) { pstmt.close(); }
		if (conn!=null) { conn.close(); }
		
		return cnt;
	}
	
//	메뉴 하나 정보 가져오기
	public Menu GetDataByNo(int menuno) throws Exception {
		String sql = "select * from menu where menuno=?";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, menuno);
		ResultSet rs = pstmt.executeQuery();
		
		Menu menu = new Menu();
		
		if (rs.next()) {
			menu = this.getBeanData(rs);
		}
		
		return menu;
	}

	public String changeFile(String newFileName, String oldFileName) {
		// 새로 등록된 사진이 없다면
		if (newFileName == null) {
			return oldFileName; // 옛날 파일 리턴
		} else {
			return newFileName;
		}
	}

	public int DeleteMenu(int menuno) throws Exception {
		String sql = "delete from menu where menuno=?";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, menuno);
		
		cnt = pstmt.executeUpdate();
		
		if (pstmt!=null) {pstmt.close();}
		if (conn!=null) {conn.close();}
		
		return cnt;
	}
}