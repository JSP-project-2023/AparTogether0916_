package com.apartogether.model.bean;

public class Comment {
	private int cnum ; 
	private int roomno;
	private String id;
	private String content;   

	
	public int getCnum() {
		return cnum;
	}



	public void setCnum(int cnum) {
		this.cnum = cnum;
	}






	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public int getRoomno() {
		return roomno;
	}



	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}



	@Override
	public String toString() {
		return "Comment [cnum=" + cnum + ", roomno=" + roomno + ", id=" + id + ", content=" + content + "]";
	}







	
	
	
}
