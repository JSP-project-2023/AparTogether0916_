package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;
import com.apartogether.utility.Paging;

public class StoreListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		
		StoreDao dao = new StoreDao();
		
		try {
			int totalCount = dao.GetTotalStoreCount(mode, keyword);
			String url = super.getUrlInfomation("StoreList");
			boolean isGrid = true;
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			List<Store> storeList = dao.selectAll(pageInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		super.gotoPage("store/stList.jsp");
		
	}

}
