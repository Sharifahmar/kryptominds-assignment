/**
 *
 */
package com.demo.krypto.exception;

/**
 * @author ahmar.akhtar.sharif
 *
 */
public class CustomException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -2156368968662222805L;

	private String message;

	private Boolean success;


	public CustomException(String message, Boolean success) {
		super();
		this.message = message;
		this.success = success;

	}



	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}






}
