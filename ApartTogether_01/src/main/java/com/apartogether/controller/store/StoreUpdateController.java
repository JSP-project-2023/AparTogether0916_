package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;
import com.apartogether.model.bean.Store;
import com.apartogether.model.dao.StoreDao;
import com.oreilly.servlet.MultipartRequest;

public class StoreUpdateController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Store bean = new Store();
		StoreDao dao = new StoreDao();
		
		//번호
		String sttel = mr.getParameter("areacode1") + "-";
		sttel += mr.getParameter("areacode2") + "-";
		sttel += mr.getParameter("areacode3");
		
		//주소
		String stplace = mr.getParameter("stplace1") + "Δ";
		stplace += mr.getParameter("stplace2");
		
		//가게 오픈 마감시간
		String sttime = mr.getParameter("startShopAmPm") + " ";
		sttime += mr.getParameter("startShopTime") + " ~ ";
		sttime += mr.getParameter("endShopAmPm") + " ";
		sttime += mr.getParameter("endShopTime");
		
		//TODO: 다오를 통해 업데이트
		bean.setId(mr.getParameter("id"));
		bean.setStno(mr.getParameter("stno"));
		bean.setStname(mr.getParameter("stname"));
		bean.setCategory(mr.getParameter("category"));
		//주소
		bean.setStplace(stplace);
		//번호
		bean.setSttel(sttel);
		
		bean.setContent(mr.getParameter("content"));
		bean.setCeofile(mr.getFilesystemName("ceofile"));

		//가게 오픈, 마감시간
		bean.setSttime(sttime);
		
		bean.setStlogo(mr.getFilesystemName("stlogo"));
		bean.setFee(Integer.parseInt(mr.getParameter("fee")));
		bean.setRedday(mr.getParameter("redday"));
		bean.setCeono(mr.getParameter("ceono"));
		
		int cnt = -1;
		
		//가게 정보 업데이트
		try {
			cnt = dao.UpdateStore(bean);
			if (cnt == -1) {
				System.out.println("실패");
			}else {
				System.out.println("성공");
				//TODO: 업데이트 후 알럿 창
				super.setAlertMessage("수정이 완료되었습니다.");
				
				//TODO: 업데이트 후 가게 상세화면으로 이동
				//컨트롤러? goto?
				super.gotoPage("common/home.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		//수정 버튼으로 update폼으로 이동
		
		//아아디 가져옴.
		String id = request.getParameter("id");
		//가게 고유번호 가져옴
		String stno = request.getParameter("stno");
				
		StoreDao dao = new StoreDao();
				
		Store bean = dao.getStorebyId(id, stno);
		
		//가게 주소
		String[] staddr = bean.getStplace().split("Δ");
		
		//가게 전화번호
		String[] sttel = bean.getSttel().split("-");
		
		
		//가게 운영시간 split
		String str = bean.getSttime().replace("~", " ");
		String[] sttime = str.split("\\s+");
		
		request.setAttribute("staddr", staddr);
		request.setAttribute("sttime", sttime);
		request.setAttribute("sttel", sttel);
		request.setAttribute("bean", bean);
		request.setAttribute("id", id);
		
		super.gotoPage("store/UpdateStore.jsp");
	}
}
