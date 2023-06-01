package com.shirtmoxy.app.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmailSubscriptionDto {

	private Integer id;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 255, message = "Email must not exceed 255 characters")
	private String email;

	@NotNull(message = "Date created is required")
	private Date dateCreated;

	public EmailSubscriptionDto() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
