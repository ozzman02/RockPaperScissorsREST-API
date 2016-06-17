package com.restapi.core;

public class Success {
	
	private String message;

	public Success() { }

	public Success(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Success [message=" + message + "]";
	}
	
}
