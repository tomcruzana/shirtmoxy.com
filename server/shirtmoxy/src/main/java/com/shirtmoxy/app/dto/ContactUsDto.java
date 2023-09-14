package com.shirtmoxy.app.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ContactUsDto {

	private int id;

	@NotBlank(message = "First name is required")
	@Size(max = 255, message = "First name cannot exceed 255 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 255, message = "Last name cannot exceed 255 characters")
	private String lastName;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 255, message = "Email cannot exceed 255 characters")
	private String email;

	@Size(max = 255, message = "Phone number cannot exceed 255 characters")
	private String phone;	

	private ContactUsCategoryDto category;

	@NotBlank(message = "Subject is required")
	@Size(max = 255, message = "Subject cannot exceed 255 characters")
	private String subject;

	@NotBlank(message = "Message is required")
	@Size(max = 255, message = "Message cannot exceed 255 characters")
	private String Message;

	private Date dateCreated;

	private Date lastUpdated;

	public ContactUsDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ContactUsCategoryDto getCategory() {
		return category;
	}

	public void setCategory(ContactUsCategoryDto category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
