package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.MaterialDto;
import com.shirtmoxy.app.entity.Material;

@Component
@Qualifier("MaterialConverter")
public class MaterialConverter implements ObjectConverter<MaterialDto, Material> {

	@Override
	public MaterialDto convertToDTO(Material entity) {
		MaterialDto dto = new MaterialDto();
		dto.setId(entity.getId());
		dto.setType(entity.getType());
		return dto;
	}

	@Override
	public Material convertToEntity(MaterialDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
