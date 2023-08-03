package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.entity.Product;
import com.shirtmoxy.app.exception.ProductException;
import com.shirtmoxy.app.repository.ProductRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class ProductServiceImpl implements ProductService {

	// number of product items per page
	private final int SEARCH_RESULT_PER_PAGE = 5;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
    @Qualifier("ProductConverter")
	private ObjectConverter<ProductDto, Product> productConverter;

	@Override
	public Page<ProductDto> readAllProducts(int pageNum) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE, Sort.by("id"));
		Page<Product> productsPage = productRepo.findAll(pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public ProductDto readById(int id) throws ProductException {

		return null;
	}

	@Override
	public Page<ProductDto> search(String keyword, int pageNum) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE, Sort.by("name"));
		Page<Product> searchResult = productRepo.search(keyword, pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : searchResult.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, searchResult.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByCategoryId(int id, int pageNum) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByCategoryId(id, pageable);
		
		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}
		
		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByGenderId(int id, int pageNum) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByGenderId(id, pageable);
		
		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}
		
		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByManufacturerId(int id, int pageNum) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, SEARCH_RESULT_PER_PAGE, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByManufacturerId(id, pageable);
		
		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}
		
		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

}
