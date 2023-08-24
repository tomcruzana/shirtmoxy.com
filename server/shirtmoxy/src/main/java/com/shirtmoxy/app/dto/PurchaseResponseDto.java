package com.shirtmoxy.app.dto;

public class PurchaseResponseDto {
	private final String orderTrackingNumber;

	public PurchaseResponseDto(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

}
