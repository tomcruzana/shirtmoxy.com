package com.shirtmoxy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shirtmoxy.app.dto.CountryDto;
import com.shirtmoxy.app.dto.StateDto;
import com.shirtmoxy.app.service.StoreFormService;

@RestController
@RequestMapping("/storeform")
public class StoreFormController {

	@Autowired
	StoreFormService storeFormService;
	
	@GetMapping("/country/all")
	public ResponseEntity<List<CountryDto>> getAllCountries() {
		List<CountryDto> countryList = storeFormService.readAllCountries();
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}
	
	@GetMapping("/state/all")
	public ResponseEntity<List<StateDto>> getAllStatesByCountryCode(@RequestParam("countryCode") String code) {
		List<StateDto> stateList = storeFormService.readAllStatesByCountryCode(code);
		return new ResponseEntity<>(stateList, HttpStatus.OK);
	}
}
