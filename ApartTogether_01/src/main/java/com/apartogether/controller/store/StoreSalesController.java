package com.apartogether.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.SaleMenu;
import com.apartogether.model.dao.StoreDao;

public class StoreSalesController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		int stno = Integer.parseInt(request.getParameter("stno"));
		
		StoreDao dao = new StoreDao();
		
		List<SaleMenu> lists = null;
		//가게 누적 매출 데이터 가져오기
		lists = dao.getcumSales(stno);
		
		for(SaleMenu i : lists) {
			System.out.println(i);
		}
		
		request.setAttribute("bean", lists);
		
		//가게 점주는 추후 세션에서 받아옴 
		
		super.gotoPage("store/StoreSales.jsp");
	}
}
