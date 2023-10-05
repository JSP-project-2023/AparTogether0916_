package com.apartogether.model.bean;

public class Room {
	// 방 목록을 위한 room클래스
	private int roomno;
	private String stname;
	private String ordertime;
	private String roomname;
	private String place;
	private String category;
	private int row_count;
	
	
	public int getRow_count() {
		return row_count;
	}
	public void setRow_count(int row_count) {
		this.row_count = row_count;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}

	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	@Override
	public String toString() {
		return "Room [roomno=" + roomno + ", stname=" + stname + ", ordertime=" + ordertime + ", roomname=" + roomname
				+ ", place=" + place + ", category=" + category + ", row_count=" + row_count + "]";
	}

	
}
