package com.shirtmoxy.app.utils.helper.converter;

//Type parameter 'DTO' and 'Entity' are Placeholders
public interface ObjectConverter<DTO, Entity> {
	DTO convertToDTO(Entity entity);

	Entity convertToEntity(DTO dto);
}