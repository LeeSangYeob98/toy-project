package com.kh.toy.common.code;

public enum Config {
	
	DOMAIN("http://localhost:7070"),
	SMTP_AUTHENTICATION_ID("enql2012@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("ansdj8870@"),
	COMPANY_EMAIL("enql2012@gmail.com"),
	// UPLOAD_PATH("C:\\CODE\\upload\\"); //운영서버용 경로
	FILE_UPLOAD_PATH("C:\\CODE\\upload\\"); // 개발서버용 경로, 운영서버용 경로는 따로 만들어야함
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	}
}
