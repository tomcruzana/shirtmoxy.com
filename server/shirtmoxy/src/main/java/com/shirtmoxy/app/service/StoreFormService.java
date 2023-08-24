package com.shirtmoxy.app.service;

import java.util.List;

import com.shirtmoxy.app.dto.CountryDto;
import com.shirtmoxy.app.dto.StateDto;
import com.shirtmoxy.app.exception.StoreFormException;

public interface StoreFormService {
	List<CountryDto> readAllCountries() throws StoreFormException;
	
	List<StateDto> readAllStatesByCountryCode(String code) throws StoreFormException;
}
