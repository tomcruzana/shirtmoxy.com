package com.shirtmoxy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.EmailSubscriptionDto;
import com.shirtmoxy.app.service.EmailSubscriptionService;

@RestController
public class EmailSubscriptionController {

	@Autowired
	EmailSubscriptionService emailSubscriptionService;

	@Autowired
	Environment env;

	@PostMapping("/subscribe")
	public ResponseEntity<String> addCustomer(@RequestBody EmailSubscriptionDto emailSubscriptionDto) throws Exception {
		
		emailSubscriptionService.addEmail(emailSubscriptionDto);
		
		String successMessage = env.getProperty("API.INSERT_SUCCESS");
		
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

}
