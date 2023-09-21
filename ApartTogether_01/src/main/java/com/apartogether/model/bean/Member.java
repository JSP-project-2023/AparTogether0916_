package com.apartogether.model.bean;

public class Member {
	private String id; 		/* 멤버 아이디 */
	private String mtype; 	/* admin / user / biz | 3종류 */
	private String name; 	/* 이름 */
	private String password;/* 패스워드 */
	private String phone; 	/* 휴대폰번호(String형식) */
	private String birth; 	/* 날짜형식을 따라갑니다. */
	private String gender; 	/* male / female | 성별 */
	private String nickname;/* 닉네임 */
	private String address; /* 주소 */
	private String profile; /* 프로필 이미지 */
	private String passwordanswer;
	private String passwordquest;
	
	
	/* 생성자 */
	public Member() {
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", mtype=" + mtype + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", birth=" + birth + ", gender=" + gender + ", nickname=" + nickname + ", address=" + address
				+ ", profile=" + profile + "]";
	}


	/* [st.] getter&setter */	
	
	
	public String getId() {
		return id;
	}
	public String getPasswordanswer() {
		return passwordanswer;
	}

	public void setPasswordanswer(String passwordanswer) {
		this.passwordanswer = passwordanswer;
	}

	public String getPasswordquest() {
		return passwordquest;
	}

	public void setPasswordquest(String passwordquest) {
		this.passwordquest = passwordquest;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/* [ed.] getter&setter */
	
	
	
	
	public Member(String id, String mtype, String name, String password, String phone, String birth, String gender,
			String nickname, String address, String profile) {
		super();
		this.id = id;
		this.mtype = mtype;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.nickname = nickname;
		this.address = address;
		this.profile = profile;
	}

}