package com.apartogether.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apartogether.controller.SuperClass;

public class MemberLogoutController extends SuperClass {
	@Override // 회원이 로그 아웃을 시도합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doGet(request, response);

		/*
		 * [st] 로그아웃 했을때 장바구니 목록 저장&불러오기 MallDao dao = new MallDao(); Map<Integer,
		 * Integer> WishList = super.mycart.GetAllCartList(); try { // Session 영역에 남겨진
		 * 장바구니가 있으면, 임시 테이블 wishlist에 추가해 줍니다. if(super.loginfo != null) {
		 * if(WishList.size() > 0 ) { dao.insertWishList(super.loginfo.getId(),
		 * WishList); } } else { super.youNeededLogin(); return; } [ed] 로그아웃 했을때 장바구니 목록
		 * 저장&불러오기
		 * 
		 * // 로그인시 저장했던 로그인 정보 등을 깨끗히 비웁니다.
		 * super.session.invalidate();
		 * super.gotoPage("member/meLoginForm.jsp"); // 로그인 페이지로 이동함
		 * 
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		// 로그인시 저장했던 로그인 정보 등을 깨끗히 비웁니다.
		super.session.invalidate();
		super.gotoPage("common/home.jsp"); // 로그인 페이지로 이동함
	}
}