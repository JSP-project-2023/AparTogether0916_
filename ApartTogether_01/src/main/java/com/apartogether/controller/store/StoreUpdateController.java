package com.apartogether.controller.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.HomeController;
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
		bean.setStno(Integer.parseInt(mr.getParameter("stno")));
		bean.setStname(mr.getParameter("stname"));
		bean.setCategory(mr.getParameter("category"));
		//주소
		bean.setStplace(stplace);
		//번호
		bean.setSttel(sttel);
		
		bean.setContent(mr.getParameter("content"));
		
		//사업자 등록증, 새로운 파일 등록여부
		String ceofile = dao.changeFile(mr.getFilesystemName("ceofile"), mr.getParameter("ceofileUpdate"));
		//가게 로고, 새로운 파일 등록여부
		String stlogofile = dao.changeFile(mr.getFilesystemName("stlogo"), mr.getParameter("stlogoUpdate"));
		
		//TODO dao의 changFile을 값을 bean.set()에 넣어줌.
		bean.setCeofile(ceofile);
		bean.setStlogo(stlogofile);
		
		
		//가게 오픈, 마감시간
		bean.setSttime(sttime);
		
		bean.setFee(Integer.parseInt(mr.getParameter("fee")));
		bean.setBtime(Integer.parseInt(mr.getParameter("btime")));
		bean.setRedday(mr.getParameter("redday"));
		bean.setCeono(mr.getParameter("ceono"));
		
		int cnt = -1;
		
		//가게 정보 업데이트
		try {
			cnt = dao.UpdateStore(bean);
			if (cnt == -1) {
				super.setAlertMessage("가게 수정에 실패하였습니다.");
			}else {
				super.setSuccessAlertMessage("가게 수정이 완료되었습니다.");
				//TODO: 업데이트 후 가게 상세화면으로 이동
				new MyStoreListController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);
		//아아디 가져옴.
		String id = request.getParameter("id");
		//가게 고유번호 가져옴
		int stno = Integer.parseInt(request.getParameter("stno"));
				
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
