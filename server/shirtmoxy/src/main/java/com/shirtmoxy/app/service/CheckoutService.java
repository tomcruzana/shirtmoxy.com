package com.shirtmoxy.app.service;

import com.shirtmoxy.app.dto.PurchaseDto;
import com.shirtmoxy.app.dto.PurchaseResponseDto;
import com.shirtmoxy.app.exception.PurchaseException;

public interface CheckoutService {

	PurchaseResponseDto placeOrder(PurchaseDto purchase) throws PurchaseException;
}
