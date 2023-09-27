package com.apartogether.model.bean;

public class Vote {

	private int voteno;
	private String votetitle;
	private String votecol1;
	private String votecol2;
	private String votecol3;
	private String votecol4;
	private String votecol5;
	private String votedate;
	private int endVote; // 투표 종류 여부

	public Vote() {
	}
	
	@Override
	public String toString() {
		return "Vote [voteno=" + voteno + ", votetitle=" + votetitle + ", votecol1=" + votecol1 + ", votecol2="
				+ votecol2 + ", votecol3=" + votecol3 + ", votecol4=" + votecol4 + ", votecol5=" + votecol5
				+ ", votedate=" + votedate + ", endVote=" + endVote + "]";
	}

	public Integer getEndVote() {
		return endVote;
	}

	public void setEndVote(Integer endVote) {
		this.endVote = endVote;
	}

	/* [st] getter&setter */
	public int getVoteno() {
		return voteno;
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
	/* [ed] getter&setter */
}