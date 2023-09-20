package com.apartogether.model.bean;

public class Store {
	
	//TODO 가게 고유 코드 Integer로 변경
	private String stno; // 가게 고유 코드
	private String id; // 회원(사업자) 아이디
	private String stname; // 가게 이름
	private Integer fee; // 배달비
	private String category; // 카테고리
	private String stplace; // 가게 위치
	private String sttel; // 가게 전화번호
	private String content; // 가게 소개
	private String ceofile; // 사업자등록증 파일(업로드 할때)
	private String ceono; // 사업자등록번호 ex) 041-89-00154
	private String sttime; // 가게 운영 시간
	private String stlogo; // 가게 로고 이미지(업로드)
	private String redday; // 휴무일
	private Integer btime; // 배달 시간 ex) 65분
	
	public Store() {
	}
	
	public String getStno() {
		return stno;
	}
	public void setStno(String stno) {
		this.stno = stno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStname() {
		return stname;
	}
	public void setStname(String stname) {
		this.stname = stname;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStplace() {
		return stplace;
	}
	public void setStplace(String stplace) {
		this.stplace = stplace;
	}
	public String getSttel() {
		return sttel;
	}
	public void setSttel(String sttel) {
		this.sttel = sttel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCeofile() {
		return ceofile;
	}
	public void setCeofile(String ceofile) {
		this.ceofile = ceofile;
	}
	public String getCeono() {
		return ceono;
	}
	public void setCeono(String ceono) {
		this.ceono = ceono;
	}
	public String getSttime() {
		return sttime;
	}
	public void setSttime(String sttime) {
		this.sttime = sttime;
	}
	public String getStlogo() {
		return stlogo;
	}
	public void setStlogo(String stlogo) {
		this.stlogo = stlogo;
	}
	public String getRedday() {
		return redday;
	}
	public void setRedday(String redday) {
		this.redday = redday;
	}
	public Integer getBtime() {
		return btime;
	}
	public void setBtime(Integer btime) {
		this.btime = btime;
	}
	@Override
	public String toString() {
		return "Store [stno=" + stno + ", id=" + id + ", stname=" + stname + ", fee=" + fee + ", category=" + category
				+ ", stplace=" + stplace + ", sttel=" + sttel + ", content=" + content + ", ceofile=" + ceofile
				+ ", ceono=" + ceono + ", sttime=" + sttime + ", stlogo=" + stlogo + ", redday=" + redday + ", btime="
				+ btime + "]";
	}
	
}
