package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.apartogether.model.bean.Menu;
import com.apartogether.model.bean.OrderLog;
import com.apartogether.model.bean.SaleMenu;
import com.apartogether.model.bean.Store;
import com.apartogether.utility.Paging;
import com.apartogether.utility.PagingStore;

public class StoreDao extends SuperDao {
	int cnt = -1;
	
	
	//스토어 등록 메소드
	public int Insertstore(Store store) throws SQLException {
		System.out.println("Insertstore()에 들어온 객체 : " + store);
		
		String sql = " insert into store(stno, id, stname, fee, category, stplace, sttel, content, ceofile, ceono, sttime , stlogo, redday, btime)";
		sql += " values(seqstore.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, store.getId());
		pstmt.setString(2, store.getStname());
		pstmt.setInt(3, store.getFee());
		pstmt.setString(4, store.getCategory());
		pstmt.setString(5, store.getStplace());
		pstmt.setString(6, store.getSttel());
		pstmt.setString(7, store.getContent());
		pstmt.setString(8, store.getCeofile());
		pstmt.setString(9, store.getCeono());
		pstmt.setString(10, store.getSttime());
		pstmt.setString(11, store.getStlogo());
		pstmt.setString(12, store.getRedday());
		pstmt.setInt(13, store.getBtime());
		
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
	public Store getStorebyId(String id, int stno) throws Exception {
		Store bean = new Store();
		
		String sql = "select * from store where id=? and stno=? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setInt(2, stno);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			bean.setStno(rs.getInt("stno"));
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
		
		System.out.println(bean);
		return bean;
	}
	
//	전체 가게 리스트 목록 화면에 출력 (페이징 처리, mode / keyword 검색 기능) - 이리수
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
		System.out.println("StoreDao!!!! mode : " + mode + " / keyword : " + keyword + " cate : " + category); // 어떤 값으로 검색했는지 확인
		
		if (mode==null || mode.equals("all") || keyword==null || keyword.equals("all") || category==null || category.equals("all")) {
		} else if (mode.equals("category")){
			sql += " where " + mode + " like '%" + category + "%'";
		} else {
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
//		페이징 처리
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
		
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return storeAllList;
	}
	
	
//	개인별 가게 리스트 목록 화면에 출력 (id 매개변수, 페이징 처리, mode / keyword 검색 기능) - 이리수
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
		
		if (mode==null || mode.equals("all") || keyword==null || keyword.equals("all") || category==null || category.equals("all")) {
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
		
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		
		return storeAllList;
	}
	
	
//	현재 로그인한 id로만 내 가게 리스트 리턴 => MenuManage - 이리수
	public List<Store> selectAll(String id) throws Exception {
		String sql = "select * from store where id=?";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		List<Store> stList = new ArrayList<Store>();
		
		while	(rs.next()) {
			stList.add(this.getBeanData(rs));
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
		
		return stList;
	}
	
	
//	store에 bean 한개 값 셋팅해서 Store 객체로 리턴 - 이리수
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
		
		return storeBean;
	}

//	 불러올 가게 몇 개 인지 카운팅  - 이리수
	public int GetTotalStoreCount(String mode, String keyword, String categoryItem) throws Exception {
		cnt = -1; // 카운팅 담을 변수
		
		String sql = "select count(*) as cnt from store";
		
		if (mode == null || mode.equals("all") || keyword == null || keyword.equals("all") || categoryItem == null || categoryItem.equals("all")) { // 전체 검색 일때는 조건 부여 x
			System.out.println("alll!!!!");
		} else if (!(categoryItem == null || categoryItem.equals("all")) && mode.equals("category")){
			System.out.println("not all");
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
	
//	내가 등록한 가게가 몇개인지 카운딩 - 이리수
	public int GetMyTotalStoreCount(String mode, String keyword, String categoryItem, String id) throws Exception {
		cnt = -1; // 카운팅 담을 변수
		
		String sql = "select count(*) as cnt from store where id=?";
		
		if (mode == null || mode.equals("all") || keyword == null || keyword.equals("all") || categoryItem == null || categoryItem.equals("all")) { // 전체 검색 일때는 조건 부여 x
			System.out.println("alll!!!!");
		} else if (!(categoryItem == null || categoryItem.equals("all")) && mode.equals("category")){
			System.out.println("not all");
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
	
//	가게 1개 삭제
	public int deleteStore(int stno) throws Exception {
		String sql = "delete from store where stno = ?";
		cnt = -1;
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		cnt = pstmt.executeUpdate();

		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}
	
	//가게 업데이트
	public int UpdateStore(Store bean) throws SQLException {
		System.out.println("들어온 객체 : " + bean);
		
		int cnt = 0;
		String sql ="update store set stname=?, fee=?, category=?, stplace=?, sttel=?, content=?, ceofile=?, ceono=?, sttime=?, stlogo=?, redday=?, btime=40 ";
		sql += "where id=? and stno=? ";
		conn = super.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getStname());
		pstmt.setInt(2, bean.getFee());
		pstmt.setString(3, bean.getCategory());
		pstmt.setString(4, bean.getStplace());
		pstmt.setString(5, bean.getSttel());
		pstmt.setString(6, bean.getContent());
		pstmt.setString(7, bean.getCeofile());
		pstmt.setString(8, bean.getCeono());
		pstmt.setString(9, bean.getSttime());
		pstmt.setString(10, bean.getStlogo());
		pstmt.setString(11, bean.getRedday());
		pstmt.setString(12, bean.getId());
		pstmt.setInt(13, bean.getStno());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if (pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
		return cnt;
	}
	//가게 메뉴에서 보기 위한 가게 조회
	public Store getStorebyStno(int stno) throws Exception {
		Store bean = new Store();

		String sql = "select * from store where stno=? ";

		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, stno);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			bean.setStno(rs.getInt("stno"));
			bean.setId(rs.getString("id"));
			bean.setStname(rs.getString("stname"));
			bean.setFee(rs.getInt("fee"));
			bean.setCategory(rs.getString("category"));
			bean.setStplace(splitAddr(rs.getString("stplace")));
			bean.setSttel(rs.getString("sttel"));
			bean.setContent(nullChecking(rs.getString("content")));
			bean.setCeofile(rs.getString("ceofile"));
			bean.setCeono(rs.getString("ceono"));
			bean.setSttime(rs.getString("sttime"));
			bean.setStlogo(rs.getString("stlogo"));
			bean.setRedday(nullChecking(rs.getString("redday")));
			bean.setBtime(rs.getInt("btime"));

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
	//주소 분리 메소드
	private String splitAddr(String address) {
		return address.replace("Δ", " ");
	}
	
	//null값 반환 방지
	private String nullChecking(String string) {
		if(string == null) {
			string = "　";
		}
		return string;
	}
	
	//파일 교체 메소드
	public String changeFile(String newfileName, String oldfileName) {
		// 새로 등록된 사진이 없다면
		if (newfileName == null) {
			// 옛날 파일을 리턴
			return oldfileName;
		} else {
			// 등록된 파일을 리턴
			return newfileName;
		}	
	}
	
	//가게번호로 가게 메뉴 목록을 가져옴.
	public List<Menu> getMenubyStno(int stno) throws SQLException {
		String sql = " select * from menu where stno=? order by menuno";
		
		List<Menu> lists = new ArrayList<Menu>();
		Menu bean = null;
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, stno);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			bean = getMenuData(rs);
			
			String detail = bean.getMenudetail();
			String str[] = null;
			
//			메뉴 설명 구분자 처리
			if (detail.indexOf("Δ") < 0) { // 구분자가 없으면 그래도 입력
				bean.setMenudetail(detail);
			} else {
				str = detail.split("Δ");
				bean.setMenudetail(str[0] + "<br>" + str[1]); // 화면에 보여줄 때는 한 칸에 다 넣기
			}
			
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
	
	//가게 객체로 변환
	private Menu getMenuData(ResultSet rs) throws SQLException {
		Menu bean = new Menu();
		
		bean.setMenuno(rs.getInt("menuno"));
		bean.setStno(rs.getInt("stno"));
		bean.setMenuname(rs.getString("menuname"));
		bean.setPrice(rs.getInt("price"));
		bean.setMenuimage(rs.getString("menuimage"));
		bean.setMenudetail(rs.getString("menudetail"));
	
		return bean;
	}
	
	//매출현황 데이터 가져오기.
	public List<SaleMenu> getcumSales(int stno) throws SQLException {
		List<SaleMenu> lists = new ArrayList<SaleMenu>();
		SaleMenu bean = null;
		
		String sql="select menu.menuname as menuname, sum(a.qty) as qty , sum(a.qty*menu.price) as total "
				+ "from menu inner join(select * from room inner join personal on room.roomno=personal.roomno where room.stno=?) a "
				+ "on menu.stno=a.stno and menu.menuno=a.menuno "
				+ "group by menu.menuname ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		ResultSet rs = pstmt.executeQuery();
		
		//메뉴 판매
		while(rs.next()) {
			bean = new SaleMenu();
			
			bean.setMenuname(rs.getString("menuname"));
			bean.setCumqty(rs.getInt("qty"));
			bean.setCumsale(rs.getInt("total"));		
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
	
	//날짜별 판매량 가져오는 메소드
	public Map<String, Integer> getSalePrice(int stno) throws SQLException{
		Map<String, Integer> saleMonth = new HashMap<String, Integer>();
		
		String sql = "select sum(a.qty*menu.price) as total, to_char(a.ordertime, 'yy/mm') as ordertime "
				+ "from menu inner join(select * from room inner join personal on room.roomno=personal.roomno where room.stno=?) a "
				+ "on menu.stno=a.stno and menu.menuno=a.menuno "
				+ "group by to_char(a.ordertime, 'yy/mm')";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			saleMonth.put(rs.getString("ordertime"), rs.getInt("total"));
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
		
		return saleMonth;
	}

	public int getTotalOrderCount(int stno) throws SQLException {
		int cnt = 0;
		
		String sql="select count(*) as cnt "
				+ "from menu inner join (select * from room inner join personal "
				+ "on room.roomno=personal.roomno) a "
				+ "on menu.menuno=a.menuno "
				+ "where menu.stno=? ";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, stno);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt");
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
		return cnt;
	}
	
	//페이징을 통해 데이터 베이스에서 가져옴.
	public List<OrderLog> getSelectAll(Paging pageInfo) throws SQLException {
		List<OrderLog> lists = new ArrayList<OrderLog>();
		OrderLog bean = null;
		
		String sql="select ranking, menu.menuname, a.orderplace, a.ordertime, a.qty, (a.qty*menu.price) as total , a.orderno "
				+ "from menu inner join (select orderno, ordertime, qty, orderplace, menuno, rank() over(order by orderno desc) as ranking "
				+ "from room inner join personal "
				+ "on room.roomno=personal.roomno "
				+ "where stno=?) a "
				+ "on menu.menuno=a.menuno "
				+ "where ranking between ? and ?";
		
		conn = super.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getStno());
		pstmt.setInt(2, pageInfo.getBeginRow());
		pstmt.setInt(3, pageInfo.getEndRow());
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			bean = new OrderLog();
			
			bean.setMenuname(rs.getString("menuname"));
			bean.setAddr(rs.getString("orderplace"));
			bean.setOrdertime(rs.getString("ordertime"));
			bean.setQty(rs.getInt("qty"));
			bean.setSellprice(rs.getInt("total"));
			bean.setOrderno(rs.getInt("orderno"));
			
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
}
