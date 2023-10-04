package com.apartogether.model.bean;

public class VoteCount {
	
	
	private int cnt; // 각 항목에 대한 투표한 모든 사람의 수
	private String selectvotecol; // 각 항목(1. 항목, 2. 항목 ... list 형식으로 전체 항목 저장예정)
	private int total; // 퍼센테이지를 위한 총 합
	
	public VoteCount() {
		// TODO Auto-generated constructor stub
	}

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getSelectvotecol() {
		return selectvotecol;
	}
	public void setSelectvotecol(String string) {
		this.selectvotecol = string;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
	public VoteCount(int cnt, String selectvotecol, int total) {
		super();
		this.cnt = cnt;
		this.selectvotecol = selectvotecol;
		this.total = total;
	}

	@Override
	public String toString() {
		return "VoteCount [cnt=" + cnt + ", selectvotecol=" + selectvotecol + ", total=" + total + "]";
	}

	
	
	
	
}