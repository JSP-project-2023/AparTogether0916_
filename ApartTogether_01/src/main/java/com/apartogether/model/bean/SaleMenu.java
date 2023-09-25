package com.apartogether.model.bean;

//판매 메뉴 확인 Bean
public class SaleMenu {
	private String menuname;
	private int cumqty;
	private int cumsale;
	private String stname;
	

	@Override
	public String toString() {
		return "SaleMenu [menuname=" + menuname + ", cumqty=" + cumqty + ", cumsale=" + cumsale + "]";
	}
	
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public int getCumqty() {
		return cumqty;
	}
	public void setCumqty(int cumqty) {
		this.cumqty = cumqty;
	}
	public int getCumsale() {
		return cumsale;
	}
	public void setCumsale(int cumsale) {
		this.cumsale = cumsale;
	}
	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}
	
}
