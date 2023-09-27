package com.apartogether.model.bean;

public class OrderLog {
	private String menuname;
	private String addr;
	private String ordertime;
	private int qty;
	private int sellprice;
	private int orderno;

	
	@Override
	public String toString() {
		return "OrderLog [menuname=" + menuname + ", addr=" + addr + ", ordertime=" + ordertime + ", qty=" + qty
				+ ", sellPrice=" + sellprice + ", orderno=" + orderno + "]";
	}


	public int getSellprice() {
		return sellprice;
	}


	public void setSellprice(int sellprice) {
		this.sellprice = sellprice;
	}


	public String getMenuname() {
		return menuname;
	}


	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getOrdertime() {
		return ordertime;
	}


	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getOrderno() {
		return orderno;
	}


	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	
	
	
	
}
