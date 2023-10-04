package com.apartogether.model.bean;

public class Vote {
	//	private Integer stno; // 가게 고유 코드
	// TODO 가게 고유 코드 Integer로 변경
	private int voteno; // 가게 고유 코드
	private String votetitle; // 회원(사업자) 아이디
	private String votecol1;
	private String votecol2;
	private String votecol3;
	private String votecol4;
	private String votecol5;
	private String votedate;
	private int endvote; // 투표 종류 여부
	private String voteid; // 투표 게시글 작성자

	public Vote() {
	}


	public Vote(int voteno, String votetitle, String votecol1, String votecol2, String votecol3, String votecol4,
			String votecol5, String votedate, int endvote, String voteid) {

		super();
		this.voteno = voteno;
		this.votetitle = votetitle;
		this.votecol1 = votecol1;
		this.votecol2 = votecol2;
		this.votecol3 = votecol3;
		this.votecol4 = votecol4;
		this.votecol5 = votecol5;
		this.votedate = votedate;
		this.endvote = endvote;
		this.voteid = voteid;
	}

	public int getVoteno() {
		return voteno;
	}


	@Override
	public String toString() {
		return "Vote [voteno=" + voteno + ", votetitle=" + votetitle + ", votecol1=" + votecol1 + ", votecol2="
				+ votecol2 + ", votecol3=" + votecol3 + ", votecol4=" + votecol4 + ", votecol5=" + votecol5
				+ ", votedate=" + votedate + ", endvote=" + endvote + ", voteid=" + voteid + "]";
	}


	public void setVoteno(int voteno) {
		this.voteno = voteno;
	}


	public String getVotetitle() {
		return votetitle;
	}


	public void setVotetitle(String votetitle) {
		this.votetitle = votetitle;
	}


	public String getVotecol1() {
		return votecol1;
	}


	public void setVotecol1(String votecol1) {
		this.votecol1 = votecol1;
	}


	public String getVotecol2() {
		return votecol2;
	}


	public void setVotecol2(String votecol2) {
		this.votecol2 = votecol2;
	}


	public String getVotecol3() {
		return votecol3;
	}


	public void setVotecol3(String votecol3) {
		this.votecol3 = votecol3;
	}


	public String getVotecol4() {
		return votecol4;
	}


	public void setVotecol4(String votecol4) {
		this.votecol4 = votecol4;
	}


	public String getVotecol5() {
		return votecol5;
	}


	public void setVotecol5(String votecol5) {
		this.votecol5 = votecol5;
	}


	public String getVotedate() {
		return votedate;
	}


	public void setVotedate(String votedate) {
		this.votedate = votedate;
	}


	public int getEndvote() {
		return endvote;
	}


	public void setEndvote(int endvote) {
		this.endvote = endvote;
	}


	public String getVoteid() {
		return voteid;
	}


	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}
	
}