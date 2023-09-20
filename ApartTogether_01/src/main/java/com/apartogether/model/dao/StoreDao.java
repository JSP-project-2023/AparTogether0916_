package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Store;
import com.apartogether.utility.PagingStore;

public class StoreDao extends SuperDao {

	public List<Store> selectAll(PagingStore pageInfo) throws Exception {
		/* TopN 구문 사용해서 페이징 처리된 목록 반환 */
		
		/* 페이징 처리할 부분 ranking 으로 순서 정렬 */
		String sql = "select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime ";
		sql += " from (select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime, ";
		sql += " rank() over(order by stno desc) as ranking from store ";
		
		/* 키워드 검색 */
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		String category = pageInfo.getCategory();
		System.out.println("StoreDaooooooo mode : " + mode + " / keyword : " + keyword + " cate : " + category);
		
		if (mode==null || mode.equals("all") || keyword==null || keyword.equals("all")) {
		} else if (mode.equals("category")){
			sql += " where " + mode + " like '%" + category + "%'";
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		sql += " ) where ranking between ? and ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql);
		
		/* 보여줄 처음, 마지막 값 설정 */
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery();
		
		List<Store> storeAllList = new ArrayList<Store>();
		
		while (rs.next()) {
			storeAllList.add(getBeanData(rs));
		}
		
		return storeAllList;
	}
=======
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

	
>>>>>>> lleebs
	
	public List<Store> selectAll(PagingStore pageInfo, String id) throws Exception {
		/* TopN 구문 사용해서 페이징 처리된 목록 반환 */
		
		/* 페이징 처리할 부분 ranking 으로 순서 정렬 */
		String sql = "select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime ";
		sql += " from (select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime, ";
		sql += " rank() over(order by stno desc) as ranking from store where id=?";
		
		/* 키워드 검색 */
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		String category = pageInfo.getCategory();
		
		if (mode==null || mode.equals("all") || keyword==null || keyword.equals("all")) {
		} else if (mode.equals("category")){
			sql += " and " + mode + " like '%" + category + "%'";
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}
		
		sql += " ) where ranking between ? and ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		/* 보여줄 처음, 마지막 값 설정 */
		pstmt.setInt(2, pageInfo.getBeginRow());
		pstmt.setInt(3, pageInfo.getEndRow());
		
		rs = pstmt.executeQuery();
		
		List<Store> storeAllList = new ArrayList<Store>();
		
		while (rs.next()) {
			storeAllList.add(getBeanData(rs));
		}
		
		return storeAllList;
	}
	
	
	private Store getBeanData(ResultSet rs) throws Exception {
		Store storeBean = new Store();
		
		storeBean.setBtime(rs.getInt("btime")); // 배달 시간
		storeBean.setCategory(rs.getString("category")); // 카테고리
		storeBean.setCeofile(rs.getString("ceofile")); // 사업자등록증 파일 (업로드)
		storeBean.setCeono(rs.getString("ceono")); // 사업자등록번호
		storeBean.setContent(rs.getString("content")); // 가게 소개
		storeBean.setFee(rs.getInt("fee")); // 배달비
		storeBean.setId(rs.getString("id")); // 사업자 아이디
		storeBean.setRedday(rs.getString("redday")); // 휴무일
		storeBean.setStlogo(rs.getString("stlogo")); // 로고 이미지 (업로드)
		storeBean.setStname(rs.getString("stname")); // 가게 이름
		storeBean.setStno(Integer.parseInt(rs.getString("stno"))); // 가게 고유 코드
		storeBean.setStplace(rs.getString("stplace")); // 가게 위치
		storeBean.setSttel(rs.getString("sttel")); // 가게 전화번호
		storeBean.setSttime(rs.getString("sttime")); // 가게 운영 시간
		
		System.out.println(storeBean);
		
		return storeBean;
	}

	/* 불러올 가게 몇 개 인지 카운팅 */
	public int GetTotalStoreCount(String mode, String keyword, String categoryItem) throws Exception {
		int cnt = -1; // 카운팅 담을 변수
		
		String sql = "select count(*) as cnt from store";
		
		if (mode == null || mode.equals("all") || keyword == null || keyword.equals("all")) { // 전체 검색 일때는 조건 부여 x
		} else if (mode.equals("category")){
			sql += " where " + mode + " like '%" + categoryItem + "%'";
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}

	public int GetMyTotalStoreCount(String mode, String keyword, String categoryItem, String id) throws Exception {
		int cnt = -1; // 카운팅 담을 변수
		
		String sql = "select count(*) as cnt from store where id=?";
		
		if (mode == null || mode.equals("all") || keyword == null || keyword.equals("all")) { // 전체 검색 일때는 조건 부여 x
		} else if (mode.equals("category")){
			sql += " and " + mode + " like '%" + categoryItem + "%'";
		} else {
			sql += " and " + mode + " like '%" + keyword + "%'";
		}
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}

	public int deleteStore(int stno) throws Exception {
		String sql = "delete from store where stno = ?";
		int cnt = -1;
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		cnt = pstmt.executeUpdate();

		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}
}
