package com.shirtmoxy.app.entity;

//@Entity
//@Table(name = "")
public class EmailSubscription {
	
	/* TODO: add hibernate annotations */
	private String email;
	private String dateCreated;

	public EmailSubscription() {

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
