package com.shirtmoxy.app.service;

import com.shirtmoxy.app.dto.PaymentInfoDto;
import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.exception.PurchaseException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

	PurchaseResponseDto placeOrder(PurchaseDto purchase) throws PurchaseException;
	
	PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException;
}
