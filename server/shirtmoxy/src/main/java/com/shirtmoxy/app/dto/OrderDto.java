package com.shirtmoxy.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.shirtmoxy.app.entity.Address;
import com.shirtmoxy.app.entity.Customer;
import com.shirtmoxy.app.entity.OrderItem;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class OrderDto {

	private Long id;

	@NotEmpty(message = "Order tracking number is required")
	@Size(max = 255, message = "Order tracking number cannot exceed 255 characters")
	private String orderTrackingNumber;

	@Digits(integer = 19, fraction = 2, message = "Total price must have a maximum of 19 digits, including 2 decimal places")
	@DecimalMin(value = "0.00", inclusive = true, message = "Total price must be greater than or equal to 0.00")
	private BigDecimal totalPrice;

	private int totalQuantity;

	@Size(max = 128, message = "Status cannot exceed 128 characters")
	private String status;

	private Date dateCreated;

	private Date lastUpdated;

	private Set<OrderItem> orderItems = new HashSet<>();

	private Customer customer;

	private Address shippingAddress;

	private Address billingAddress;

	public OrderDto() {

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

//	public void add(OrderItem item) {
//
//		if (item != null) {
//			if (orderItems == null) {
//				orderItems = new HashSet<>();
//			}
//
//			orderItems.add(item);
//			item.setOrder(this);
//		}
//	}
}
