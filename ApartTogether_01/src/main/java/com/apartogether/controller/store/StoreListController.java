package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.MemberDao;
import com.apartogether.model.dao.StoreDao;
import com.apartogether.utility.PagingStore;

public class StoreListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
//		현재 사용자 주소 출력
		try {
			Member mem = (Member) session.getAttribute("loginfo");
			if (mem!=null) {
				String id = mem.getId();
				MemberDao memdao = new MemberDao();
				Member myinfo = memdao.getDataByPrimaryKey(id);
				String[] address = myinfo.getAddress().split("Δ");
				
				String myaddress = address[0] + " " + address[1];
				
				request.setAttribute("myaddress", myaddress);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		StoreList 화면 처리
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		
		String storeName = request.getParameter("keyword"); // 가게명 검색했을 때
		String category = request.getParameter("categoryList"); // 카테고리 선택했을 때
		
		
		StoreDao dao = new StoreDao();
		
		try {
			int totalCount = dao.GetTotalStoreCount(mode, storeName, category); // 키워드 검색 시 mode, keyword 로 조건 검색한 총 결과 개수 count
			String url = super.getUrlInfomation("stList"); // 전체 가게 리스트 목록
			boolean isGrid = true;
			PagingStore pageInfo = new PagingStore(pageNumber, pageSize, totalCount, url, mode, storeName, category, isGrid);
			
//			id 조건 없이 모든 가게 리스트 불러오기
			List<Store> stList = dao.selectAll(pageInfo);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("storeList", stList);
			super.gotoPage("store/StoreList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}