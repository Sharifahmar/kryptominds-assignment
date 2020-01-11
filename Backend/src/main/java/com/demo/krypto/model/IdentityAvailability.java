package com.demo.krypto.model;

public class IdentityAvailability {
	private Boolean available;

	private String response;
	
	

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getResponse() {
		return response;
	}

	

	public void setResponse(String response) {
		this.response = response;
	}

	public IdentityAvailability(Boolean available, String response) {
		super();
		this.available = available;
		this.response = response;
	}

	
	
}
