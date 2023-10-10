package com.apartogether.model.bean;

public class Order {
//	주문 내역을 위한 bean 클래스
	private String ordertime;
	private String stname;
	private String stlogo;
	private int roomno;

	

	
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
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}

	public String getStlogo() {
		return stlogo;
	}
	public void setStlogo(String stlogo) {
		this.stlogo = stlogo;
	}
	@Override
	public String toString() {
		return "Order [ordertime=" + ordertime + ", stname=" + stname + ", stlogo=" + stlogo + ", roomno=" + roomno
				+ "]";
	}

	
	
}
