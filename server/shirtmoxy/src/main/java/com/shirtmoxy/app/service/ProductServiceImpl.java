package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.dto.ProductSkuDto;
import com.shirtmoxy.app.entity.Product;
import com.shirtmoxy.app.exception.ProductException;
import com.shirtmoxy.app.repository.ProductRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

import jakarta.persistence.criteria.Predicate;

@Service
public class ProductServiceImpl implements ProductService {

	// number of product items per page
	// private final int SEARCH_RESULT_PER_PAGE = 5;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	@Qualifier("ProductConverter")
	private ObjectConverter<ProductDto, Product> productConverter;

	// TODO - to be deleted
	@Override
	public Page<ProductDto> readAllProducts(int pageNum, int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));
		Page<Product> productsPage = productRepo.findAll(pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);

			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> search(String keyword, int pageNum, int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("name"));
		Page<Product> searchResult = productRepo.search(keyword, pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : searchResult.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, searchResult.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByCategoryId(int id, int pageNum, int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByCategoryId(id, pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByGenderId(int id, int pageNum, int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByGenderId(id, pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> readByManufacturerId(int id, int pageNum, int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));
		Page<Product> productsPage = productRepo.findByManufacturerId(id, pageable);

		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : productsPage.getContent()) {
			ProductDto productDto = productConverter.convertToDTO(product);
			productDtos.add(productDto);
		}

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public Page<ProductDto> readFilteredProducts(String productType, String category, List<String> manufacturers,
			List<String> genders, List<String> colors, List<String> sizes, List<String> materials, int pageNum,
			int pageSize) throws ProductException {
		Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("id"));

		// This code allows us to filter products based on the provided parameters, and
		// it's scalable, so we can easily add more filter criteria as needed

		// Create a Specification to dynamically build the query
		Specification<Product> spec = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (productType != null) {
				predicates.add(cb.equal(root.get("productType").get("name"), productType));
			}

			if (category != null) {
				predicates.add(cb.equal(root.get("category").get("name"), category));
			}

			if (manufacturers != null && !manufacturers.isEmpty()) {
				predicates.add(root.get("manufacturer").get("name").in(manufacturers));
			}

			if (genders != null && !genders.isEmpty()) {
				predicates.add(root.get("gender").get("type").in(genders));
			}

			if (colors != null && !colors.isEmpty()) {
				predicates.add(root.get("color").get("name").in(colors));
			}

			if (sizes != null && !sizes.isEmpty()) {
				predicates.add(root.get("size").get("type").in(sizes));
			}

			if (materials != null && !materials.isEmpty()) {
				predicates.add(root.get("material").get("type").in(materials));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		// find filtered products
		Page<Product> productsPage = productRepo.findAll(spec, pageable);

		// convert to dto
		List<ProductDto> productDtos = productsPage.getContent().stream().map(productConverter::convertToDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
	}

	@Override
	public ProductDto readProductDetailsBySku(String sku) throws ProductException {
		Optional<Product> productOptional = productRepo.findBySku(sku);
		Product product = productOptional.orElseThrow(() -> new ProductException("No products found"));

		return productConverter.convertToDTO(product);
	}

	@Override
	public ProductSkuDto readProductDetailsByNameAndColor(String productName, int colorId) throws ProductException {
		Optional<Product> productOptional = productRepo.findProductsByNameAndColorGrouped(productName, colorId);
		Product product = productOptional.orElseThrow(() -> new ProductException("No products found"));

		ProductSkuDto productSkuDto = new ProductSkuDto();
		productSkuDto.setSku(product.getSku());

		return productSkuDto;
	}

}
