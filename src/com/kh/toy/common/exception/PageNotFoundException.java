package com.kh.toy.common.exception;

public class PageNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1313747291601761454L;
	
	public PageNotFoundException() {
		super("404");
		// 스택트레이스를 비워줌
		this.setStackTrace(new StackTraceElement[0]);
	}
	
	public PageNotFoundException(String message) {
		super(message);
	}

}
