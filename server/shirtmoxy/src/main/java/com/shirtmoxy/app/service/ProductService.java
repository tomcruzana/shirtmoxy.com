package com.shirtmoxy.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductSkuDto;
import com.shirtmoxy.app.exception.ProductException;

public interface ProductService {

	public Page<ProductDto> readAllProducts(int pageNum, int pageSize) throws ProductException;

	public Page<ProductDto> search(String keyword, int pageNum, int pageSize) throws ProductException;

	public Page<ProductDto> readByCategoryId(int id, int pageNum, int pageSize) throws ProductException;

	public Page<ProductDto> readByGenderId(int id, int pageNum, int pageSize) throws ProductException;

	public Page<ProductDto> readByManufacturerId(int id, int pageNum, int pageSize) throws ProductException;

	public Page<ProductDto> readFilteredProducts(String productType, String category, List<String> manufacturers,
			List<String> genders, List<String> colors, List<String> sizes, List<String> materials, int pageNum,
			int pageSize) throws ProductException;

	public ProductDto readProductDetailsBySku(String sku) throws ProductException;

	public ProductSkuDto readProductDetailsByNameAndColor(String productName, int colorId) throws ProductException;
}
