package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
<<<<<<< HEAD
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;
import com.apartogether.utility.PagingStore;
=======
>>>>>>> lleebs

public class StoreListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
<<<<<<< HEAD
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		
		String storeName = request.getParameter("keyword"); // 가게명 검색했을 때
		String category = request.getParameter("categoryList"); // 카테고리 선택했을 때
		
		
		StoreDao dao = new StoreDao();
		
		try {
			int totalCount = dao.GetTotalStoreCount(mode, storeName, category); // 키워드 검색 시 mode, keyword 로 조건 검색한 총 결과 개수 count
			String url = super.getUrlInfomation("storeList");
			boolean isGrid = true;
			PagingStore pageInfo = new PagingStore(pageNumber, pageSize, totalCount, url, mode, storeName, category, isGrid);
			
			List<Store> storeList = dao.selectAll(pageInfo);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("storeList", storeList);
			super.gotoPage("store/StoreList.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
=======
		super.gotoPage("store/stList.jsp");
		
	}

}
>>>>>>> lleebs
