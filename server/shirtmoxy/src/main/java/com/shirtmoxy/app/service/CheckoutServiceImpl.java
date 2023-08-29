package com.shirtmoxy.app.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.entity.Customer;
import com.shirtmoxy.app.entity.Order;
import com.shirtmoxy.app.entity.OrderItem;
import com.shirtmoxy.app.exception.PurchaseException;
import com.shirtmoxy.app.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public PurchaseResponseDto placeOrder(PurchaseDto purchase) throws PurchaseException {

		// retrieve the order info from dto
		Order order = purchase.getOrder();

		// set order initial status
		order.setStatus("order processed");

		// generate tracking number string
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);

		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));

		// populate order with billingAddress and shippingAddress
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());

		// populate customer with order
		Customer customer = purchase.getCustomer();

		// check if the customer exists already
		String theEmail = customer.getEmail();

		Customer customerFormDb = customerRepository.findByEmail(theEmail);
		if (customerFormDb != null) {
			customer = customerFormDb;
		}

		customer.add(order);

		// save to the database
		customerRepository.save(customer);

		// return a response containing the tracking number string
		return new PurchaseResponseDto(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {

		// generate a random UUID number (UUID version-4)
		// For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier

		// @TODO : add a unique validation filter
		return UUID.randomUUID().toString();
	}

}
