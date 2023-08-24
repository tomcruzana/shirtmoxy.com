package com.shirtmoxy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.service.CheckoutService;

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

}