package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

//예외처리가 강제되지 않는 UnCheckedException
//DAO에서 SQLException 대신 DataAccessException을 반환해서
//Service 단에서 예외처리가 강제되는 상황을 방지
public class DataAccessException extends HandlableException {

	private static final long serialVersionUID = 521587827126031031L;
	
	public DataAccessException(Exception e) {
		super(ErrorCode.DATABASE_ACCESS_ERROR,e);
	}
}
