/**
 *
 */
package com.demo.krypto.model;

/**
 * @author ahmar.akhtar.sharif
 * @param <T>
 *
 */
public class ApiResponseGenericModel<T> {

	private T data;

	private Boolean status;

	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponseGenericModel(T data, Boolean status) {
		super();
		this.data = data;
		this.status = status;
	}

	public ApiResponseGenericModel(String message, Boolean status) {
		super();
		this.status = status;
		this.message = message;
	}

}
