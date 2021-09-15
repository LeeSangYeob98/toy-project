package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

public class HandlableException extends RuntimeException {
	
	private static final long serialVersionUID = 7847451612288681161L;
	public ErrorCode error;
	
	// 로그를 남기지 않고 사용자에게 알림메세지만 전달하는 용도의 생성자
	public HandlableException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]); // 스택트레이스를 비워줌
	}
	// 로그를 남기고 사용자에게 알림메세지를 전달하는 용도의 생성자
	public HandlableException(ErrorCode error,Exception e) {
		this.error = error;
		e.printStackTrace();
	}

}
