package com.shirtmoxy.app.utils.helper.converter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.shirtmoxy.app.dto.ContactUsCategoryDto;
import com.shirtmoxy.app.entity.ContactUsCategory;

@Component
@Qualifier("ContactUsCategoryConverter")
public class ContactUsCategoryConverter implements ObjectConverter<ContactUsCategoryDto, ContactUsCategory> {

	@Override
	public ContactUsCategoryDto convertToDTO(ContactUsCategory entity) {
		ContactUsCategoryDto dto = new ContactUsCategoryDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	@Override
	public ContactUsCategory convertToEntity(ContactUsCategoryDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
