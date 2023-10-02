package com.apartogether.model.bean;

public class VoteCount {
	
	
	private int cnt; // 6
	private String selectvotecol; // 3
	
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

	public VoteCount(int cnt, String selectvotecol) {
		super();
		this.cnt = cnt;
		this.selectvotecol = selectvotecol;
	}

	@Override
	public String toString() {
		return "VoteCount [cnt=" + cnt + ", selectvotecol=" + selectvotecol + "]";
	}
	
	
	
	
}
