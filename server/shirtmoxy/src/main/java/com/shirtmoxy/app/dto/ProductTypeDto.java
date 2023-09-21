package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductTypeDto {

	private int id;

	@NotEmpty(message = "Name is required")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	private List<ProductDto> productList;

	public ProductTypeDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
