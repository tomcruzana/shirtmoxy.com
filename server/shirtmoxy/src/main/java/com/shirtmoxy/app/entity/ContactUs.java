package com.shirtmoxy.app.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact_us")
public class ContactUs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "First name is required")
	@Size(max = 255, message = "First name cannot exceed 255 characters")
	@Column(nullable = false, length = 255)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(max = 255, message = "Last name cannot exceed 255 characters")
	@Column(nullable = false, length = 255)
	private String lastName;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 255, message = "Email cannot exceed 255 characters")
	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Size(max = 255, message = "Phone number cannot exceed 255 characters")
	@Column(nullable = true, length = 255)
	private String phone;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
	private ContactUsCategory category;

	@NotBlank(message = "Subject is required")
	@Size(max = 255, message = "Subject cannot exceed 255 characters")
	@Column(nullable = false, length = 255)
	private String subject;

	@NotBlank(message = "Message is required")
	@Size(max = 255, message = "Message cannot exceed 255 characters")
	@Column(nullable = false, length = 255)
	private String Message;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	public ContactUs() {
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

	public ContactUsCategory getCategory() {
		return category;
	}

	public void setCategory(ContactUsCategory category) {
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
