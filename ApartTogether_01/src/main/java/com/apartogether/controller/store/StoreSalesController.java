package com.apartogether.controller.store;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		//가게 점주는 추후 세션에서 받아옴 session.getAttribute();
		
		//날짜(년/월),총매출
		Map<String,Integer> saleMonth = dao.getSalePrice(stno);
		
		//월, 총 매출
		Map<String,Integer> sales = new HashMap<String, Integer>();
		
		for(String key : saleMonth.keySet()) {
			String[] year = key.split("/");
			//년도 체크
			if(year[0].equals("23")) {
				sales.put(year[1], saleMonth.get(key));
			}
			//배열 초기화
			year = null;
		}
		System.out.println("size(): " + sales.size());
		request.setAttribute("sales", sales);
		
		super.gotoPage("store/StoreSales.jsp");
	}
}
