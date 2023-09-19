package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;
import com.apartogether.utility.PagingStore;

public class MyStoreListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		
		String storeName = request.getParameter("keyword"); // 가게명 검색했을 때
		String category = request.getParameter("categoryList"); // 카테고리 선택했을 때
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		StoreDao dao = new StoreDao();
		
		try {
//			id로 검색한 내 가게 리스트 개수 카운트
			int myTotalCount = dao.GetMyTotalStoreCount(mode, storeName, category, id); // 키워드 검색 시 mode, keyword 로 조건 검색한 총 결과 개수 count
			String url = super.getUrlInfomation("myStoreList");
			boolean isGrid = true;
			PagingStore pageInfo = new PagingStore(pageNumber, pageSize, myTotalCount, url, mode, storeName, category, isGrid);
			
			List<Store> myStoreList = dao.selectAll(pageInfo, id);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("myStoreList", myStoreList);
			
			super.gotoPage("store/MyStoreList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
