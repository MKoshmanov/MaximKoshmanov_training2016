package com.mkoshmanov.training.transport.web.exception;

public class StopException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public StopException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public StopException() {
		super();
	}
}