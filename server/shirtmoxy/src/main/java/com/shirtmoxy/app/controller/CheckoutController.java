package com.shirtmoxy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.PaymentInfoDto;
import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	@Autowired
	private CheckoutService checkoutService;

	@PostMapping("/purchase")
	public PurchaseResponseDto placeOrder(@RequestBody PurchaseDto purchase) {
		PurchaseResponseDto purchaseResponse = checkoutService.placeOrder(purchase);

		return purchaseResponse;
	}

	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDto paymentInfo) throws StripeException {
		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		String paymentStr = paymentIntent.toJson();

		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}

}