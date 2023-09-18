package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apartogether.model.bean.Store;
import com.apartogether.utility.Paging;
import com.apartogether.utility.PagingStore;

public class StoreDao extends SuperDao {

//	public List<Store> selectAll(Paging pageInfo2) throws Exception {
//		/* TopN 구문 사용해서 페이징 처리된 목록 반환 */
//		
//		/* 페이징 처리할 부분 ranking 으로 순서 정렬 */
//		String sql = "select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime ";
//		sql += " from (select stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime, stlogo, redday, btime, ";
//		sql += " rank() over(order by stno desc) as ranking from store ";
//		
//		/* 키워드 검색 */
//		String mode = pageInfo2.getMode();
//		String keyword = pageInfo2.getKeyword();
//		
//		if (mode==null || mode.equals("all") || keyword==null || keyword.equals("all")) {
//		} else {
//			sql += " where " + mode + " like '%" + keyword + "%'";
//		}
//		
//		sql += " ) where ranking between ? and ?";
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		conn = super.getConnection();
//		
//		pstmt = conn.prepareStatement(sql);
//		
//		/* 보여줄 처음, 마지막 값 설정 */
//		pstmt.setInt(1, pageInfo2.getBeginRow());
//		pstmt.setInt(2, pageInfo2.getEndRow());
//		
//		rs = pstmt.executeQuery();
//		
//		List<Store> storeAllList = new ArrayList<Store>();
//		
//		while (rs.next()) {
//			storeAllList.add(getBeanData(rs));
//		}
//		
//		return storeAllList;
//	}
	
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
		
		System.out.println("mode : " + mode + " / keyword : " + keyword);
		String sql = "select count(*) as cnt from store";
		
		if (mode == null || mode.equals("all") || keyword == null || keyword.equals("all")) { // 전체 검색 일때는 조건 부여 x
		} else if (mode.equals("category")){
			sql += " where " + mode + " like '%" + categoryItem + "%'";
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		System.out.println(sql);
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			System.out.println("rs next");
			cnt = rs.getInt("cnt");
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		System.out.println("cnt : " + cnt);
		return cnt;
	}
}
