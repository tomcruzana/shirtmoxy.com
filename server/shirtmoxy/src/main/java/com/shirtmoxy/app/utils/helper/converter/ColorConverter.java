package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.ColorDto;
import com.shirtmoxy.app.entity.Color;

@Component
@Qualifier("ColorConverter")
public class ColorConverter implements ObjectConverter<ColorDto, Color> {

	@Override
	public ColorDto convertToDTO(Color entity) {
		ColorDto dto = new ColorDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setRed(entity.getRed());
		dto.setGreen(entity.getGreen());
		dto.setBlue(entity.getBlue());
		return dto;
	}

	@Override
	public Color convertToEntity(ColorDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
