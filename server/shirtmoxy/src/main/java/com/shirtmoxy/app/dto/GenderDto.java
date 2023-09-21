package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class GenderDto {

	private int id;

	@NotEmpty(message = "Type is required and must not be empty")
	@Size(min = 1, max = 12, message = "Type must have a length of 1")
	private String type;

	private List<ProductDto> productList;

	public GenderDto() {
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

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
