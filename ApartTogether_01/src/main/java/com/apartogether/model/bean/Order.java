package com.apartogether.model.bean;

public class Order {
//	주문 내역을 위한 bean 클래스
	private int orderno;
	private String ordertime;
	private String stname;
	private String menuname;
	private String stlogo;
	private int qty;
	private int totalprice;
	
	
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
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
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getStlogo() {
		return stlogo;
	}
	public void setStlogo(String stlogo) {
		this.stlogo = stlogo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	@Override
	public String toString() {
		return "Order [orderno=" + orderno + ", ordertime=" + ordertime + ", stname=" + stname + ", menuname="
				+ menuname + ", stlogo=" + stlogo + ", qty=" + qty + ", totalprice=" + totalprice + "]";
	}
	
	
}
