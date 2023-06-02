package com.shirtmoxy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.ProductDto;
import com.shirtmoxy.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> productPage = productService.readAllProducts(pageNum);
		return new ResponseEntity<>(productPage, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<ProductDto>> searchProducts(@RequestParam("keyword") String keyword,
			@RequestParam("pageNum") int pageNum) {

		Page<ProductDto> searchResult = productService.search(keyword, pageNum);
		return new ResponseEntity<>(searchResult, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getProduct() {

		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
