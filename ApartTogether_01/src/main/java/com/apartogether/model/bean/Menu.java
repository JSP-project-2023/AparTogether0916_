package com.apartogether.model.bean;

public class Menu {
	private Integer menuno;
	private Integer stno;
	private String menuname;
	private Integer price;
	private String menuimage; 
	private String menudetail;
	
	
	public Integer getMenuno() {
		return menuno;
	}
	public void setMenuno(Integer menuno) {
		this.menuno = menuno;
	}
	public Integer getStno() {
		return stno;
	}
	public void setStno(Integer stno) {
		this.stno = stno;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getMenuimage() {
		return menuimage;
	}
	public void setMenuimage(String menuimage) {
		this.menuimage = menuimage;
	}
	public String getMenudetail() {
		return menudetail;
	}
	public void setMenudetail(String menudetail) {
		this.menudetail = menudetail;
	}
	@Override
	public String toString() {
		return "Menu [menuno=" + menuno + ", stno=" + stno + ", menuname=" + menuname + ", price=" + price
				+ ", menuimage=" + menuimage + ", menudetail=" + menudetail + "]";
	}
	
	public Menu() {
	}
}