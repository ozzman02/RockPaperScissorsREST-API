package com.restapi.core;

public class Error {
	
	private String errorMessage;

	public Error() {}

	public Error(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "Error [errorMessage=" + errorMessage + "]";
	}
	
}
