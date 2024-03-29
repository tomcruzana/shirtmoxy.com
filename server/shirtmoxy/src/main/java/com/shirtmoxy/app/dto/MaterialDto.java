package com.shirtmoxy.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MaterialDto {

	private int id;

	@NotBlank(message = "Material type is required")
	@Size(max = 255, message = "Material type must not exceed 255 characters")
	private String type;

	private List<ProductDto> productList;

	public MaterialDto() {
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
