package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.OrderLog;
import com.apartogether.model.dao.StoreDao;
import com.apartogether.utility.Paging;

public class StoreOrderLogController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		StoreDao dao = new StoreDao();
		
		List<OrderLog> lists = dao.getOrderLog(stno);
		
		//페이지 사이즈
		int totalCount = dao.getTotalOrderCount(stno);
		String url = super.getUrlInfomation("stOrLog");
		boolean isGrid = false;
		
		Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
		
		request.setAttribute("bean", lists);
		request.setAttribute("pageInfo", pageInfo);
		
		super.gotoPage("store/StoreOrderLog.jsp");
	}
}
