package com.kh.toy.member.model.dto;

import java.sql.Date;

//7월 12일 월요일
//DTO(Data Transfer Object)
//데이터 전송 객체
//DB로부터 얻어온 데이터를 service(비즈니스 로직)으로 보낼 때 사용하는 객체
//비즈니스 로직을 포함하지 않는, 순수하게 데이터 전송만을 위한 객체
//getter/setter, [equals, hashCode, toString] 메서드만을 갖는다.

//*** 참고 
//도메인 객체 : DB 테이블에서 조회 해온 한 행(ROW)의 값을 저장하는 용도로 
//	사용하는 객체
//DOMAIN OBJECT, VALUE OBJECT(VO), DTO, ENTITYM, BEAN

//DTO의 조건(JAVA BEAN 규약, 20년 전 서블릿 시절)
//1. 모든 필드변수는 PRIVATE일 것
//2. 반드시 기본생성자가 존재할 것.(매개변수가 있는 생성자가 있더라도 필수이다.)
//3. 모든 필드변수는 GETTER/SETTER 메서드를 가져야 한다.


//오라클 - 자바 타입 매핑
//CHAR, VARCHAR2 -> String
//DATE -> java.util.Date, java.sql.Date
//NUMBER -> int, double(float)
public class Member {
	
//	USER_ID	VARCHAR2(36 CHAR)
//	PASSWORD	VARCHAR2(60 CHAR)
//	EMAIL	VARCHAR2(50 CHAR)
//	GRADE	CHAR(4 CHAR)
//	TELL	VARCHAR2(15 CHAR)
//	REG_DATE	DATE
//	RENTABLE_DATE	DATE
//	IS_LEAVE	NUMBER
	
	private String userId;
	private String password;
	private String email;
	private String grade;
	private String tell;
	private Date regDate;
	private Date rentable;
	private int isLeave;
	
	public Member() {
		super();
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getTell() {
		return tell;
	}
	
	public void setTell(String tell) {
		this.tell = tell;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	public Date getRentable() {
		return rentable;
	}
	
	public void setRentable(Date rentable) {
		this.rentable = rentable;
	}
	
	public int getIsLeave() {
		return isLeave;
	}
	
	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", email=" + email + ", grade=" + grade
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentable=" + rentable + ", isLeave=" + isLeave + "]";
	}
}
