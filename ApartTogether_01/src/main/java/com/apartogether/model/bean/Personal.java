package com.apartogether.model.bean;

public class Personal {
	private int menuno;
	private int roomno;
	private String id;
	private int qty;
	public int getMenuno() {
		return menuno;
	}
	public void setMenuno(int menuno) {
		this.menuno = menuno;
	}
	public int getRoomno() {
		return roomno;
	}
	public void setRoomno(int roomno) {
		this.roomno = roomno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "Personal [menuno=" + menuno + ", roomno=" + roomno + ", id=" + id + ", qty=" + qty + "]";
	}
	
}
