package com.apartogether.model.bean;

public class VoteCount {
	
	
	private int countCol1; // 6
	private int countCol2; // 3
	private int countCol3; // 1
	private int countCol4; // 
	private int countCol5; //
	private int countAll = countCol1 + countCol2 + countCol3 + countCol4 + countCol5;     
	
	public VoteCount() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * private double percentCol1 = Math.round(countCol1/countAll * 100); private
	 * double percentCol2 = Math.round(countCol2/countAll * 100); private double
	 * percentCol3 = Math.round(countCol3/countAll * 100); private double
	 * percentCol4 = Math.round(countCol4/countAll * 100); private double
	 * percentCol5 = Math.round(countCol5/countAll * 100);
	 * 
	 */
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public int getCountCol1() {
		return countCol1;
	}
	public void setCountCol1(int countCol1) {
		this.countCol1 = countCol1;
	}
	public int getCountCol2() {
		return countCol2;
	}
	public void setCountCol2(int countCol2) {
		this.countCol2 = countCol2;
	}
	public int getCountCol3() {
		return countCol3;
	}
	public void setCountCol3(int countCol3) {
		this.countCol3 = countCol3;
	}
	public int getCountCol4() {
		return countCol4;
	}
	public void setCountCol4(int countCol4) {
		this.countCol4 = countCol4;
	}
	public int getCountCol5() {
		return countCol5;
	}
	public void setCountCol5(int countCol5) {
		this.countCol5 = countCol5;
	}
	@Override
	public String toString() {
		return "VoteCount [countAll=" + countAll + ", countCol1=" + countCol1 + ", countCol2=" + countCol2
				+ ", countCol3=" + countCol3 + ", countCol4=" + countCol4 + ", countCol5=" + countCol5 + "]";
	}
	public VoteCount(int countAll, int countCol1, int countCol2, int countCol3, int countCol4, int countCol5) {
		super();
		this.countAll = countAll;
		this.countCol1 = countCol1;
		this.countCol2 = countCol2;
		this.countCol3 = countCol3;
		this.countCol4 = countCol4;
		this.countCol5 = countCol5;
	}

	
	
	
}
