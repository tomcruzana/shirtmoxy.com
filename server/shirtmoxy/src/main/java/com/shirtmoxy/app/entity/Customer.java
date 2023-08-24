package com.shirtmoxy.app.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "First name is required")
	@Size(max = 255, message = "First name cannot exceed 255 characters")
	@Column(name = "first_name", nullable = false, length = 255)
	private String firstName;

	@NotEmpty(message = "Last name is required")
	@Size(max = 255, message = "Last name cannot exceed 255 characters")
	@Column(name = "last_name", nullable = false, length = 255)
	private String lastName;

	@Size(max = 255, message = "Company cannot exceed 255 characters")
	@Column(name = "company", nullable = true, length = 255)
	private String company;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 255, message = "Email cannot exceed 255 characters")
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Order> orders = new HashSet<>();

	public Customer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void add(Order order) {

		if (order != null) {

			if (orders == null) {
				orders = new HashSet<>();
			}

			orders.add(order);
			order.setCustomer(this);
		}
	}
}
