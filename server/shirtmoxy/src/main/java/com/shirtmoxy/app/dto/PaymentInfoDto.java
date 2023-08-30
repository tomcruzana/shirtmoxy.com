package com.shirtmoxy.app.dto;

public class PaymentInfoDto {
	private int amount;
	private String currency;
	private String emailReceipt;

	public PaymentInfoDto() {
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getEmailReceipt() {
		return emailReceipt;
	}

	public void setEmailReceipt(String emailReceipt) {
		this.emailReceipt = emailReceipt;
	}

}
