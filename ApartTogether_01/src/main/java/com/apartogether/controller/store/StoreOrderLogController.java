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

		//stno 무조건 받아야됨.
		int stno = Integer.parseInt(request.getParameter("stno"));
		StoreDao dao = new StoreDao();
		
		String url = super.getUrlInfomation("stOrLog");
		//페이지 사이즈
		int totalCount = dao.getTotalOrderCount(stno);
		boolean isGrid = false;
		
		Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, stno, isGrid);
		
		/* List<OrderLog> lists = dao.getOrderLog(stno); */
		
		List<OrderLog> orderlist = dao.getSelectAll(pageInfo);
		
		request.setAttribute("bean", orderlist);
		request.setAttribute("pageInfo", pageInfo);
		
		super.gotoPage("store/StoreOrderLog.jsp");
	}
}
