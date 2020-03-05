package com.kosmo.mintchoco.member;

import java.sql.Date;

/*
 * 담당자 : 유지상, 장세진
 */

public class MemberVO {
	private int number; //회원 번호
	private String email; //회원 이메일
	private String password; //회원 비밀번호
	private String name; // 회원 이름
	private String nickName; // 회원 별명
	private int age; //회원 나이
	private String gender; //회원 성별
	private String introduce; // 회원 정보
	private Date indate; // 회원 가입일
	
	private String like_flag; // 찜하기 목록 공개 여부
	private String assessment_flag; // 평가 목록 공개 여부
	private String info_flag; // 회원정보 공개 여부
	
	public MemberVO() {
		super();
	}

	public MemberVO(int number, String email, String password, String name, String nickName, int age, String gender,
			String introduce, Date indate, String like_flag, String assessment_flag, String info_flag) {
		this.number = number;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.age = age;
		this.gender = gender;
		this.introduce = introduce;
		this.indate = indate;
		this.like_flag = like_flag;
		this.assessment_flag = assessment_flag;
		this.info_flag = info_flag;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public String getLike_flag() {
		return like_flag;
	}

	public void setLike_flag(String like_flag) {
		this.like_flag = like_flag;
	}

	public String getAssessment_flag() {
		return assessment_flag;
	}

	public void setAssessment_flag(String assessment_flag) {
		this.assessment_flag = assessment_flag;
	}

	public String getInfo_flag() {
		return info_flag;
	}

	public void setInfo_flag(String info_flag) {
		this.info_flag = info_flag;
	}

	@Override
	public String toString() {
		return "MemberVO [number=" + number + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", nickName=" + nickName + ", age=" + age + ", gender=" + gender + ", introduce=" + introduce
				+ ", indate=" + indate + ", like_flag=" + like_flag + ", assessment_flag=" + assessment_flag
				+ ", info_flag=" + info_flag + "]";
	}
	
	
	
	
	
	
	
	
	
	
}


