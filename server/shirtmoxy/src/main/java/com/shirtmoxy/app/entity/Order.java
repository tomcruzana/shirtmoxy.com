package com.shirtmoxy.app.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotEmpty(message = "Order tracking number is required")
	@Size(max = 255, message = "Order tracking number cannot exceed 255 characters")
	@Column(name = "order_tracking_number", nullable = false, length = 255)
	private String orderTrackingNumber;

	@Digits(integer = 19, fraction = 2, message = "Total price must have a maximum of 19 digits, including 2 decimal places")
	@DecimalMin(value = "0.00", inclusive = true, message = "Total price must be greater than or equal to 0.00")
	@Column(name = "total_price", nullable = false, columnDefinition = "DECIMAL(19,2)")
	private BigDecimal totalPrice;

	@Column(name = "total_quantity")
	private int totalQuantity;

	@Size(max = 128, message = "Status cannot exceed 128 characters")
	@Column(name = "status", nullable = true, length = 128)
	private String status;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private Set<OrderItem> orderItems = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "shipping_address_id", referencedColumnName = "id", unique = true)
	private Address shippingAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "billing_address_id", referencedColumnName = "id", unique = true)
	private Address billingAddress;

	public Order() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void add(OrderItem item) {

		if (item != null) {
			if (orderItems == null) {
				orderItems = new HashSet<>();
			}

			orderItems.add(item);
			item.setOrder(this);
		}
	}
}
