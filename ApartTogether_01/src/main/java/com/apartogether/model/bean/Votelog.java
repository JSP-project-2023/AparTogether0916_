package com.apartogether.model.bean;

public class Votelog {
	private int voteno; // 투표한 게시글넘버 | vote에 대한 fk(cascade) 
	private String voteid; //투표한 사람의 id 값 | members에 대한 fk(set null)
	private String selectvotecol; //투표한 항목

	public Votelog() {
	}

	@Override
	public String toString() {
		return "Votelog [voteno=" + voteno + ", voteid=" + voteid + ", selectvotecol=" + selectvotecol + "]";
	}

	public Votelog(int voteno, String voteid, String selectvotecol) {
		super();
		this.voteno = voteno;
		this.voteid = voteid;
		this.selectvotecol = selectvotecol;
	}

	/* [st] getter&setter */
	public int getVoteno() {
		return voteno;
	}

	public void setVoteno(int voteno) {
		this.voteno = voteno;
	}

	public String getVoteid() {
		return voteid;
	}

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}

	public String getSelectvotecol() {
		return selectvotecol;
	}

	public void setSelectvotecol(String selectvotecol) {
		this.selectvotecol = selectvotecol;
	}
	/* [ed] getter&setter */
}