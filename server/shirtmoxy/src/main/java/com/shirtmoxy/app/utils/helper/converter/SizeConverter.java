package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.SizeDto;
import com.shirtmoxy.app.entity.Size;

@Component
@Qualifier("SizeConverter")
public class SizeConverter implements ObjectConverter<SizeDto, Size> {

	@Override
	public SizeDto convertToDTO(Size entity) {
		SizeDto dto = new SizeDto();
		dto.setId(entity.getId());
		dto.setType(entity.getType());
		dto.setLength(entity.getLength());
		dto.setWidth(entity.getWidth());
		return dto;
	}

	@Override
	public Size convertToEntity(SizeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
