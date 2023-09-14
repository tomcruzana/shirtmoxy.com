package com.shirtmoxy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.ContactUsCategoryDto;
import com.shirtmoxy.app.dto.ContactUsDto;
import com.shirtmoxy.app.service.ContactUsService;

@RestController
@RequestMapping("/contact")
public class ContactUsController {

	@Autowired
	ContactUsService contactUsService;

	@Autowired
	Environment env;

	@GetMapping("/category/all")
	public ResponseEntity<List<ContactUsCategoryDto>> getAllContactCategories() {
		List<ContactUsCategoryDto> contactUsCategoryList = contactUsService.readAllCategories();
		return new ResponseEntity<>(contactUsCategoryList, HttpStatus.OK);
	}

	@PostMapping("/submit-message")
	public ResponseEntity<String> submitContactUsMessage(@RequestBody ContactUsDto contactUsDto) {
		contactUsService.sendMessage(contactUsDto);
		String successMessage = env.getProperty("API.CONTACT_US_DETAILS_CREATE_SUCCESS");
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

}
