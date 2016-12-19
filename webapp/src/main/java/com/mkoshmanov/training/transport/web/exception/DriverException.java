package com.mkoshmanov.training.transport.web.exception;

public class DriverException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public DriverException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public DriverException() {
		super();
	}
}