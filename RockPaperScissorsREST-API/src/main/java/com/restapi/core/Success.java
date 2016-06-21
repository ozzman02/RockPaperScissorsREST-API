package com.restapi.core;

/**
 * The Class Success.
 */
public class Success {
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new success.
	 */
	public Success() { }

	/**
	 * Instantiates a new success.
	 *
	 * @param message the message
	 */
	public Success(String message) {
		this.message = message;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Success [message=" + message + "]";
	}
	
}
