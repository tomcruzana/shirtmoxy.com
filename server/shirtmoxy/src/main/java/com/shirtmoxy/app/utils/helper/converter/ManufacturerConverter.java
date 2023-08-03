package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.ManufacturerDto;
import com.shirtmoxy.app.entity.Manufacturer;

@Component
@Qualifier("ManufacturerConverter")
public class ManufacturerConverter implements ObjectConverter<ManufacturerDto, Manufacturer> {

	@Override
	public ManufacturerDto convertToDTO(Manufacturer entity) {
		ManufacturerDto dto = new ManufacturerDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public Manufacturer convertToEntity(ManufacturerDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
