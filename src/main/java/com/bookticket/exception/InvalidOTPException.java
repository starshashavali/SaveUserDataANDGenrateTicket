package com.bookticket.exception;

public class InvalidOTPException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidOTPException(String msg) {
		super(msg);
	}

}
