package com.apartogether.model.bean;

public class Store {
//	private Integer stno; // 가게 고유 코드
	
	//TODO 가게 고유 코드 Integer로 변경
	private int stno; // 가게 고유 코드
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
	private String ststatus; // 영업 상태 ex) open, close
	
	
	public Store() {
	}
	
	public String getststatus() {
		return ststatus;
	}
	public void setststatus(String ststatus) {
		this.ststatus = ststatus;
	}
	public int getStno() {
		return stno;
	}
	public void setStno(int stno) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("Store [stno=");
		builder.append(stno);
		builder.append(", id=");
		builder.append(id);
		builder.append(", stname=");
		builder.append(stname);
		builder.append(", fee=");
		builder.append(fee);
		builder.append(", category=");
		builder.append(category);
		builder.append(", stplace=");
		builder.append(stplace);
		builder.append(", sttel=");
		builder.append(sttel);
		builder.append(", content=");
		builder.append(content);
		builder.append(", ceofile=");
		builder.append(ceofile);
		builder.append(", ceono=");
		builder.append(ceono);
		builder.append(", sttime=");
		builder.append(sttime);
		builder.append(", stlogo=");
		builder.append(stlogo);
		builder.append(", redday=");
		builder.append(redday);
		builder.append(", btime=");
		builder.append(btime);
		builder.append(", ststatus=");
		builder.append(ststatus);
		builder.append("]");
		return builder.toString();
	}
	
}
