package com.shirtmoxy.app.dto;

public class EmailSubscriptionDto {

	private String email;
	private String dateCreated;

	public EmailSubscriptionDto(String email, String dateCreated) {
		this.email = email;
		this.dateCreated = dateCreated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

}
