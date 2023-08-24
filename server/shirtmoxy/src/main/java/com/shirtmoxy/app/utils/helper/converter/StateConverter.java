package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.CountryDto;
import com.shirtmoxy.app.dto.StateDto;
import com.shirtmoxy.app.entity.Country;
import com.shirtmoxy.app.entity.State;



@Component
@Qualifier("StateConverter")
public class StateConverter  implements ObjectConverter<StateDto, State>{
	
	@Autowired
	@Qualifier("CountryConverter")
	private ObjectConverter<CountryDto, Country> countryConverter;
	
	@Override
	public StateDto convertToDTO(State entity) {
		StateDto dto = new StateDto();

		dto.setId(entity.getId());
		dto.setCountry(countryConverter.convertToDTO(entity.getCountry()));
		dto.setName(entity.getName());
		
		return dto;
	}

	@Override
	public State convertToEntity(StateDto dto) {
		return null;
	}

}
