package com.shirtmoxy.app.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

public class SizeDto {

	private int id;

	@NotEmpty(message = "Type is required and must not be empty")
	private String type;

	@Digits(integer = 3, fraction = 1, message = "Length must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.0", inclusive = true, message = "Length must be greater than or equal to 0.0")
	@DecimalMax(value = "999.9", inclusive = true, message = "Length must be less than or equal to 999.9")
	private BigDecimal length;

	@Digits(integer = 3, fraction = 1, message = "Width must have a maximum of 8 digits, including 2 decimal places")
	@DecimalMin(value = "0.0", inclusive = true, message = "Width must be greater than or equal to 0.0")
	@DecimalMax(value = "999.9", inclusive = true, message = "Width must be less than or equal to 999.9")
	private BigDecimal width;

	public SizeDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

}
