package com.kh.toy.common.code.member;

//enumerated type : 열거형
//서로 연관된 상수들의 집합
//서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공해준다.
public enum MemberGrade {
	
	//회원 등급 코드가 'ME00'이면 해당 등급명은 '일반'이고 연장 가능 횟수는 1회
	//'ME00', '일반', 1 = 연관된 상수들
	
	//내부적으로 enum은 Class이다.
	//ME00("일반", 1) 
	//-> public static final MemberGrade ME00 = new MemberGrade("일반", 1);
	//이라는 인스턴스를 생성한다. -> 이름으로 인스턴스를 부름(리플렉스)
	//public이기 때문에 어디에서나 접근이 가능하고,
	//static이기 때문에 언제나 접근이 가능한 인스턴스에 등급명과 연장횟수를 저장해두고
	//getter를 통해서 반환받아 사용할 수 있다.
	ME00("일반", "user", 1),
	ME01("성실", "user", 2),
	ME02("우수", "user", 3),
	ME03("vip", "user", 4),
	
	AD00("super", "admin"),
	AD01("member", "admin"),
	AD02("board", "admin");
	
	public final String DESC;
	public final String ROLE;
	public final int EXTENDABLE_CNT;
	
	private MemberGrade(String desc, String role) {
		this.DESC = desc;
		this.ROLE = role;
		this.EXTENDABLE_CNT = 99;
	}
	
	private MemberGrade(String desc, String role, int extendableCnt) {
		this.DESC = desc;
		this.ROLE = role;
		this.EXTENDABLE_CNT = extendableCnt;
	}
	
}
