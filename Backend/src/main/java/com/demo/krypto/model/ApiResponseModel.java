/**
 *
 */
package com.demo.krypto.model;

/**
 * @author ahmar.akhtar.sharif
 *
 */
public class ApiResponseModel {

	private String message;

	private Boolean status;

	public ApiResponseModel(String message, Boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

}
