package com.apartogether.model.bean;

public class Vote {
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
<<<<<<< HEAD
		this.voteid = voteid;
=======
>>>>>>> origin/sup_new_branch02
		this.endvote = endvote;
		this.voteid = voteid;
	}

	
	
	@Override
	public String toString() {
<<<<<<< HEAD
		return "Vote [voteno=" + voteno + ", votetitle=" + votetitle + ", votecol1=" + votecol1 + ", votecol2="
				+ votecol2 + ", votecol3=" + votecol3 + ", votecol4=" + votecol4 + ", votecol5=" + votecol5
				+ ", votedate=" + votedate + ", endVote=" + endvote + ", voteid=" + voteid + "]";
=======
		StringBuilder builder = new StringBuilder();
		builder.append("Vote [voteno=");
		builder.append(voteno);
		builder.append(", votetitle=");
		builder.append(votetitle);
		builder.append(", votecol1=");
		builder.append(votecol1);
		builder.append(", votecol2=");
		builder.append(votecol2);
		builder.append(", votecol3=");
		builder.append(votecol3);
		builder.append(", votecol4=");
		builder.append(votecol4);
		builder.append(", votecol5=");
		builder.append(votecol5);
		builder.append(", votedate=");
		builder.append(votedate);
		builder.append(", endvote=");
		builder.append(endvote);
		builder.append(", voteid=");
		builder.append(voteid);
		builder.append("]");
		return builder.toString();
>>>>>>> origin/sup_new_branch02
	}

	public int getVoteno() {
		return voteno;
	}



	public Integer getEndVote() {
		return endvote;
	}

	public void setEndVote(Integer endvote) {
		this.endvote = endvote;
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

<<<<<<< HEAD
=======

>>>>>>> origin/sup_new_branch02
	public int getEndvote() {
		return endvote;
	}

<<<<<<< HEAD
=======

>>>>>>> origin/sup_new_branch02
	public void setEndvote(int endvote) {
		this.endvote = endvote;
	}

<<<<<<< HEAD
=======

>>>>>>> origin/sup_new_branch02
	public String getVoteid() {
		return voteid;
	}

<<<<<<< HEAD
	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}
	/* [ed] getter&setter */
=======

	public void setVoteid(String voteid) {
		this.voteid = voteid;
	}
	
>>>>>>> origin/sup_new_branch02
}