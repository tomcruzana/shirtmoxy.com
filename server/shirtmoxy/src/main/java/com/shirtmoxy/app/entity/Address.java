package com.shirtmoxy.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "Full name is required")
	@Size(max = 255, message = "Full name cannot exceed 255 characters")
	@Column(name = "full_name", nullable = false, length = 255)
	private String fullName;

	@NotEmpty(message = "Street is required")
	@Size(max = 255, message = "Street cannot exceed 255 characters")
	@Column(name = "street", nullable = false, length = 255)
	private String street;

	@Size(max = 255, message = "Line2 cannot exceed 255 characters")
	@Column(name = "line2", nullable = true, length = 255)
	private String line2;

	@NotEmpty(message = "City is required")
	@Size(max = 255, message = "City cannot exceed 255 characters")
	@Column(name = "city", nullable = false, length = 255)
	private String city;

	@NotEmpty(message = "State is required")
	@Size(max = 255, message = "State cannot exceed 255 characters")
	@Column(name = "state", nullable = false, length = 255)
	private String state;

	@NotEmpty(message = "Country is required")
	@Size(max = 255, message = "Country cannot exceed 255 characters")
	@Column(name = "country", nullable = false, length = 255)
	private String country;

	@NotEmpty(message = "Zip code is required")
	@Size(max = 255, message = "Zip code cannot exceed 255 characters")
	@Column(name = "zip_code", nullable = false, length = 255)
	private String zipCode;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;

	public Address() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}