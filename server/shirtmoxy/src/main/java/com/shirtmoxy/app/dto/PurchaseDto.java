package com.shirtmoxy.app.dto;

import java.util.Set;

import com.shirtmoxy.app.entity.Address;
import com.shirtmoxy.app.entity.Customer;
import com.shirtmoxy.app.entity.Order;
import com.shirtmoxy.app.entity.OrderItem;

public class PurchaseDto {
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

	public PurchaseDto() {

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	

}
