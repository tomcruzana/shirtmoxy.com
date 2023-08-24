package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.CountryDto;
import com.shirtmoxy.app.entity.Country;

@Component
@Qualifier("CountryConverter")
public class CountryConverter  implements ObjectConverter<CountryDto, Country>{
	
	@Override
	public CountryDto convertToDTO(Country entity) {
		CountryDto dto = new CountryDto();

		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		
		return dto;
	}

	@Override
	public Country convertToEntity(CountryDto dto) {
		return null;
	}


}
