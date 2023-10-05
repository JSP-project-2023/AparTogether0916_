package com.apartogether.model.cart;

public class CartItem {
	private String id;
	private int menuno;
	private String menuname;
	private int qty;
	private int price;
	
	public CartItem() {
		// TODO Auto-generated constructor stub
	}
	

	public CartItem(int menuno, String menuname, int qty, int price) {
		super();
		this.menuno = menuno;
		this.menuname = menuname;
		this.qty = qty;
		this.price = price;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMenuno() {
		return menuno;
	}

	public void setMenuno(int menuno) {
		this.menuno = menuno;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", menuno=" + menuno + ", menuname=" + menuname + ", qty=" + qty + ", price="
				+ price + "]";
	}
	
	
	

}
