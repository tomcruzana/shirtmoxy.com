package com.shirtmoxy.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@GetMapping("/products")
	public ResponseEntity<String> getAllProducts() {
		String msg = "Welcome to shirtmoxy.com";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
