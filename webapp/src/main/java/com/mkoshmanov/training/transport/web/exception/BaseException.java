package com.mkoshmanov.training.transport.web.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public BaseException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public BaseException() {
		super();
	}
}


