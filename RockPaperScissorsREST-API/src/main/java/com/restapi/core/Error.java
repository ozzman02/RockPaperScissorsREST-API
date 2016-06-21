package com.restapi.core;

/**
 * The Class Error.
 */
public class Error {
	
	/** The error message. */
	private String errorMessage;

	/**
	 * Instantiates a new error.
	 */
	public Error() {}

	/**
	 * Instantiates a new error.
	 *
	 * @param errorMessage the error message
	 */
	public Error(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Error [errorMessage=" + errorMessage + "]";
	}
	
}
