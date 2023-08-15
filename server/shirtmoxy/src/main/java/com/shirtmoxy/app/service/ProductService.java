package com.shirtmoxy.app.service;

import org.springframework.data.domain.Page;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.exception.ProductException;

public interface ProductService {

	public Page<ProductDto> readAllProducts(int pageNum, int pageSize) throws ProductException;

	public ProductDto readById(int id) throws ProductException;

	public Page<ProductDto> search(String keyword, int pageNum, int pageSize) throws ProductException;
	
	public Page<ProductDto> readByCategoryId(int id, int pageNum, int pageSize) throws ProductException;
	
	public Page<ProductDto> readByGenderId(int id, int pageNum, int pageSize) throws ProductException;
	
	public Page<ProductDto> readByManufacturerId(int id, int pageNum, int pageSize) throws ProductException;

	public ProductDto readProductDetailsById(int id) throws ProductException;
}
