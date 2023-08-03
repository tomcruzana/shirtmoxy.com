package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.GenderDto;
import com.shirtmoxy.app.entity.Gender;

@Component
@Qualifier("GenderConverter")
public class GenderConverter implements ObjectConverter<GenderDto, Gender> {

	@Override
	public GenderDto convertToDTO(Gender entity) {
		GenderDto dto = new GenderDto();
		dto.setId(entity.getId());
		dto.setType(entity.getType());
		return dto;
	}

	@Override
	public Gender convertToEntity(GenderDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
