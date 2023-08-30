package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.PaymentInfoDto;
import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.entity.Customer;
import com.shirtmoxy.app.entity.Order;
import com.shirtmoxy.app.entity.OrderItem;
import com.shirtmoxy.app.exception.PurchaseException;
import com.shirtmoxy.app.repository.CustomerRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private Environment environment;

	public CheckoutServiceImpl(@Value("${stripe.key.secret}") String stripeSecretKey) {
		// initialize the Stripe API key with the secret key
		Stripe.apiKey = stripeSecretKey;
	}

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

	@Override
	public PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException {
		// create the stripe payment type & properties
		List<String> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");

		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentInfo.getAmount());
		params.put("currency", paymentInfo.getCurrency());
		params.put("payment_method_types", paymentMethodTypes);
		params.put("description", environment.getProperty("stripe.payment.intent.description"));
		params.put("receipt_email", paymentInfo.getEmailReceipt());

		return PaymentIntent.create(params);
	}

}
