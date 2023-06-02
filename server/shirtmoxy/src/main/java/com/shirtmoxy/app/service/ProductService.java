package com.shirtmoxy.app.service;

import org.springframework.data.domain.Page;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.exception.ProductException;

public interface ProductService {

	public Page<ProductDto> readAllProducts(int pageNum) throws ProductException;

	public ProductDto readById(int id) throws ProductException;

	public Page<ProductDto> search(String keyword, int pageNum) throws ProductException;
}
