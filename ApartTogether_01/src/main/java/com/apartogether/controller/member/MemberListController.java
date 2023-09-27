package com.apartogether.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Member;
import com.apartogether.model.dao.MemberDao;
import com.apartogether.utility.Paging;
import com.apartogether.utility.PagingMember;


public class MemberListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		
		//페이징 처리를 위한 파라미터
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keywordmtype = request.getParameter("keywordmtype") ;
		String keyword = request.getParameter("keyword") ;
		
		MemberDao dao = new MemberDao();
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keywordmtype);
			String url = super.getUrlInfomation("meList") ;
			boolean isGrid = false;
			PagingMember pageInfo = new PagingMember(pageNumber, pageSize, totalCount, url, mode, keywordmtype, keyword, isGrid);
			
			List<Member> lists = dao.selectAll(pageInfo);
			List<Map<String, String>> addressSetList = new ArrayList<>();
			
			for(Member bean : lists) {
				// Δ를 기준으로 주소를 나눈다.
				String[] addressSet = bean.getAddress().split("Δ");
				
				 if (addressSet.length >= 2) {
			        Map<String, String> addressMap = new HashMap<>();
			        addressMap.put("firstPart", addressSet[0]);
			        addressMap.put("secondPart", addressSet[1]);
			        addressSetList.add(addressMap);
			    }else {
			    	Map<String, String> addressMap = new HashMap<>();
			        addressMap.put("firstPart", addressSet[0]);
			        addressMap.put("secondPart", "");
			        addressSetList.add(addressMap);
			    }
			}
			
			request.setAttribute("addressSetList", addressSetList);
			request.setAttribute("datalist", lists);
			
			//페이징관련 정보를 바인딩
			request.setAttribute("pageInfo", pageInfo);
			
			super.gotoPage("member/meList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
