package com.shirtmoxy.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shirtmoxy.app.dto.CountryDto;
import com.shirtmoxy.app.dto.StateDto;
import com.shirtmoxy.app.entity.Country;
import com.shirtmoxy.app.entity.State;
import com.shirtmoxy.app.exception.StoreFormException;
import com.shirtmoxy.app.repository.CountryRepository;
import com.shirtmoxy.app.repository.StateRepository;
import com.shirtmoxy.app.utils.helper.converter.ObjectConverter;

@Service
public class StoreFormServiceImpl implements StoreFormService {

	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;

	@Autowired
	@Qualifier("CountryConverter")
	private ObjectConverter<CountryDto, Country> countryConverter;
	
	@Autowired
	@Qualifier("StateConverter")
	private ObjectConverter<StateDto, State> stateConverter;

	@Override
	public List<CountryDto> readAllCountries() throws StoreFormException {
		List<Country> countries = countryRepo.findAll();

		// check if countries is null or empty
		if (countries == null || countries.isEmpty()) {
			throw new StoreFormException("No countries found.");
		}

		List<CountryDto> countryDtos = new ArrayList<>();

		for (Country country : countries) {
			CountryDto countryDto = countryConverter.convertToDTO(country);
			countryDtos.add(countryDto);
		}

		return countryDtos;
	}

	@Override
	public List<StateDto> readAllStatesByCountryCode(String code) throws StoreFormException {
		List<State> states = stateRepo.findByCountryCode(code);

		// check if states is null or empty
		if (states == null || states.isEmpty()) {
			throw new StoreFormException("No States found.");
		}

		List<StateDto> stateDtos = new ArrayList<>();

		for (State state : states) {
			StateDto stateDto = stateConverter.convertToDTO(state);
			stateDtos.add(stateDto);
		}

		return stateDtos;
	}

}
