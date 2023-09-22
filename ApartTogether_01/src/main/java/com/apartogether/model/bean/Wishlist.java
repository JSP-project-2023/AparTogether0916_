package com.apartogether.model.bean;

public class Wishlist {
	private String nickname;
	private String menuname;
	private int menuono;
	private int fee;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public int getMenuono() {
		return menuono;
	}
	public void setMenuono(int menuono) {
		this.menuono = menuono;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "Wishlist [nickname=" + nickname + ", menuname=" + menuname + ", menuono=" + menuono + ", fee=" + fee
				+ "]";
	}
	
	
		
}
